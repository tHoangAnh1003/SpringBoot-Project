package com.javaweb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.reponsitory.DistrictRepository;
import com.javaweb.reponsitory.RentRepository;
import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.reponsitory.entity.DistrictEntity;
import com.javaweb.reponsitory.entity.RentEntity;

@Component
public class BuildingConverter {
	
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentRepository rentRepository;
	@Autowired
	private ModelMapper molderMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = molderMapper.map(item, BuildingDTO.class);
		
//		DistrictEntity district = districtRepository.findDistrict(item.getDistrictId());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());
		
//		List<RentEntity> rent = rentRepository.findRent(item.getId());
//		List<String> rentVal = new ArrayList<>();
//		for (RentEntity index : rent) {
//			rentVal.add(index.getValue().toString());
//		}
//		String rentValue = String.join(", ", rentVal);
//		building.setRentArea(rentValue);
		
		String rentAreas = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(rentAreas);
		return building;
	}
}
