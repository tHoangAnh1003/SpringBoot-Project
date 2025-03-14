package com.javaweb.reponsitory.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.javaweb.reponsitory.BuildingRepository;
import com.javaweb.reponsitory.entity.BuildingEntity;
import com.javaweb.untils.ConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
	@Override
	public List<BuildingEntity> findAll(Map<Object, Object> ob, String[] typeCode) {
		StringBuilder sql = new StringBuilder("SELECT * FROM Building ");
		
		sql.append(" left join rentarea on building.id = rentarea.buildingid");
		
		sql.append(" left join renttype on renttype.id = rentarea.id");
		
		if (ob.get("staffid") != null) {
			sql.append(" left join assignmentbuilding on assignmentbuilding.buildingid = building.id");
		}
		
		if (ob.get("district") != null && !ob.get("district").equals("")) {
			sql.append(" join district on district.id = building.districtid");
		}
		
		StringBuilder where = new StringBuilder(" where 1 = 1 ");

		if (ob.get("name") != null && !ob.get("name").equals("")) {
			where.append(" AND name LIKE '%" + ob.get("name") + "%' ");
		}
		
		if (ob.get("floorarea") != null) {
			where.append(" AND floorarea = " + ob.get("floorarea"));
		}
		
		if (ob.get("ward") != null && !ob.get("ward").equals("")) {
			where.append(" AND ward like '%" + ob.get("ward") + "%' ");
		}
		
		if (ob.get("street") != null && !ob.get("street").equals("")) {
			where.append(" AND street like '%" + ob.get("street") + "%'");
		}
		
		if (ob.get("level") != null && !ob.get("level").equals("")) {
			where.append(" AND level like " + ob.get("level"));
		}
		
		if (ob.get("areafrom") != null) {
			where.append(" AND rentarea.value >= " + ob.get("areafrom"));
		}
		
		if (ob.get("areato") != null) {
			where.append(" AND rentarea.value <= " + ob.get("areato"));
		}
		
		if (ob.get("pricefrom") != null) {
			where.append(" AND rentprice >= " + ob.get("pricefrom"));
		}
		
		if (ob.get("priceto") != null) {
			where.append(" AND rentprice <= " + ob.get("priceto"));
		}

		if (ob.get("managername") != null && !ob.get("managername").equals("")) {
			where.append(" AND managername like '%" + ob.get("managername") + "%'");
		}
		
		if (ob.get("district") != null && !ob.get("district").equals("")) {
			where .append(" AND district.code like '%" + ob.get("district") + "%'");
		}

		if (ob.get("managerphonenumber") != null && !ob.get("managerphonenumber").equals("")) {
			where.append(" AND managerphonenumber like '%" + ob.get("managerphonenumber") + "%'");
		}
		
		if (ob.get("numberofbasement") != null && !ob.get("numberofbasement").equals("")) {
			where.append(" AND numberofbasement = " + ob.get("numberofbasement"));
		}
		
		if (ob.get("direction") != null && !ob.get("direction").equals("")) {
			where.append(" AND direction like '%" + ob.get("direction") + "%'");
		}
		
		if (ob.get("value") != null) {
			where.append(" AND value = " + ob.get("value"));
		}

		if (ob.get("staffid") != null) {
			where.append(" AND assignmentbuilding.staffid = " + ob.get("staffid"));
		}
		
		if (ob.get("typeCode") != null) {
			where.append(" and renttype.code like '%" + ob.get("typeCode") + "%'");
		}
		
		String Where = where.toString();

		sql.append(Where);
		
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		
		try(Connection conn = ConnectionUtil.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql.toString())){
			
			boolean flag = false;
			
			BuildingEntity nowBuilding = new BuildingEntity();
			
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setDistrictId(rs.getLong("districtid"));
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
				building.setRentArea(rs.getString("value"));
				
				if (!flag) {
					nowBuilding = building;
					flag = true;
				}
				
				if (!nowBuilding.getName().equals(building.getName())) {
					result.add(nowBuilding);
					nowBuilding = building;
				} else if (nowBuilding.getRentArea() != building.getRentArea()) {
						StringBuilder rent = new StringBuilder(nowBuilding.getRentArea());
						rent.append(",");
						rent.append(building.getRentArea());
						nowBuilding.setRentArea(rent.toString());
				}
			}

			result.add(nowBuilding);
			
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
