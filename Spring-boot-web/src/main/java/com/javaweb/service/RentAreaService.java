package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentEntity;
import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public interface RentAreaService {
    List<RentEntity> createRentArea(BuildingEntity buildingEntity, BuildingDTO buildingDTO);
}
