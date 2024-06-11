package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void queryJoin(BuildingSearchRequest builder, StringBuilder sql) {
        Long rentAreaFrom = builder.getRentPriceFrom();
        Long rentAreaTo = builder.getRentPriceTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            sql.append(" JOIN rentarea rn ON rn.buildingid = b.id ");
        }

        Long staffId = builder.getStaffId();

        if (staffId != null) {
            sql.append(" JOIN assignmentbuilding asm ON asm.buildingid = b.id ");
        }
    }

    public void queryWhereNormal(BuildingSearchRequest builder, StringBuilder where) {
        try {
            Field[] field = BuildingSearchRequest.class.getDeclaredFields();
            for (Field items : field) {
                items.setAccessible(true);
                String fieldName = items.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = items.get(builder);
                    if (value != null) {
                        if (items.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                        } else if (items.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                        } else if (items.getType().getName().equals("java.lang.String") && value != "") {
                            where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryWhereSpecial(BuildingSearchRequest builder, StringBuilder where) {
        Long staffId = builder.getStaffId();
        if (staffId != null) {
            where.append(" AND asm.staffId = " + staffId);
        }

        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();

        if (rentAreaFrom != null || rentAreaTo != null) {

            where.append(" AND EXISTS (SELECT * FROM rentarea rn WHERE rn.buildingid = b.id ");

            if (rentAreaFrom != null) {
                where.append(" AND rn.value >= " + rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" AND rn.value <= " + rentAreaTo);
            }

            where.append(")");

        }

        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }

        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND ( ");
            String sqlJoin = typeCode.stream().map(item -> " b.type LIKE '%" + item + "%'")
                    .collect(Collectors.joining(" OR "));
            where.append(sqlJoin + " ) ");
        }
    }

    public List<BuildingEntity> findAll(BuildingSearchRequest builder) {
        StringBuilder jpql = new StringBuilder("SELECT b.* FROM building b ");

        queryJoin(builder, jpql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryWhereNormal(builder, where);
        queryWhereSpecial(builder, where);
        jpql.append(where);
        jpql.append(" GROUP BY b.id ");

        Query query = entityManager.createNativeQuery(jpql.toString(), BuildingEntity.class);

        return query.getResultList();
    }
}