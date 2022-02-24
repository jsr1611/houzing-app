package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.repository.HouseDetailsRepository;
import uz.digitalone.houzingapp.service.HouseDetailsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseDetailsServiceImpl implements HouseDetailsService {
    private final HouseDetailsRepository houseDetailsRepository;

    @Override
    public HouseDetails save(HouseDetailsDto dto) {
        HouseDetails houseDetails = new HouseDetails();
        houseDetails.setRoom(dto.getRoom());
        houseDetails.setBath(dto.getBath());
        houseDetails.setHasGarage(dto.isHasGarage());
        houseDetails.setArea(dto.getArea());
        return houseDetailsRepository.save(houseDetails);
    }

    @Override
    public List<HouseDetails> findAll() {
        return houseDetailsRepository.findAll();
    }

    @Override
    public HouseDetails findById(Long id) {
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if(optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            return houseDetails;
        }
        return null;
    }

    @Override
    public HouseDetails editById(Long id, HouseDetailsDto dto) {
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if(optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            if(dto.getRoom() != null && !dto.getRoom().equals(houseDetails.getRoom())) {
                houseDetails.setRoom(dto.getRoom());
            }
            if(dto.getBath() != null && !dto.getBath().equals(houseDetails.getBath())) {
                houseDetails.setBath(dto.getBath());
            }
            houseDetails.setHasGarage(dto.isHasGarage());
            if(dto.getArea() != null && !dto.getArea().equals(houseDetails.getArea())) {
                houseDetails.setArea(dto.getArea());
            }
            return houseDetailsRepository.save(houseDetails);
        }
        return null;
    }

    @Override
    public HouseDetails deleteById(Long id) {
        Optional<HouseDetails> optionalHouseDetails = houseDetailsRepository.findById(id);
        if(optionalHouseDetails.isPresent()) {
            HouseDetails houseDetails = optionalHouseDetails.get();
            return houseDetails;
        }
        return null;
    }
}
