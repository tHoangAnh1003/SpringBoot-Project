package com.javaweb.repository;

import com.javaweb.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentRepository extends JpaRepository<RentEntity,Long> {
    void deleteByBuildingEntityIdIn(Long[] ids);

    void deleteByBuildingEntityId(Long id);
}
