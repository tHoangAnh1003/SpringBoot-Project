package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.reponsitory.BuildingRepository;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	public List<BuildingDTO> findAll(Map<String, Object> ob, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(ob, typeCode);
		
		List<BuildingEntity> buildEntities = buildingRepository.findAll(buildingSearchBuilder);
		List<BuildingDTO> result = new ArrayList<>();
		
		for (BuildingEntity item : buildEntities) {
			BuildingDTO building = buildingConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;
	}
}
