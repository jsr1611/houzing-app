package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.repository.HouseRepository;
import uz.digitalone.houzingapp.response.Response;
import uz.digitalone.houzingapp.service.HouseService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    public HttpEntity<?> create(HouseDto dto) {
        return null;
    }

    @Override
    public HttpEntity<?> findAll(Pageable pageable) {
        Response response = new Response();
        Page<House> incomingAll = houseRepository.findAll(pageable);
        List<House> incomingList = incomingAll.getContent();
        response.setSuccess(true);
        if(incomingList.size() == 0){
            response.setMessage("No data was found");
        }
        else {
            response.setMessage("House list");
        }
//        response = new Response(true, "Incoming List", incomingList);
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
        return null;
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
