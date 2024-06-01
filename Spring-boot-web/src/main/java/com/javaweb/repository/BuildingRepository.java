package com.javaweb.repository;

import java.util.List;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {

    List<BuildingEntity> findByNameContaining(String name);
    List<BuildingEntity> findByNameContainingAndWardContaining(String name, String ward);
    void deleteByIdIn(Long[] ids);
    BuildingDTO findBuildingById(Long id);
}
