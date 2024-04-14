package com.javaweb.reponsitory.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.reponsitory.DistrictRepository;
import com.javaweb.reponsitory.entity.DistrictEntity;
import com.javaweb.untils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {


	public DistrictEntity findDistrict(String districtId) {
		
		StringBuilder SQL = new StringBuilder("SELECT * from district ");
		
		SQL.append("where id like '%" + districtId + "%'");
		
		DistrictEntity district = new DistrictEntity();
		
		try(Connection conn = ConnectionUtil.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(SQL.toString())){
			
			while (rs.next()) {
				district.setName(rs.getString("name"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		
		return district;
	}

}
