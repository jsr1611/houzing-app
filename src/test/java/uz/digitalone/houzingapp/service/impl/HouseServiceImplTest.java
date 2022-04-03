package uz.digitalone.houzingapp.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HouseServiceImplTest {
    @Test
    @DisplayName(value = "Ushbu test dto ga kerakli malumotlar berilgan holatda passed bo'ladi")
    public void checkHouseDto() {
        HouseServiceImpl houseService = new HouseServiceImpl(null,null,null,null,null,null,null);
        HouseDetailsDto houseDetailsDto = new HouseDetailsDto();
        LocationDto locationDto = new LocationDto();
        Set<AttachmentDto> dtoList = new HashSet<>();
        HouseDto dto = new HouseDto("Dacha","bla-bla",houseDetailsDto,122.5,15000.0,locationDto,"Toshkent","Toshkent","Olmazor","Uzbekistan","10002",dtoList,15L,true,true);
        Assertions.assertFalse(houseService.checkIsNotNull(dto));
    }
}