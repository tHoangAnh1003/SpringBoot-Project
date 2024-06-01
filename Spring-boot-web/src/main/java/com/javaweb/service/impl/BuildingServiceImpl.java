package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentRepository rentRepository;
    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildings = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildings) {
            BuildingSearchResponse building = buildingSearchResponseConverter.toBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }

    @Override
    public void createAndUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity building = buildingDTOConverter.toBuildingEntity(buildingDTO);
        buildingRepository.save(building);
        if (buildingDTO.getId() != null) {
            rentRepository.deleteByBuildingEntityId(building.getId());
        }
        List<RentEntity> rentAreaEntities = rentAreaService.createRentArea(building, buildingDTO);
        for (RentEntity it : rentAreaEntities) {
            rentRepository.save(it);
        }
    }


    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = new ArrayList<>();
        for (AssignmentBuildingEntity it : building.getAssignmentBuildingEntity()) {
            staffAssignment.add(it.getUserEntity());
        }
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("SUCCESS");
        return responseDTO;
    }

    @Override
    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(buildingEntity);
        return buildingDTO;
    }

}
