package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchRequest builder);
}
