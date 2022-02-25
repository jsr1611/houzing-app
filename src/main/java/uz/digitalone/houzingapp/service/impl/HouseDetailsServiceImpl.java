package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.repository.HouseDetailsRepository;
import uz.digitalone.houzingapp.service.HouseDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        Response response = new Response();
        List<HouseDetails> houseDetailsList = houseDetailsRepository.findAll();
        response.setSuccess(true);
        if (houseDetailsList.size() == 0) {
            response.setMessage("No HouseDetails were found");
        } else {
            response.setDataList(new ArrayList<>(houseDetailsList));
        }
        return ResponseEntity.ok(houseDetailsList);
    }

    @Override
    public HttpEntity<?> findById(Long id) {
        Response response = new Response();
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            response.setSuccess(true);
            response.setData(houseDetails);
        } else {
            response.setSuccess(false);
            response.setMessage("HouseDetails was not found with id {" + id + "}");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> editById(Long id, HouseDetailsDto dto) {
        Response response = new Response();
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            if (dto.getRoom() != null && !dto.getRoom().equals(houseDetails.getRoom())) {
                houseDetails.setRoom(dto.getRoom());
            }
            if (dto.getBath() != null && !dto.getBath().equals(houseDetails.getBath())) {
                houseDetails.setBath(dto.getBath());
            }
            houseDetails.setHasGarage(dto.isHasGarage());
            if (dto.getArea() != null && !dto.getArea().equals(houseDetails.getArea())) {
                houseDetails.setArea(dto.getArea());
            }
            HouseDetails saved = houseDetailsRepository.save(houseDetails);
            response.setSuccess(true);
            response.setData(saved);

        } else {
            response.setSuccess(false);
            response.setMessage("HouseDetails was not found with id{" + id + "}");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Response response = new Response();
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            response.setSuccess(true);
            response.setMessage("Successfully deleted");
            response.setData(houseDetails);
        } else {
            response.setSuccess(false);
            response.setMessage("HouseDetails was not found with id {" + id + "}");
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
            houseDetails.setHasGarage(dto.isHasGarage());
            if (dto.getArea() != null) {
                houseDetails.setArea(dto.getArea());
            }
            houseDetails = houseDetailsRepository.save(houseDetails);
            return houseDetails;
        }
        return null;
    }
}
