package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> findAll(Map<String, Object> ob, List<String> typeCode);

    List<BuildingSearchResponse> searchAll(BuildingSearchBuilder buildingSearchBuilder);

    void addOrUpdateBuilding(BuildingDTO buildingDTO);

    void delete(List<Long> ids);

    BuildingDTO findBuildingById(Long id);
}
