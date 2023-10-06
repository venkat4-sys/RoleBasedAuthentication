package com.getinfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USER_TBL")
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue
	private Integer userId;
	
	@Column(name="UserName")
	private String name;
	
	private String email;
	
	private Integer age;
	
	@Column(name="password")
	private String pwd;
	
	@ManyToOne
	@JoinColumn(name="role_id_fk",referencedColumnName = "roleId")
	private RoleEntity role;
	
	private boolean active;
	

}
