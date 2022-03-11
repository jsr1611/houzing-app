package uz.digitalone.houzingapp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.entity.House;

public interface HouseService {

    public HttpEntity<?> create(HouseDto dto);

    public HttpEntity<?> findAll(
            String houseName, String firstName, String lastName, Integer room,
            Double minPrice, Double maxPrice, String address, String city,
            String region, String country, String zipCode, @ApiIgnore Pageable pageable);

    public HttpEntity<?> findOneById(Long id);

    public House findById(Long id);

    public HttpEntity<?> edit(Long id, HouseDto dto);

    public HttpEntity<?> delete(Long id);
}
