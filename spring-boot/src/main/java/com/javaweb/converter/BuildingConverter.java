package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.reponsitory.entity.BuildingEntity;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper molderMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = molderMapper.map(item, BuildingDTO.class);
		
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());

		
		String rentAreas = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(rentAreas);
		return building;
	}
}
