package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public void createAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<AssignmentBuildingEntity> assignBuildingEntities = new ArrayList<>();
        for(Long it : assignmentBuildingDTO.getStaffs()){
            AssignmentBuildingEntity assignBuilding = new AssignmentBuildingEntity();
            UserEntity userEntity = new UserEntity();
            BuildingEntity buildingEntity = new BuildingEntity();
            userEntity.setId(it);
            buildingEntity.setId(assignmentBuildingDTO.getBuildingId());
            assignBuilding.setUserEntity(userEntity);
            assignBuilding.setBuildingEntity(buildingEntity);
            assignBuildingEntities.add(assignBuilding);
        }
        for(AssignmentBuildingEntity it : assignBuildingEntities){
            assignmentBuildingRepository.save(it);
        }

    }

}
