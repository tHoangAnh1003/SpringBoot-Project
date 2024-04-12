package com.javaweb.reponsitory.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.reponsitory.BuildingRepository;
import com.javaweb.reponsitory.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		// TODO Auto-generated method stub
		String jpql = "FROM BuildingEntity b WHERE 1 = 1 ";
		Query query = entityManager.createQuery(jpql, BuildingEntity.class);
		return query.getResultList();
	}

}
