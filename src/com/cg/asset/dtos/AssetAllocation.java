package com.cg.asset.dtos;



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

@Entity(name="Asset_Allocation")
@Table(name="Asset_Allocation")
@NamedQueries({

	@NamedQuery(name = "assetListAllocated" , query = "select a from Asset_Allocation a"),
	
	})

@SequenceGenerator( name="allocation_id_seq", sequenceName="asset_allocation_id_seq", allocationSize=1 , initialValue=1)
public class AssetAllocation {

	private int allocationId;
	private Asset assetId;
	private Employee empNo;
	private Date allocationDate;
	private Date releaseDate;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="assetid")
	public Asset getAssetId() {
		return assetId;
	}

	public void setAssetId(Asset assetId) {
		this.assetId = assetId;
	}


	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empno")
	public Employee getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Employee empNo) {
		this.empNo = empNo;
	}

	@Id
	@Column(name="allocationid")
	@GeneratedValue(generator = "allocation_id_seq", strategy=GenerationType.SEQUENCE)
	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}


	@Column(name="allocation_date")
	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	@Column(name="release_date")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	@Override
	public String toString() {
		return "AssetAllocation [allocationId=" + allocationId + ", assetId="
				+ assetId + ", empNo=" + empNo + ", allocationDate="
				+ allocationDate + ", releaseDate=" + releaseDate + "]";
	}

	
	
	/*
	public AssetAllocation() {
		super();
	}

	public AssetAllocation(int allocationId, int assetId, int empNo,
			Date allocationDate, Date releaseDate) {
		super();
		this.allocationId = allocationId;
		this.assetId = assetId;
		this.empNo = empNo;
		this.allocationDate = allocationDate;
		this.releaseDate = releaseDate;
	}*/

}
