package com.javaweb.converter;

import java.util.stream.Collectors;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper molderMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity item) {
        BuildingDTO building = molderMapper.map(item, BuildingDTO.class);

//        building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());
//
//
//        String rentAreas = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
//        building.setRentArea(rentAreas);
        return building;
    }

    public BuildingSearchResponse toBuildingReponse(BuildingEntity item) {
        BuildingSearchResponse buildingSearchResponse = molderMapper.map(item, BuildingSearchResponse.class);

        districtCode districtEnum = districtCode.fromString(item.getDistrict());

        buildingSearchResponse.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEnum.getDistrictName());

        return buildingSearchResponse;
    }
}
