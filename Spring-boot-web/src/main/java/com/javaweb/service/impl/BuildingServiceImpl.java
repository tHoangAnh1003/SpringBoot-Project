package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.service.BuildingService;
import com.javaweb.builder.BuildingSearchBuilder;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
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

    public List<BuildingSearchResponse> searchAll(BuildingSearchBuilder buildingSearchBuilder) {
//        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(ob, typeCode);

        List<BuildingEntity> buildEntities = buildingRepository.findAll(buildingSearchBuilder);

//		List<BuildingEntity> buildEntities = buildingRepository.findAll();

//		List<BuildingEntity> buildEntities = buildingRepository.findByNameContainingAndWardContaining(buildingSearchBuilder.getName(), "Phuong 7");

//		BuildingEntity buildEntity = buildingRepository.findById(1L).get();

        List<BuildingSearchResponse> result = new ArrayList<>();

        for (BuildingEntity item : buildEntities) {
            BuildingSearchResponse building = buildingConverter.toBuildingReponse(item);
            result.add(building);
        }

        return result;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = molderMapper.map(buildingDTO, BuildingEntity.class);
        buildingRepository.save(buildingEntity);
    }


    @Autowired
    private ModelMapper molderMapper;


    @Override
    public void delete(List<Long> ids) {
        // TODO Auto-generated method stub
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingDTO buildingDTO = buildingRepository.findBuildingById(id);
        return buildingDTO;
    }
}
