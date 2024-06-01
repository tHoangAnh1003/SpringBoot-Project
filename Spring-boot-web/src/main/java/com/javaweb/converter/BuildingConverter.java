package com.javaweb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentEntity;
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
        BuildingDTO buildingDTO = molderMapper.map(item, BuildingDTO.class);

        String[] typeCodes = item.getTypeCode().split(",");

        List<String> typeCode = new ArrayList<>();
        for(String it : typeCodes){
            typeCode.add(it);
        }
        buildingDTO.setTypeCode(typeCode);

        List<RentEntity> rentAreaEntities = item.getRentEntities();
        String areaResult = rentAreaEntities.stream().map(items -> items.getValue()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(areaResult);

        return buildingDTO;
    }

    public BuildingSearchResponse toBuildingReponse(BuildingEntity item) {
        BuildingSearchResponse buildingSearchResponse = molderMapper.map(item, BuildingSearchResponse.class);

        if (item.getDistrict() != null) {
            districtCode districtEnum = districtCode.fromString(item.getDistrict());

            buildingSearchResponse.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEnum.getDistrictName());
        }

        return buildingSearchResponse;
    }
}
