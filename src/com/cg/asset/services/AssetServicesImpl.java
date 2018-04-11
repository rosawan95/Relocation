package com.cg.asset.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.asset.daos.AssetDaoImpl;
import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.AssetAllocation;
import com.cg.asset.dtos.Employee;
import com.cg.asset.dtos.Request;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;

@Service("assetService")
@Transactional
public class AssetServicesImpl implements IAssetServices {

	
	AssetDaoImpl dao;

	
	@Resource(name="assetDao")
	public void setAssetDao(AssetDaoImpl dao){
		this.dao = dao;
	}


	@Override
	public List<Asset> getAssetDetailsListAdmin() throws AssetException {
		return dao.getAssetDetailsListAdmin();
	}


	@Override
	public List<Integer> authenticate(String userName, String password)
			throws AssetException {
		
		List<Integer> myList = new ArrayList<Integer>();
		
		User user = dao.getUserDetails(userName);
		String privilege = "Admin";

		System.out.println(user);

		if (password.equals(user.getUserPassword())) {
			if (privilege.equals(user.getUserType())) {
				
				myList.add(1);
				myList.add(0);
			}else{
			myList.add(2);
			int empId=user.getEmpid().getEmpno();
			System.out.println("employee id of manager logged in "+empId);
			myList.add(empId);
			}
		}
		else{
			myList.add(0);
		}
		return myList;
	
	}
	
	@Override
	public boolean isDateValid(Date reqSQLDate) {
		java.sql.Date todaysDate = new java.sql.Date(Calendar.getInstance()
				.getTime().getTime());

		System.out.println("Todays date: " + todaysDate);
		System.out.println("User entered date: " + reqSQLDate);

		if (reqSQLDate.compareTo(todaysDate) > 0) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Asset updateAssetAdd(Asset assets)throws AssetException {
		
		return dao.updateAssetAdd(assets);
	}


	@Override
	public List<Request> getRequestsPendingList() throws AssetException {
	
		return dao.getRequestsPendingList();
	}


	@Override
	public Asset getAssetDetails(int assetId) throws AssetException {
		
		return dao.getAssetDetails(assetId);
	}


	@Override
	public Asset addNewAsset(Asset asset) throws AssetException {
		return dao.addNewAsset(asset);
	}
	
	@Override
	public Request rejectRequest(int requestId) throws AssetException {		
		return dao.rejectRequest(requestId);
	}
	
	@Override
	public boolean removeAsset(Asset asset) throws AssetException {
		return dao.removeAsset(asset);
	}



	@Override
	public List<Integer> getAssetListAllocated() throws AssetException {
		return dao.getAssetListAllocated();
	}


	@Override
	public boolean approveRequest(int requestId) throws AssetException {
		return dao.approveRequest(requestId);
	}


	@Override
	public List<Employee> getAvailableEmployees(int mgrId)
			throws AssetException {
		
		return dao.getAvailableEmployees(mgrId);
	}


	@Override
	public List<Asset> getAvailableAssetsDetails() throws AssetException {
		
		return dao.getAvailableAssetsDetails();
	}


	@Override
	public int addRequest(Request req) throws AssetException {
		
		return dao.addRequest(req);
	}


	@Override
	public List<Request> showAllRequests(int mgrId) {
		
		return dao.showAllRequests(mgrId);
	}


	@Override
	public List<AssetAllocation> getAssetAlloc() throws AssetException {
		// TODO Auto-generated method stub
		return dao.getAssetAlloc();
	}
	
}