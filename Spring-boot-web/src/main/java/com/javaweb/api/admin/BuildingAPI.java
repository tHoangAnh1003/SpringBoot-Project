package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.javaweb.service.BuildingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        // code from Project-2
        buildingService.addOrUpdateBuilding(buildingDTO);
        return "Add Or Update Building";
    }

    @DeleteMapping("/{ids}")
    public String deleteBuilding(@RequestBody List<Long> ids) {
        // code SQL
        buildingService.delete(ids);
        return "Delete Building";
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loafStaff(@PathVariable("id") Long id) {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<StaffResponseDTO> staffAssignment = userEntities.stream().map(user -> {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(user.getId());
            staffResponseDTO.setFullName(user.getFullName());
            staffResponseDTO.setChecked(user.getId().equals(id) ? "checked" : "unchecked");
            return staffResponseDTO;
        }).collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffAssignment);
        responseDTO.setMessage("Success");

        return responseDTO;
    }

    @PutMapping
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        // code
        assignmentBuildingService.updateAssignment(assignmentBuildingDTO);
    }

}
