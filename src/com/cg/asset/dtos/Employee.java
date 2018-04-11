package com.cg.asset.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="EMPLOYEE")
@Table(name="Employee")
@NamedQueries({

	@NamedQuery(name = "qryAvailableEmployees" , query = "SELECT e FROM EMPLOYEE e WHERE e.mgr =:mgrId"),	
	@NamedQuery(name = "qryEnoFromEname" , query = "SELECT e FROM EMPLOYEE e WHERE e.ename =:empName"),			
})

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2528554242874191532L;
	
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private Department deptId;
	private List<Request> req;
	private List<AssetAllocation> assetAllo;	
	
	private User user;
	

	@Id
	@Column(name="empno")
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	@Column(name="ename")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Column(name="job")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name="mgr")
	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	
	@Column(name="hiredate")
	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}



	@OneToMany(mappedBy="emp")
	public List<Request> getReq() {
		return req;
	}
	public void setReq(List<Request> req) {
		this.req = req;
	}

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dept_id")
	public Department getDeptId() {
		return deptId;
	}


	public void setDeptId(Department deptId) {
		this.deptId = deptId;
	}


	@OneToMany(mappedBy="empNo")
	public List<AssetAllocation> getAssetAllo() {
		return assetAllo;
	}


	public void setAssetAllo(List<AssetAllocation> assetAllo) {
		this.assetAllo = assetAllo;
	}


	@OneToOne(mappedBy="empid") 
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", job=" + job
				+ ", mgr=" + mgr + ", hiredate=" + hiredate + ", deptId="
				+ deptId + "]";
	}
	
	
}
