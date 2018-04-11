package com.cg.asset.dtos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity(name="user")
@Table(name="User_Master")
@NamedQueries({
@NamedQuery(name="qryUserOnUsername" , query = "select u from user u where userName is :uname"),

}) 
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	private String userPassword;
	private String userType;
	private Employee empid;
   
/*	
	CREATE TABLE User_Master(
			userid VARCHAR2(6) PRIMARY KEY,
			username VARCHAR2(15) UNIQUE,
			userpassword VARCHAR2(50),
			usertype VARCHAR2(10),
			empno NUMBER(6) references Employee(empno) UNIQUE
			);
	*/
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empno")
	public Employee getEmpid() {
		return empid;
	}

	public void setEmpid(Employee empid) {
		this.empid = empid;
	}


	@Id
	@Column(name="userid")
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="username")
	@NotEmpty(message="UserName is Mandatory")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="userpassword")
	@NotEmpty(message="Password is Mandatory")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name="usertype")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	


}
