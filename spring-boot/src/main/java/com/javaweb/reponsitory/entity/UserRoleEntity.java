package com.javaweb.reponsitory.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "user_role")
public class UserRoleEntity {

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public UserEntity getUsers() {
//		return users;
//	}
//
//	public void setUsers(UserEntity users) {
//		this.users = users;
//	}
//
//	public RoleEntity getRoles() {
//		return roles;
//	}
//
//	public void setRoles(RoleEntity roles) {
//		this.roles = roles;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn(name = "userId")
//	private UserEntity users;
//	
//	@ManyToOne
//	@JoinColumn(name = "roleId")
//	private RoleEntity roles;	
}
