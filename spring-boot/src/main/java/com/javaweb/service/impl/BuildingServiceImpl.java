package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.reponsitory.BuildingRepository;
import com.javaweb.reponsitory.DistrictRepository;
import com.javaweb.reponsitory.RentRepository;
import com.javaweb.reponsitory.entity.DistrictEntity;
import com.javaweb.reponsitory.entity.RentEntity;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentRepository rentRepository;
	
	public List<BuildingDTO> findAll(Map<Object, Object> ob, List<String> typeCode) {
		List<BuildingEntity> buildEntities = buildingRepository.findAll(ob, typeCode);
		List<BuildingDTO> result = new ArrayList<>();
		
		for (BuildingEntity item : buildEntities) {
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			
			DistrictEntity district = districtRepository.findDistrict(item.getDistrictId());
			building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district.getDistrict());
			building.setManagerName(item.getManagerName());
			building.setNumberOfBasement(item.getNumberOfBasement());
			building.setManagerPhoneNumber(item.getManagerPhoneNumber());
			building.setFloorArea(item.getFloorArea());
			building.setRentPrice(item.getRentPrice());
			building.setServiceFee(item.getServiceFee());
			building.setBrokerageFee(item.getBrokerageFee());
			building.setVacantArea(item.getVacantArea());
			
			List<RentEntity> rent = rentRepository.findRent(item.getId());
			List<String> rentVal = new ArrayList<>();
			for (RentEntity index : rent) {
				rentVal.add(index.getRentArea());
			}
			String rentValue = String.join(",", rentVal);
			building.setRentArea(rentValue);
			
			result.add(building);
		}
		
		return result;
	}
}
