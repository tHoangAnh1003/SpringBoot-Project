package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.service.RentAreaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentAreaServiceImpl implements RentAreaService  {
    @Override
    public List<RentEntity> createRentArea(BuildingEntity buildingEntity, BuildingDTO buildingDTO) {
        List<RentEntity> result = new ArrayList<>();

        if(buildingDTO.getRentArea() != "") {
            String[] values = buildingDTO.getRentArea().split(",");

            for (String it : values) {
                RentEntity rentArea = new RentEntity();
                rentArea.setValue(it);
                rentArea.setBuildingEntity(buildingEntity);
                result.add(rentArea);
            }
        }

        return result;
    }
}
