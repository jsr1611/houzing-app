package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.repository.HouseDetailsRepository;
import uz.digitalone.houzingapp.response.Response;
import uz.digitalone.houzingapp.service.HouseDetailsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseDetailsServiceImpl implements HouseDetailsService {
    private final HouseDetailsRepository houseDetailsRepository;

    @Override
    public Response save(HouseDetailsDto dto) {
        HouseDetails houseDetails = new HouseDetails();
        houseDetails.setRoom(dto.getRoom());
        houseDetails.setBath(dto.getBath());
        houseDetails.setHasGarage(dto.isHasGarage());
        houseDetails.setArea(dto.getArea());
        houseDetailsRepository.save(houseDetails);
        return new Response(true, "Successfully saved", houseDetails);
    }

    @Override
    public Response findAll() {
        List<HouseDetails> houseDetailsList = houseDetailsRepository.findAll();
        return new Response(true, "HouseDetails list", houseDetailsList);
    }

    @Override
    public Response findById(Long id) {
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            return new Response(true, "Registered by HouseDetails id{" + id + "}", houseDetails);
        }
        return new Response(false, "Not found", null);
    }

    @Override
    public Response editById(Long id, HouseDetailsDto dto) {
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
            return new Response(true, "Successfully edited", houseDetails);
        }
        return new Response(false, "Not found", null);
    }

    @Override
    public Response deleteById(Long id) {
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if (optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            return new Response(true, "Successfully deleted", houseDetails);
        }
        return new Response(false, "Not found", null);
    }
}
