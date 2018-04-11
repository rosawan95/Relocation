package com.cg.asset.daos;

import java.util.List;

import com.cg.asset.dtos.*;
import com.cg.asset.exception.*;

public interface IAssetDao {

	public List<Asset> getAssetDetailsListAdmin() throws AssetException;

	public User getUserDetails(String userName) throws AssetException;

	 public List<Request> getRequestsPendingList() throws AssetException;	
	 public Asset updateAssetAdd(Asset assets)throws AssetException;
	 public Asset getAssetDetails(int assetId) throws AssetException;
	 public Asset addNewAsset(Asset asset) throws AssetException;
	 public Request rejectRequest(int requestId) throws AssetException;
	 public boolean approveRequest(int requestId) throws AssetException;
	 
	 boolean removeAsset(Asset asset) throws AssetException;
	 public List<Integer> getAssetListAllocated() throws AssetException;
	 
	//To create data which is to be displayed on the asset request form
	List<Employee> getAvailableEmployees(int mgrId) throws AssetException;
	List<Asset> getAvailableAssetsDetails() throws AssetException;

	//add request
	int addRequest(Request req) throws AssetException;
		
	//show request data
	public List<Request> showAllRequests(int mgrId);
	public List<AssetAllocation> getAssetAlloc() throws AssetException;
}
