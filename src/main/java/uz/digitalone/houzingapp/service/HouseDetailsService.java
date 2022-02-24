package uz.digitalone.houzingapp.service;

import org.springframework.http.HttpEntity;
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
    HttpEntity<?> save(HouseDetailsDto dto);

    /**
     * HouseDetails list
     * @return
     */
    HttpEntity<?> findAll();

    /**
     * HouseDetails on id
     * @param id
     * @return
     */
    HttpEntity<?> findById(Long id);


    /**
     * HouseDetails edited
     * @param id
     * @param dto
     * @return
     */
    HttpEntity<?> editById(Long id, HouseDetailsDto dto);

    /**
     * HouseDetails By Id deleted
     * @param id
     * @return
     */
    HttpEntity<?> deleteById(Long id);
}
