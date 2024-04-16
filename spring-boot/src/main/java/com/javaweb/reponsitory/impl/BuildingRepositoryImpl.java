package com.javaweb.reponsitory.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.reponsitory.BuildingRepository;
import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.untils.ConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	public void queryJoin(BuildingSearchBuilder builder, StringBuilder sql) {
		Long rentAreaFrom = builder.getRentPriceFrom();
		Long rentAreaTo = builder.getRentPriceTo();
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" JOIN rentarea rn ON rn.buildingid = b.id ");
		}
		
		Long staffId = builder.getStaffId();
		
		if (staffId != null) {
			sql.append(" JOIN assignmentbuilding asm ON asm.buildingid = b.id ");
		}
		
		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" JOIN buildingrenttype bdt ON bdt.buildingid = b.id");
			sql.append(" JOIN renttype rt ON rt.id = bdt.renttypeid");
		}
	}
	
	public void queryWhereNormal(BuildingSearchBuilder builder, StringBuilder where) {		
		try {
			Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
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
						} else if (items.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void queryWhereSpecial(BuildingSearchBuilder builder, StringBuilder where) {
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
		
		List<String> typeCode = builder.getTypeCode();
		Long rentPriceFrom = builder.getRentPriceFrom();
		Long rentPriceTo = builder.getRentPriceTo();
		if (rentPriceFrom != null) {
			where.append(" AND b.rentprice >= " + rentPriceFrom);
		}
		if (rentPriceTo != null) {
			where.append(" AND b.rentprice <= " + rentPriceTo);
		}
		
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND ( ");
			String sqlJoin = typeCode.stream().map(item -> " rt.code LIKE '%" + item + "%'")
					.collect(Collectors.joining(" OR "));
			where.append(sqlJoin + " ) ");
		}
	}
	
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, "
				+ "b.numberofbasement, b.managername, b.managerphonenumber, "
				+ "b.floorarea, b.rentprice, b.brokeragefee, b.servicefee FROM Building b ");
		
		queryJoin(builder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryWhereNormal(builder, where);
		queryWhereSpecial(builder, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");
		
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		
		try(Connection conn = ConnectionUtil.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql.toString())){
			
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();;
				building.setName(rs.getString("name"));
				building.setDistrictId(rs.getString("districtid"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setManagerName(rs.getString("managername"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setRentPrice(rs.getString("rentprice"));
				building.setServiceFee(rs.getLong("servicefee"));
				building.setBrokerageFee(rs.getLong("brokeragefee"));
				building.setVacantArea(0L);
				building.setId(rs.getLong("id"));
		
				result.add(building);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		
		return result;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}
	
}
