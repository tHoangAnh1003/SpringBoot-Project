package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
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
		
//		List<BuildingEntity> buildEntities = buildingRepository.findAll();
		
//		List<BuildingEntity> buildEntities = buildingRepository.findByNameContainingAndWardContaining(buildingSearchBuilder.getName(), "Phuong 7");
		
//		BuildingEntity buildEntity = buildingRepository.findById(1L).get();
		
		List<BuildingDTO> result = new ArrayList<>();
		
		for (BuildingEntity item : buildEntities) {
			BuildingDTO building = buildingConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;
	}


	@Autowired
	private ModelMapper molderMapper;
	@Override
	public void createBuilding(BuildingRequestDTO buildingDTO) {
		// TODO Auto-generated method stub
		
		BuildingEntity buildingEntity = molderMapper.map(buildingDTO, BuildingEntity.class);
		buildingRepository.save(buildingEntity);
		
	}
	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		buildingRepository.deleteByIdIn(ids);
		
	}
}
