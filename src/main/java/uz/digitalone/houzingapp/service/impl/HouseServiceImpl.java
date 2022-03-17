package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.*;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.*;
import uz.digitalone.houzingapp.repository.HouseRepository;
import uz.digitalone.houzingapp.repository.UserRepository;
import uz.digitalone.houzingapp.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseDetailsService houseDetailsService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final AttachmentService attachmentService;
    private final UserRepository userRepository;


    @Override
    public HttpEntity<?> create(HouseDto dto) {
        House house = new House();

        house.setName(dto.getName());
        house.setDescription(dto.getDescription());

        HouseDetailsDto detailsDto = dto.getHouseDetailsDto();
        if(detailsDto != null){
            HouseDetails details = houseDetailsService.create(detailsDto);
            if(details != null)
                house.setHouseDetails(details);
        }
        house.setPrice(dto.getPrice());
        house.setSalePrice(dto.getSalePrice());
        LocationDto locationDto = dto.getLocationDto();
        if(locationDto != null){
            Location location = locationService.create(locationDto);
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
        house = houseRepository.save(house);
        Response response = new Response(true, "Successfully created.", house);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> findAll(Pageable pageable) {
        Response response = new Response();
        Page<House> incomingAll = houseRepository.findAll(pageable);
        List<House> incomingList = incomingAll.getContent();
        response.setSuccess(true);
        if(incomingList.size() == 0){
            response.setMessage("No data was found");
            incomingList = new ArrayList<>();
        }
        else {
            response.setMessage("House list");

        }

        response.setDataList(new ArrayList<>(incomingList));
        response.getMap().put("size", incomingAll.getSize());
        response.getMap().put("total_elements", incomingAll.getTotalElements());
        response.getMap().put("total_pages", incomingAll.getTotalPages());
        return ResponseEntity.ok(response);
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
        return null;
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> findByList(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(()
                -> new RuntimeException("User id not found"));

        List<House> houseList = houseRepository.findByUser(user).orElseThrow(()
                -> new RuntimeException("House not found"));

        Response response ;
        if (houseList.size() > 0) {
            response = new Response(true, "Houses for user Id", houseList);
        }else
            response = new Response(true, "User's houses not found");

        return ResponseEntity.status(response.isSuccess() ? 200:401).body(response);
    }
}
