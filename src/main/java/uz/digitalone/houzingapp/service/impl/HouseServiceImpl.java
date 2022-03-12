package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.*;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.*;
import uz.digitalone.houzingapp.repository.HouseRepository;
import uz.digitalone.houzingapp.service.*;

import javax.persistence.criteria.Predicate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseDetailsService houseDetailsService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final AttachmentService attachmentService;


    @Override
    public HttpEntity<?> create(HouseDto dto) {
        House house = new House();
        User user = MyUserService.currentUser;
        house.setUser(user);
        house.setName(dto.getName());
        house.setDescription(dto.getDescription());

        HouseDetailsDto detailsDto = dto.getHouseDetailsDto();
        if(detailsDto != null){
            HouseDetails details = houseDetailsService.create(detailsDto);
            if(details != null && details.getRoom()>0)
                house.setHouseDetails(details);
        }
        house.setPrice(dto.getPrice());
        house.setSalePrice(dto.getSalePrice());
        LocationDto locationDto = dto.getLocationDto();
        if(locationDto != null && locationDto.getLongitude() != 0 && locationDto.getLatitude() != 0){
            Location location = locationService.findOne(dto.getLocationDto());
            if(location != null)
                house.setLocation(location);
            else
                location = locationService.create(locationDto);
                if(location != null)
                    house.setLocation(location);
        }
        house.setAddress(dto.getAddress());
        house.setCity(dto.getCity());
        house.setRegion(dto.getRegion());
        house.setCountry(dto.getCountry());
        house.setZipCode(dto.getZipCode());
        AttachmentDto attachmentDto = dto.getAttachmentDto();
        if(attachmentDto != null && attachmentDto.getImgPathList().size() > 0){
            Set<Attachment> attachmentList = attachmentService.createList(attachmentDto);
            house.setAttachments(attachmentList);
        }
        Category category = categoryService.findById(dto.getCategoryId());
        if(category != null)
            house.setCategory(category);
        if(house.getHouseDetails() == null)
            return ResponseEntity.status(400).body(new Response(false, "Error with house Details or status info", detailsDto));
        house = houseRepository.save(house);
        Response response = new Response(true, "Successfully created.", house);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> findAll(
            String houseName, String firstName, String lastName, Integer room,
            Double minPrice, Double maxPrice, String address, String city, String region,
            String country, String zipCode, Pageable pageable) {
        Response response = null;
        Page<House> incomingAll = houseRepository.findAll(
                getSpecification(houseName, firstName, lastName, room, minPrice, maxPrice, address,
                        city, region, country, zipCode),
                pageable);
        List<House> incomingList = incomingAll.getContent();

        if(incomingList.size() == 0){
            response = new Response(true, "No data was found");
            incomingList = new ArrayList<>();
        }
        else {
            response =new Response(true, "House list");
        }

        response.setDataList(new ArrayList<>(incomingList));
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
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("salePrice"), minPrice));
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("salePrice"), maxPrice));
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

            if(room != null){
                predicateList.add(criteriaBuilder.equal(root.get("houseDetails").get("room"), room));
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

    @Override
    public HttpEntity<?> findOneById(Long id) {
        return null;
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
        Response response = null;
        House house = findById(id);
        if(house != null){
            house.setName(dto.getName());
            house.setDescription(dto.getDescription());
            house.setAddress(dto.getAddress());
            house.setCity(dto.getCity());
            house.setRegion(dto.getRegion());
            house.setZipCode(dto.getZipCode());
            house.setCountry(dto.getCountry());
            house.setPrice(dto.getPrice());
            house.setSalePrice(dto.getSalePrice());

            HouseDetails houseDetails = houseDetailsService.updateById(house.getHouseDetails().getId(), dto.getHouseDetailsDto());
            house.setHouseDetails(houseDetails);
            Location location = locationService.updateById(house.getLocation().getId(), dto.getLocationDto());
            house.setLocation(location);
            Set<Attachment> attachments = attachmentService.update(house.getAttachments(), dto.getAttachmentDto());
            house.setAttachments(attachments);
            Category category = categoryService.findById(dto.getCategoryId());
            house.setCategory(category);
            house = houseRepository.save(house);
            response = new Response(true, "Successfully updated.", house);
        }
        else {
            response = new Response(false, "House with id {"+id+"} does not exist");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Response response = null;
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
}
