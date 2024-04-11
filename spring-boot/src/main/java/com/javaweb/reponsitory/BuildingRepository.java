package com.javaweb.reponsitory;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.reponsitory.entity.BuildingEntity;

public interface BuildingRepository {
	void delete(Long[] ids);

	List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
