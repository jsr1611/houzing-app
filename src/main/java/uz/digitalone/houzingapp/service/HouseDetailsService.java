package uz.digitalone.houzingapp.service;

import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.response.Response;

import java.util.List;

public interface HouseDetailsService {
    /**
     * HouseDetails save
     * @param dto
     * @return
     */
    Response save(HouseDetailsDto dto);

    /**
     * HouseDetails list
     * @return
     */
    Response findAll();

    /**
     * HouseDetails on id
     * @param id
     * @return
     */
    Response findById(Long id);


    /**
     * HouseDetails edited
     * @param id
     * @param dto
     * @return
     */
    Response editById(Long id, HouseDetailsDto dto);

    /**
     * HouseDetails By Id deleted
     * @param id
     * @return
     */
    Response deleteById(Long id);
}
