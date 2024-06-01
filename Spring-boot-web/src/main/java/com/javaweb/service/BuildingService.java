package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface BuildingService {

    ResponseDTO listStaffs(Long buildingId);

    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    void createAndUpdateBuilding(BuildingDTO buildingDTO);

    BuildingDTO toBuildingDTO(BuildingEntity buildingEntity);
}
