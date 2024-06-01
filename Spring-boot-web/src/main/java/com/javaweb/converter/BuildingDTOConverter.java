package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingEntity toBuildingEntity(BuildingDTO item) {

        BuildingEntity buildingEntity = modelMapper.map(item, BuildingEntity.class);
        buildingEntity.setTypeCode(String.join(", ", item.getTypeCode()));

        return buildingEntity;
    }
}
