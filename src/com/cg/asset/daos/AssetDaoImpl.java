package com.cg.asset.daos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.asset.dtos.*;
import com.cg.asset.exception.*;

@Repository("assetDao")
public class AssetDaoImpl implements IAssetDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Asset> getAssetDetailsListAdmin() throws AssetException {
		Query query = manager.createNamedQuery("qryAllAssets",Asset.class);
		return query.getResultList();
	}

	
	@Override
	public User getUserDetails(String userName) throws AssetException {

		System.out.println(userName);
		Query query = manager.createNamedQuery ("qryUserOnUsername",User.class);
		query.setParameter("uname",userName);
		return (User) query.getSingleResult();
		
	}

	@Override
	public Asset updateAssetAdd(Asset assets)throws AssetException {
		
		manager.merge(assets);
	
		return assets;
	}


	@Override
	public List<Request> getRequestsPendingList() throws AssetException {
		
		Query query = manager.createNamedQuery("qryRequeststatus",Request.class);
        query.setParameter("status", "Pending");
		return query.getResultList();
	}


	@Override
	public Asset getAssetDetails(int assetId) throws AssetException {
		Query query=manager.createNamedQuery("qryAssetOnId",Asset.class);
		query.setParameter("assetno", assetId);
		return (Asset) query.getSingleResult();
		
	}


	@Override
	public Asset addNewAsset(Asset asset) throws AssetException {
		manager.persist(asset);
		return asset;
	}

	

	@Override
	public Request rejectRequest(int requestId) throws AssetException {
	
		Request req= getRequestDetails(requestId);
		req.setStatus("Denied");
		return manager.merge(req);
	
	}

	public Request getRequestDetails(int requestId) throws AssetException  {
		
		Request request = manager.find(Request.class, requestId);
		return request;
		
	}
									
																						
	@Override
	public boolean approveRequest(int requestId) throws AssetException {
		Request request= getRequestDetails(requestId);
		int assetId = request.getAsset().getAssetId();
		System.out.println("AssetId for approving request  "+assetId);
		Asset asset=getAssetDetails(assetId);
		request.setStatus("Approved");
		request = updateRequestStatus(request);
		if(request.getStatus().equals("Approved")){
			System.out.println("Request status updated successfully to Approved");
			int quantity = asset.getQuantity();
			System.out.println("Asset Quantity before approval  "+quantity);
			asset.setQuantity(quantity-1);
			System.out.println("Asset quantity to update "+asset.getQuantity());
			if(quantity == 1){
				asset.setStatus("Not Available");
			}
			Asset updatedAsset = updateQuantityAfterApproval(asset);
			int newQuantity = updatedAsset.getQuantity();
				if(newQuantity < quantity){
				 System.out.println("Quantity updated successfully after request approval");	
 
				 AssetAllocation newAssetallocation = new AssetAllocation();
				
			
				 int requestForDays = request.getRequestForDays();			 
				 System.out.println("request date  "+request.getRequestDate());
			 
				 Date requestDate = request.getRequestDate();
				 System.out.println("request date  before adding in java.sql format  "+requestDate);
				 
				 newAssetallocation.setAssetId(updatedAsset);
				 newAssetallocation.setEmpNo(request.getEmp());
				 manager.detach(request);
				 System.out.println(newAssetallocation);
				 
				 Date requestDateforAdditionOperation = requestDate;
				 java.util.Date requestDateUtilFormat = requestDateforAdditionOperation;
				 System.out.println("request date  before adding  in java.util format  "+requestDateUtilFormat);
				 
				 long secondsToAdd = requestForDays;
				 secondsToAdd *= (1000*60*60*24);
				 
				 requestDateUtilFormat.setTime(requestDateUtilFormat.getTime()+secondsToAdd);
				 System.out.println("request date  after adding  in java.util format  "+requestDateUtilFormat);
				 
				 System.out.println(newAssetallocation);
				 
				 Date releaseDate = (Date) requestDateUtilFormat;
				 System.out.println("release date  after adding  in java.sql format  "+releaseDate);
				 
				 System.out.println(newAssetallocation);
				 
				 
				 newAssetallocation.setReleaseDate(releaseDate);
				 
				 
				 Request req=getRequestDetails(requestId);
				 
				 newAssetallocation.setAllocationDate(req.getRequestDate());
				 req.setStatus("Approved");
				 System.out.println(newAssetallocation);
				 insertIntoAssetAllocation(newAssetallocation);
				 System.out.println("Asset Allocated successfully");
				return true;
				}
		}
		return false;
	}
	
	
	private Request updateRequestStatus(Request request){
		
		Request updatedRequest = manager.merge(request);
		return updatedRequest;
	}
	
	
	private Asset updateQuantityAfterApproval(Asset asset){			
		
		Asset updatedAsset = manager.merge(asset);
		return updatedAsset;
	}

	
	public void insertIntoAssetAllocation(AssetAllocation assetAllo){
		System.out.println("assetAllo  "+assetAllo);
		
		manager.merge(assetAllo);
		
	}


	@Override
	public boolean removeAsset(Asset asset) throws AssetException {
		
		Asset assetresponse=manager.find(Asset.class,asset.getAssetId());
		manager.remove(assetresponse);
		return true;
		
	}


	@Override
	public List<Integer> getAssetListAllocated() throws AssetException {
		Query query = manager.createNamedQuery("assetListAllocated",AssetAllocation.class);
		return query.getResultList();
		}

	
	@Override
	public List<Employee> getAvailableEmployees(int mgrId) throws AssetException {
		
		Query query = manager.createNamedQuery("qryAvailableEmployees",Employee.class);
		query.setParameter("mgrId",mgrId);
		return  query.getResultList();
		
	}


	@Override
	public List<Asset> getAvailableAssetsDetails() throws AssetException {
		System.out.println("yy");
		Query query = manager.createNamedQuery("qryAvailableAssetsDetails",Asset.class);
		List<Asset> availAssetDetails= query.getResultList();
		return availAssetDetails;
	}
	
	@Override
	public int addRequest(Request req) throws AssetException {
		req.setEmp(manager.find(Employee.class, req.getEmp().getEmpno()));
		req.setAsset(manager.find(Asset.class, req.getAsset().getAssetId()));	
		manager.persist(req);
		return 0;
	}


	@Override
	public List<Request> showAllRequests(int mgrId) {
		Query qry=manager.createNamedQuery("qryListAvailRequests",Request.class);	
		qry.setParameter("mgrId", mgrId);		
		return qry.getResultList();
	}
	@Override
	public List<AssetAllocation> getAssetAlloc() throws AssetException {
		Query query = manager.createNamedQuery("assetListAllocated",AssetAllocation.class);
		return query.getResultList();
	}
	
}
