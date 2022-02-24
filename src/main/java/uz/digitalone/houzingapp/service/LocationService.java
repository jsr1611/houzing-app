package uz.digitalone.houzingapp.service;

import org.springframework.http.HttpEntity;
import uz.digitalone.houzingapp.dto.LocationDto;

public interface LocationService {

    /**
     * Location save
     * @return saved location
     */
    HttpEntity<?> save(LocationDto dto);

    /**
     * Location edit only Id
     * @return edited location
     */
    HttpEntity<?> edit(LocationDto dto, Long id);

    /**
     * Location Delete for Id
     * @return true = Delete || false = id Not found
     */
    HttpEntity<?> delete(Long id);

    /**
     * Get Location Find By Id
     * @return location || Id not found
     */
    HttpEntity<?> findById(Long id);

    /**
     * Get All Location
     * @return Location lists
     */

    HttpEntity<?> findAll();

}
