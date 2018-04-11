package com.cg.asset.dtos;

import java.io.Serializable;




import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name="REQUEST")
@Table(name="REQUEST")
@SequenceGenerator(name="seqRequestId", sequenceName="request_id_seq", allocationSize=1, initialValue=1100 )

@NamedQueries({
	
	@NamedQuery(name = "qryRequeststatus" , query = "select r from REQUEST r where status is :status"),
	@NamedQuery(name = "qryAllreq" , query = "SELECT r FROM REQUEST r "),	
	@NamedQuery(name = "qryListAvailRequests" , query = "SELECT r FROM REQUEST r"
													+ " WHERE r.emp.empno IN ("
													+ " SELECT r.emp.empno FROM r.emp "
													+ "WHERE r.emp.mgr =:mgrId) order by r.requestId ")
})

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8795988249935917481L;
	
	private int requestId;
	private Employee emp;
	private Asset asset;
	private Date requestDate;
	private int requestForDays;
	private String status;

	@Id
	@Column(name="requestid")
	@GeneratedValue(generator="seqRequestId", strategy=GenerationType.SEQUENCE)
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empno")
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="assetid")
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	@Column(name="requestdate")
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name="requestfordays")
	public int getRequestForDays() {
		return requestForDays;
	}

	public void setRequestForDays(int requestForDays) {
		this.requestForDays = requestForDays;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", emp=" + emp + ", asset="
				+ asset + ", requestDate=" + requestDate + ", requestForDays="
				+ requestForDays + ", status=" + status + "]";
	}	
}
