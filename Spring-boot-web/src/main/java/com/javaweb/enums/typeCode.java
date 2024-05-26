package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum typeCode {

    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String typeCodeName;

    typeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }

    public String getTypeCodeName() {
        return typeCodeName;
    }

    public static Map<String, String> getTypeCode() {
        Map<String, String> loadTypeCode = new LinkedHashMap<>();

        for (typeCode it : typeCode.values()) {
            loadTypeCode.put(it.toString(), it.getTypeCodeName());
        }

        return loadTypeCode;
    }
}
