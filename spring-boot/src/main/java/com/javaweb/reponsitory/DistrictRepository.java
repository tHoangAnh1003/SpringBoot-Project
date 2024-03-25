package com.javaweb.reponsitory;

import com.javaweb.reponsitory.entity.DistrictEntity;

public interface DistrictRepository {
	public DistrictEntity findDistrict(String districtId);
}
