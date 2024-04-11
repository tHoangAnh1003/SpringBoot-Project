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
import com.javaweb.untils.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	public void queryJoin(BuildingSearchBuilder builder, StringBuilder sql) {
		Long rentAreaFrom = builder.getRentPriceFrom();
		Long rentAreaTo = builder.getRentPriceTo();
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" join rentarea rn on rn.buildingid = b.id ");
		}
		
		Long staffId = builder.getStaffId();
		
		if (staffId != null) {
			sql.append(" join assignmentbuilding asm on asm.buildingid = b.id ");
		}
		
		List<String> typeCode = builder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" left join buildingrenttype bdt on bdt.buildingid = b.id");
			sql.append(" left join renttype rt on rt.id = bdt.renttypeid");
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
					if (StringUtils.checkString((String)value)) {
						if (items.getType().getName().equals("java.lang.Long")) {
							where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
						} else if (items.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
						} else if (items.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName.toLowerCase() + " like '%" + value + "%'");
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
		
		if (rentAreaFrom != null && rentAreaTo != null) {
			
			where.append(" AND EXISIS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");
			
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
		if (rentAreaTo != null) {
			where.append(" AND b.rentprice <= " + rentPriceTo);
		}
		
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND ( ");
			String sqlJoin = typeCode.stream().map(item -> " rt.code Like '%" + item + "%'")
					.collect(Collectors.joining(" OR "));
			where.append(sqlJoin + " ) ");
		}
	}
	
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder("SELECT * FROM Building b ");
		
		queryJoin(builder, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		queryWhereNormal(builder, where);
		queryWhereSpecial(builder, where);
		sql.append(where);
		sql.append(" Group by b.id ");
		
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
				building.setRentPrice(rs.getString("rentpricedescription"));
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