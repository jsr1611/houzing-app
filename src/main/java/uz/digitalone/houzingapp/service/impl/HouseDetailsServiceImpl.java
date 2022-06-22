package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.repository.HouseDetailsRepository;
import uz.digitalone.houzingapp.service.HouseDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseDetailsServiceImpl implements HouseDetailsService {
    /**
     * HouseDetails - CRUD
     */
    private final HouseDetailsRepository houseDetailsRepository;

    @Override
    public HttpEntity<?> save(HouseDetailsDto dto) {
        HouseDetails houseDetails = create(dto);
        Response response = null;
        if(houseDetails != null)
            response = new Response(true, "Successfully created.", houseDetails);
        else
            response = new Response(false, "Something went wrong");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findAll() {
        Response response = null;
        List<HouseDetails> houseDetailsList = houseDetailsRepository.findAll();

        if (houseDetailsList.size() == 0) {
            response = new Response(true, "No HouseDetails were found");
        } else {
            response = new Response(true, "House Details List", new ArrayList<>(houseDetailsList));
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findOneById(Long id) {
        Response response = null;
        HouseDetails houseDetails = findById(id);
        if (houseDetails != null) {
            response = new Response(true, "House details", houseDetails);
        } else {
            response = new Response(false, "HouseDetails was not found with id {" + id + "}");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> editById(Long id, HouseDetailsDto dto) {
        Response response = null;
        HouseDetails houseDetails = updateById(id, dto);
        if(houseDetails != null){
            response = new Response(true, "Updated HouseDetails", houseDetails);
        } else {
            response = new Response(false, "HouseDetails was not found with id{" + id + "}");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HouseDetails updateById(Long id, HouseDetailsDto dto) {
        HouseDetails houseDetails = findById(id);
        if(houseDetails != null && dto != null) {
            if (dto.getRoom() != null && dto.getRoom() != houseDetails.getRoom()) {
                houseDetails.setRoom(dto.getRoom());
            }
            if (dto.getBath() != null && dto.getBath() != houseDetails.getBath()) {
                houseDetails.setBath(dto.getBath());
            }
            if(dto.getGarage() != null && dto.getGarage() != houseDetails.getGarage())
                houseDetails.setGarage(dto.getGarage());
            if (dto.getArea() != null && dto.getArea() != houseDetails.getArea()) {
                houseDetails.setArea(dto.getArea());
            }
            if(dto.getBeds() != null && dto.getBeds() != houseDetails.getBeds()){
                houseDetails.setBeds(dto.getBeds());
            }
            if(dto.getYearBuilt() != null && dto.getYearBuilt() != houseDetails.getYearBuilt()){
                houseDetails.setYearBuilt(dto.getYearBuilt());
            }
             return houseDetailsRepository.save(houseDetails);
        }
        else
            return null;
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Response response = null;
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            response =new Response(true, "Successfully deleted");
            response.setData(houseDetails);
        } else {
            response =new Response(false, "HouseDetails was not found with id {" + id + "}");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HouseDetails create(HouseDetailsDto dto) {
        if(dto != null) {
            HouseDetails houseDetails = new HouseDetails();
            if (dto.getRoom() != null) {
                houseDetails.setRoom(dto.getRoom());
            }
            if (dto.getBath() != null) {
                houseDetails.setBath(dto.getBath());
            }
            if(dto.getGarage() != null)
                houseDetails.setGarage(dto.getGarage());
            if (dto.getArea() != null) {
                houseDetails.setArea(dto.getArea());
            }
            if(dto.getBeds() != null){
                houseDetails.setBeds(dto.getBeds());
            }

            if(dto.getYearBuilt() != null){
                houseDetails.setYearBuilt(dto.getYearBuilt());
            }

            houseDetails = houseDetailsRepository.save(houseDetails);
            return houseDetails;
        }
        return null;
    }

    public HouseDetails findById(Long id){
        Optional<HouseDetails> byId = houseDetailsRepository.findById(id);
        return byId.orElse(null);
    }
}
