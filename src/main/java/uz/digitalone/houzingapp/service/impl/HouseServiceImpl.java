package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.repository.HouseRepository;
import uz.digitalone.houzingapp.service.HouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    public HttpEntity<?> create(HouseDto dto) {
        House house = new House();

        house.setName(dto.getName());
        house.setDescription(dto.getDescription());

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
}
