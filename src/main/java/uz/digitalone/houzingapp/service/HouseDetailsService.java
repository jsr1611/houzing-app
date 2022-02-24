package uz.digitalone.houzingapp.service;

import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;

import java.util.List;

public interface HouseDetailsService {
    /**
     * HouseDetails save
     * @param dto
     * @return
     */
    HouseDetails save(HouseDetailsDto dto);

    /**
     * HouseDetails list
     * @return
     */
    List<HouseDetails> findAll();

    /**
     * HouseDetails on id
     * @param id
     * @return
     */
    HouseDetails findById(Long id);


    /**
     * HouseDetails edited
     * @param id
     * @param dto
     * @return
     */
    HouseDetails editById(Long id, HouseDetailsDto dto);

    /**
     * HouseDetails By Id deleted
     * @param id
     * @return
     */
    HouseDetails deleteById(Long id);
}
