package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.BuildingRequestDTO;
import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.reponsitory.entity.DistrictEntity;
import com.javaweb.service.BuildingService;


@RestController
@Transactional
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/building")
	@ResponseBody
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> ob,
			@RequestParam(value="typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(ob, typeCode);
		
		return result;
	}
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ModelMapper molderMapper;
	
	@PostMapping(value = "/api/building")
	public void createBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
		BuildingEntity buildingEntity = molderMapper.map(buildingDTO, BuildingEntity.class);
		
		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		
		entityManager.merge(buildingEntity);
	}

	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
//		System.out.print("OK");
	}
}
