package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.*;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.*;
import uz.digitalone.houzingapp.mapper.AttachmentMapper;
import uz.digitalone.houzingapp.mapper.HouseMapper;
import uz.digitalone.houzingapp.repository.HouseRepository;
import uz.digitalone.houzingapp.service.*;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseDetailsService houseDetailsService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final AttachmentService attachmentService;
    private final HouseMapper houseMapper;
    private final AttachmentMapper attachmentMapper;


    @Override
    public HttpEntity<?> create(HouseDto dto) {
        House house = new House();
        User user = MyUserService.currentUser;
        house.setUser(user);
        house.setName(dto.getName());
        house.setDescription(dto.getDescription());

        HouseDetailsDto detailsDto = dto.getHouseDetails();
        if(detailsDto != null){
            HouseDetails details = houseDetailsService.create(detailsDto);
            if(details != null && details.getRoom()>0)
                house.setHouseDetails(details);
        }
        house.setPrice(dto.getPrice());
        house.setSalePrice(dto.getSalePrice());
        LocationDto locationDto = dto.getLocations();
        if(locationDto != null && locationDto.getLongitude() != 0 && locationDto.getLatitude() != 0){
            Location location = locationService.findOne(dto.getLocations());
            if(location != null)
                house.setLocation(location);
            else {
                location = locationService.create(locationDto);
                if (location != null)
                    house.setLocation(location);
            }
        }
        house.setAddress(dto.getAddress());
        house.setCity(dto.getCity());
        house.setRegion(dto.getRegion());
        house.setCountry(dto.getCountry());
        house.setZipCode(dto.getZipCode());


        Set<AttachmentDto> attachmentDto = dto.getAttachments();
        if(attachmentDto != null && attachmentDto.size() > 0){
            Set<Attachment> attachmentList = attachmentService.createList(attachmentDto);
            house.setAttachments(attachmentList);
        }
        Category category = categoryService.findById(dto.getCategoryId());
        if(category != null)
            house.setCategory(category);
        if(house.getHouseDetails() == null)
            return ResponseEntity.status(400).body(new Response(false, "Error with house Details or status info", detailsDto));
        house.setStatus(true);
        house = houseRepository.save(house);
        uz.digitalone.houzingapp.dto.response.HouseDto result = houseMapper.fromEntity(house);
        Response response = new Response(true, "Successfully created.", result);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> findAll(
            String houseName, String firstName, String lastName, Integer room,
            Double minPrice, Double maxPrice, String address, String city, String region,
            String country, String zipCode, Pageable pageable) {
        Response response;
        Page<House> incomingAll = houseRepository.findAll(
                getSpecification(houseName, firstName, lastName, room, minPrice, maxPrice, address,
                        city, region, country, zipCode),
                pageable);
        List<House> incomingList = incomingAll.getContent();
        List<uz.digitalone.houzingapp.dto.response.HouseDto> result;
        if(incomingList.size() == 0){
            response = new Response(true, "No data was found");
        }
        else {
            result = houseMapper.fromEntities(incomingList);
            response = new Response(true, "House list", result);
        }
        response.getMap().put("size", incomingAll.getSize());
        response.getMap().put("total_elements", incomingAll.getTotalElements());
        response.getMap().put("total_pages", incomingAll.getTotalPages());
        return ResponseEntity.ok(response);
    }

    private Specification<House> getSpecification(
           final String houseName,
           final String firstName,
           final String lastName,
           final Integer room,
           final Double minPrice,
           final Double maxPrice,
           final String address,
           final String city,
           final String region,
           final String country,
           final String zipCode) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if(houseName != null){
                predicateList.add(criteriaBuilder.like(root.get("name"),"%"+ houseName + "%"));
            }
            if(minPrice != null && maxPrice != null){
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            else if(minPrice != null){
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            else if(maxPrice != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if(firstName != null && lastName != null) {

                StringJoiner joiner = new StringJoiner(" ");
                String fullName = joiner.add(firstName).add(lastName).toString().trim();
                if (!"".equals(fullName)) {
                    Predicate predicateFirstName;
                    Predicate predicateLastName;

                    if (!fullName.contains(" ")) {
                        predicateFirstName = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("firstname")), "%" + fullName.toLowerCase() + "%");
                        predicateLastName = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("lastname")), "%" + fullName.toLowerCase() + "%");
                        predicateList.add(criteriaBuilder.or(predicateFirstName, predicateLastName));
                    } else {
                        predicateFirstName = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("firstname")), "%" + firstName.toLowerCase() + "%");
                        predicateLastName = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("lastname")), "%" + lastName.toLowerCase() + "%");
                        Predicate predicate = criteriaBuilder.and(predicateFirstName, predicateLastName);
                        predicateList.add(predicate);
                    }
                }
            }
            else {
                String name = "";
                if(firstName != null){
                    name = firstName.toLowerCase();
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("firstname")), "%"+name+"%"));
                }
                if(lastName != null){
                    name = lastName.toLowerCase();
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("lastname")), "%"+name+"%"));
                }
            }

            if(room != null){
                predicateList.add(criteriaBuilder.equal(root.get("houseDetails").get("room"), room));
            }
            if(address != null){
                predicateList.add(criteriaBuilder.like(root.get("address"), "%"+address+"%"));
            }
            if(city != null){
                predicateList.add(criteriaBuilder.like(root.get("city"), city+"%"));
            }
            if(region != null){
                predicateList.add(criteriaBuilder.like(root.get("region"), region+"%"));
            }
            if(country != null){
                predicateList.add(criteriaBuilder.like(root.get("country"), country+"%"));
            }
            if(zipCode != null){
                predicateList.add(criteriaBuilder.like(root.get("zipCode"), zipCode+"%"));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    private Specification<House> getSpecificationForMe(
            final String houseName,
            final Boolean status,
            final LocalDateTime createdAt,
            final User user
            ) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.equal(root.get("user"), user));

            if(houseName != null){
                predicateList.add(criteriaBuilder.like(root.get("name"),"%"+ houseName + "%"));
            }
            if(status != null){
                predicateList.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if(createdAt != null){
                predicateList.add(criteriaBuilder.equal(root.get("createdAt"), createdAt));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    @Override
    public HttpEntity<?> findOneById(Long id) {
        Response response;
        House house = findById(id);
        if(house != null){
            response = new Response(true, "House", houseMapper.fromEntity(house));
        }else {
            response = new Response(false, "House with id {"+id+"} not found");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public House findById(Long id) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        House house = null;
        if(optionalHouse.isPresent()){
            house = optionalHouse.get();
        }
        return house;
    }

    @Override
    public HttpEntity<?> edit(Long id, HouseDto dto) {
        Response response;
        House house = findById(id);


        if(house != null && dto != null){
            if(dto.getName() != null && !dto.getName().equals(house.getName()))
                house.setName(dto.getName());

            if(dto.getDescription() != null && !dto.getDescription().equals(house.getDescription()))
                house.setDescription(dto.getDescription());

            if(dto.getAddress() != null && !dto.getAddress().equals(house.getAddress()))
                house.setAddress(dto.getAddress());

            if(dto.getCity() != null && !dto.getCity().equals(house.getCity()))
                house.setCity(dto.getCity());

            if(dto.getRegion() != null && !dto.getRegion().equals(house.getRegion()))
                house.setRegion(dto.getRegion());
            if(dto.getCountry() != null && !dto.getCountry().equals(house.getCountry()))
                house.setCountry(dto.getCountry());
            if(dto.getZipCode() != null && !dto.getZipCode().equals(house.getZipCode()))
                house.setZipCode(dto.getZipCode());
            if(dto.getPrice() != null && dto.getPrice() != house.getPrice())
                house.setPrice(dto.getPrice());
            if(dto.getSalePrice() != null && dto.getSalePrice() != house.getSalePrice())
                house.setSalePrice(dto.getSalePrice());
            if(dto.getStatus() != null && !dto.getStatus().equals(house.getStatus()))
                house.setStatus(dto.getStatus());
            if(dto.getFavorite() != null && !dto.getFavorite().equals(house.getFavorite())){
                house.setFavorite(dto.getFavorite());
            }
            if(dto.getHouseDetails() != null){
                HouseDetails houseDetails = houseDetailsService.updateById(house.getHouseDetails().getId(), dto.getHouseDetails());
                if(houseDetails != null)
                    house.setHouseDetails(houseDetails);
            }
            if(dto.getLocations() != null){
                Location location = locationService.updateById(house.getLocation().getId(), dto.getLocations());
                if(location != null)
                    house.setLocation(location);
            }
            if(dto.getAttachments() != null){
                Set<Attachment> attachments = attachmentService.update(house.getAttachments(), dto.getAttachments());
                if(attachments != null)
                    house.getAttachments().clear();
                    house.setAttachments(attachments);
            }
            if(dto.getCategoryId() != null){
                Category category = categoryService.findById(dto.getCategoryId());
                if(!house.getCategory().equals(category))
                    house.setCategory(category);
            }
            house = houseRepository.save(house);
            response = new Response(true, "Successfully updated.", houseMapper.fromEntity(house));
        }
        else {
            response = new Response(false, "House with id {"+id+"} does not exist");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Response response;
        House byId = findById(id);
        if(byId != null){
            houseRepository.delete(byId);
            response = new Response(true, "Successfully deleted.");
        }
        else {
            response = new Response(false, "House with id {"+id+"} does not exist.");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findMyHouses(String houseName, Boolean status, LocalDateTime createdAt, Pageable pageable) {
        User user = MyUserService.currentUser;
        Response response = null;
        List<uz.digitalone.houzingapp.dto.response.HouseDto> result = null;
        if (user != null){

            Page<House> houseListPage = houseRepository.findAll(
                    getSpecificationForMe(houseName, status, createdAt, user),
                    pageable);
            List<House> houseList = houseListPage.getContent();
            if(houseList.size() > 0){
                result = houseMapper.fromEntities(houseList);
                response = new Response(true, "Houses List", result);
                response.getMap().put("size", houseListPage.getSize());
                response.getMap().put("total_elements", houseListPage.getTotalElements());
                response.getMap().put("total_pages", houseListPage.getTotalPages());
            }
            else
                response = new Response(true, "No houses found.");
        }else {
            response = new Response(false, "Unauthorized access. Please, login first and then try again.");
        }
        return ResponseEntity.status(response.isSuccess() ? 200:401).body(response);
    }

    public boolean checkIsNotNull(HouseDto dto) {
        return dto.getName().isEmpty()
                || dto.getAddress().isEmpty()
                || dto.getAddress()==null
                || dto.getPrice()==null
                || dto.getPrice()==0
                || dto.getZipCode().isEmpty()
                || dto.getPrice()==0.0
                || dto.getSalePrice()==0.0
                || dto.getRegion().isEmpty()
                || dto.getLocations()==null
                || dto.getCountry().isEmpty()
                || dto.getCity().isEmpty()
                || dto.getAttachments()==null
                || dto.getHouseDetails()==null;
    }
}
