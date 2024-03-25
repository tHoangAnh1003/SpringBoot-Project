package com.javaweb.reponsitory;

import java.util.List;

import com.javaweb.reponsitory.entity.RentEntity;

public interface RentRepository {
	List<RentEntity> findRent(Long id);
}
