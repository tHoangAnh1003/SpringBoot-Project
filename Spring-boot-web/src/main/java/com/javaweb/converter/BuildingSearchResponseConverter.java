package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity items) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(items, BuildingSearchResponse.class);

        buildingSearchResponse.setAddress(items.getStreet() + ", " + items.getWard() + ", " + districtCode.district().get(items.getDistrict()));
        List<RentEntity> rentAreas = items.getRentEntities();
        String result = rentAreas.stream().map(item -> item.getValue()).collect(Collectors.joining(", "));

        buildingSearchResponse.setRentArea(result);
        return buildingSearchResponse;
    }
}
