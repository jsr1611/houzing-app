package uz.digitalone.houzingapp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.entity.House;

import java.time.LocalDateTime;

public interface HouseService {

   HttpEntity<?> create(HouseDto dto);

   HttpEntity<?> findAll(
    String houseName, String firstName, String lastName, Integer room,
    Double minPrice, Double maxPrice, String address, String city,
    String region, String country, String zipCode, @ApiIgnore Pageable pageable);

   HttpEntity<?> findOneById(Long id);

   House findById(Long id);

   HttpEntity<?> edit(Long id, HouseDto dto);

   HttpEntity<?> delete(Long id);

    HttpEntity<?> findMyHouses(String houseName, Boolean status, LocalDateTime createdAt, Pageable pageable);

    HttpEntity<?> getAllHousesByCategoryId(Long category_id, Pageable pageable);
}
