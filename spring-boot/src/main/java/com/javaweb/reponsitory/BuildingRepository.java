package com.javaweb.reponsitory;

import java.util.List;
import java.util.Map;

import com.javaweb.reponsitory.entity.BuildingEntity;

public interface BuildingRepository {
	void delete(Long[] ids);

	List<BuildingEntity> findAll(Map<Object, Object> ob, List<String> typeCode);
}
