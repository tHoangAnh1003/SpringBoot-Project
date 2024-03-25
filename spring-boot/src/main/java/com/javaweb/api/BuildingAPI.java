package com.javaweb.api;

import java.util.List;
import java.util.Map;

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
import com.javaweb.service.BuildingService;


@RestController
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/building")
	@ResponseBody
	public List<BuildingDTO> findBuilding(@RequestParam Map<Object, Object> ob,
			@RequestParam(value="typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(ob, typeCode);
		
		return result;
	}
	
	
	
	@PostMapping(value = "/api/building")
	public Object createBuilding(@RequestBody BuildingDTO buildingDTO) {
		validateData(buildingDTO);
		System.out.println("OK");
		return buildingDTO;
	}
	
	private void validateData(BuildingDTO buildingDTO) {
		// TODO Auto-generated method stub
		
	}

	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable Long[] ids) {
		System.out.print("OK");
	}
}
