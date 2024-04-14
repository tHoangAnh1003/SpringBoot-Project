package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.reponsitory.custom.BuildingRepositoryCustom;
import com.javaweb.reponsitory.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
//	void delete(Long[] ids);
//
//	List<BuildingEntity> findAll(BuildingSearchBuilder builder);
	
	List<BuildingEntity> findByNameContaining(String name);
	List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);
	void deleteByIdIn(Long[] ids);
}
