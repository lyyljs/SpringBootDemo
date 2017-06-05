package com.lyyljs.demos.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lyyljs.demos.domain.enums.Gender;

@Entity
@Table(name="users")
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String passwd;
	@Column
	private Gender sex;
	
	public User(){}
	
	public User(String name, String passwd, Gender sex){
		this.name = name;
		this.passwd = passwd;
		this.sex = sex;
		this.createDate = new Date();
		this.deleted = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Gender getSex() {
		return sex;
	}
	public void setSex(Gender sex) {
		this.sex = sex;
	}
	@Override
	public String toString(){
		return "User{" + 
				"name: " + name + 
				", id: " + id + "}";
	}

}
