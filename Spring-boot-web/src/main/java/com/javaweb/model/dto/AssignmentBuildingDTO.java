package com.javaweb.model.dto;

import com.javaweb.entity.BaseEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

public class AssignmentBuildingDTO {

    private Long buildingId;
    private List<Long> staffs;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public List<Long> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Long> staffs) {
        this.staffs = staffs;
    }
}
