package com.cg.asset.dtos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity(name="ASSET")
@Table(name="asset")
@NamedQueries({
	@NamedQuery(name = "qryAllAssets" , query = "select a from ASSET a order by a.assetId"),
	@NamedQuery(name = "qryAssetIdFromAssetName" , query = "SELECT a.assetId FROM ASSET a WHERE a.assetName  =:assetName"),
	@NamedQuery(name = "qryAssetOnId" , query = "select a from ASSET a where assetId is :assetno"),
	@NamedQuery(name = "qryAvailableAssetsDetails" , query = "SELECT a FROM ASSET a WHERE a.quantity  > 0")
	
})

@SequenceGenerator( name="asset_id_seq", sequenceName="asset_id_seq", allocationSize=1 , initialValue=1010)
public class Asset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6993323097069830605L;

	private int assetId;
	private String assetDesc;
	private String assetName;	
	private int quantity;
	private String status;

	List<Request> req;
	List<AssetAllocation> assetAllo;
	
	@OneToMany(mappedBy="assetId")
	public List<AssetAllocation> getAssetAllo() {
		return assetAllo;
	}
	public void setAssetAllo(List<AssetAllocation> assetAllo) {
		this.assetAllo = assetAllo;
	}
	

	@OneToMany(mappedBy="asset")
	public List<Request> getReq() {
		return req;
	}
	public void setReq(List<Request> req) {
		this.req = req;
	}
	
	
	
	@Id
	@Column(name="assetid")
	@GeneratedValue(generator = "asset_id_seq", strategy=GenerationType.SEQUENCE)
	public int getAssetId() {
		return assetId;
	}	
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	@Column(name="assetdes")
	//@Pattern(regexp = "[a-zA-Z][0-9]",message="Invalid Description!")
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}
	
	@Column(name="assetname")
	//@Pattern(regexp = "[a-zA-Z]{3,20}",message="Invalid Name!")
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	
	@Column(name="quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		return "Asset [assetId=" + assetId + ", assetDesc=" + assetDesc
				+ ", assetName=" + assetName + ", quantity=" + quantity
				+ ", status=" + status + "]";
	}
	
	


}
