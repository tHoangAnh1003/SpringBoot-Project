package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.javaweb.service.BuildingService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        buildingService.createAndUpdateBuilding(buildingDTO);
        return "Add or Update Building";
    }

    @DeleteMapping("/{ids}")
    public String deleteBuilding(@PathVariable Long[] ids) {
        buildingService.deleteBuilding(ids);
        return "Delete Building";
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public String updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {

        assignmentBuildingRepository.deleteByBuildingEntityId(assignmentBuildingDTO.getBuildingId());
        assignmentBuildingService.createAssignmentBuilding(assignmentBuildingDTO);

        return "Update Assignment";
    }

}
