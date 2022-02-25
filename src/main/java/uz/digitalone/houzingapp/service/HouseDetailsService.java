package uz.digitalone.houzingapp.service;

import org.springframework.http.HttpEntity;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;

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

    HouseDetails create(HouseDetailsDto detailsDto);
}
