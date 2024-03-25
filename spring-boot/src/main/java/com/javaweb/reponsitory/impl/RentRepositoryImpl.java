package com.javaweb.reponsitory.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.reponsitory.RentRepository;
import com.javaweb.reponsitory.entity.RentEntity;
import com.javaweb.untils.ConnectionUtil;

@Repository
public class RentRepositoryImpl implements RentRepository {

	public List<RentEntity> findRent(Long id) {
		StringBuilder SQL = new StringBuilder("SELECT * from rentarea ");
		
		SQL.append("where buildingid = " + id);
		
		List<RentEntity> rentValue = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(SQL.toString())){
			
			while (rs.next()) {
				RentEntity rent = new RentEntity();
				rent.setRentArea(rs.getString("value"));
				rentValue.add(rent);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		
		return rentValue;
	}

}
