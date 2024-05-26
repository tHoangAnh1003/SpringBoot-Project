package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.StaffEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.StaffRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper molderMapper;

    public void updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
//        Long id = assignmentBuildingDTO.getBuildingId();
//        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
//
        StaffEntity staffEntity = molderMapper.map(assignmentBuildingDTO, StaffEntity.class);

        staffRepository.save(staffEntity);

//        buildingRepository.save(building);
    }

}
