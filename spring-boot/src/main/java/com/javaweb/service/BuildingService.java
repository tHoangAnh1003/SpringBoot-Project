package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> ob, List<String> typeCode);
}
