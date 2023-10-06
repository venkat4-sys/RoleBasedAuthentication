package com.getinfy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ROLE_TBL")
public class RoleEntity {
	
	@Id
	private Integer roleId;
	
	private String roleName;

}
