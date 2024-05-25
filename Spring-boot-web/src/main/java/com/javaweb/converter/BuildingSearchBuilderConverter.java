package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {

    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> ob, List<String> typeCode) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(ob, "name", String.class))
                .setFloorArea(MapUtils.getObject(ob, "floorArea", Long.class))
                .setWard(MapUtils.getObject(ob, "ward", String.class))
                .setStreet(MapUtils.getObject(ob, "street", String.class))
                .setDistrictId(MapUtils.getObject(ob, "districtId", Long.class))
                .setNumberOfBasement(MapUtils.getObject(ob, "numberOfBasement", Long.class))
                .setTypeCode(typeCode)
                .setManagerName(MapUtils.getObject(ob, "managerName", String.class))
                .setManagerPhoneNumber(MapUtils.getObject(ob, "managerPhoneNumber", String.class))
                .setRentPriceFrom(MapUtils.getObject(ob, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtils.getObject(ob, "rentPriceTo", Long.class))
                .setAreaFrom(MapUtils.getObject(ob, "areaFrom", Long.class))
                .setAreaTo(MapUtils.getObject(ob, "areaTo", Long.class))
                .setStaffId(MapUtils.getObject(ob, "staffId", Long.class))
                .build();

        return builder;
    }
}
