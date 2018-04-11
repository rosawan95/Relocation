package com.cg.asset.controllers;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.asset.dtos.*;
import com.cg.asset.exception.*;
import com.cg.asset.dtos.Asset;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;
import com.cg.asset.services.*;

@Controller
public class AssetController {

	private IAssetServices services;

	@Resource(name="assetService")
	public void setAssetServices(IAssetServices services){
		this.services = services;
	}
	
	
	@RequestMapping("login.do")
	public ModelAndView getEmpAddFormPage(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping("authenticate.do")
	public ModelAndView submitEmpAddForm(
			HttpSession session, HttpServletRequest request ,
										@RequestParam("userName") String uname,
										@RequestParam("password") String password){
		ModelAndView model =new ModelAndView();       //@ModelAttribute is used as object comes from form. 
		session = request.getSession(true);
		
		List<Integer> myList = new ArrayList<Integer>();
		
			System.out.println("username  = "+uname);
			System.out.println("password  = "+password);
			
			try {
				myList= services.authenticate(uname, password);
				System.out.println("usertype = "+myList);
				int userType = myList.get(0);
				if (userType == 1)
				{	
					session.setAttribute("userName", uname);
					System.out.println("Admin authenticated successfully");
					model.setViewName("adminHomePage");		
					//model.addObject("userName",uname);
				}
				else if(userType == 2)
				{	
					System.out.println("Manager authenticated successfully");
					
					int mgrId = myList.get(1);
					System.out.println(" EmployeeId of manager is = "+mgrId);
					model.setViewName("managerHomePage");
					session.setAttribute("userName", uname);
					session.setAttribute("mgrId", mgrId);
					//model.addObject("empId",empId);
					//model.addObject("userName",uname);
					
				}else{
					model.setViewName("login");
					model.addObject("errorMsg","Incorrect UserName OR Password");
				}
			} catch (AssetException e) {
				System.out.println("Login Failed. Incorrect UserName");
				model.setViewName("login");
				model.addObject("errorMsg","Login Failed. Incorrect UserName");
			}
			return model;
	}
	
	
	
	@RequestMapping("showAssets.do")
	public ModelAndView getshowAssetsPage(){
		
		ModelAndView model =null;
		try {
			 model = new ModelAndView("showAssets");
			List<Asset> assetList = services.getAssetDetailsListAdmin();
			model.addObject("asset", assetList);
		
		} catch (AssetException e) {
			model = new ModelAndView("error1");
			model.addObject("errorMsg", e);
			
		}
		return model;
	}

	
	@RequestMapping("showPendingRequests.do") 
	public ModelAndView showPendingRequests(){
		
		ModelAndView model = new ModelAndView("pendingRequestList");
		try {
			List<Request> pendingRequestList = services.getRequestsPendingList();
			System.out.println("here");
			//System.out.println(pendingRequestList);	
			model.addObject("requests", pendingRequestList);
			
		} catch (AssetException e) {
			e.printStackTrace();
		
		}
		return model;
	}

	
	@RequestMapping("addNewAsset.do")
	public ModelAndView addAsset() {

		ModelAndView model = new ModelAndView("addAssetForm");
		model.addObject("asset",new Asset());
		return model;
	}


	@RequestMapping("submitAssets.do")
	public ModelAndView ast(@ModelAttribute("asset") @Valid Asset asset1,
			BindingResult result) throws AssetException {
		
		ModelAndView model = new ModelAndView();
	
		if(result.hasErrors()) {
		model.setViewName("addAssetForm");
		model.addObject("asset",asset1);
		return model;
		}
		
		System.out.println(asset1);
		Asset ast=services.addNewAsset(asset1);
		System.out.println(ast);
		model.setViewName("showAssets");
		try {
			
			List<Asset> assetList = services.getAssetDetailsListAdmin();
			model.addObject("asset", assetList);
		
		} catch (AssetException e) {
			e.printStackTrace();
		}
	
		return model;
	}
	
	
	@RequestMapping("/adminModify.do")
    public ModelAndView updateform(@RequestParam("id") int assetId){
		System.out.println("inside update.do");
		ModelAndView model=null;
		try {
			Asset asset = services.getAssetDetails(assetId);
			System.out.println(asset);
			
			model = new ModelAndView("modifyAssetForm");
			model.addObject("asset",asset);
		} catch (AssetException e) {
			model = new ModelAndView("error");
			model.addObject("errMsg","Record update failed" +e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping("/adminUpdateConfirm.do")
	public String submitupdateform(@ModelAttribute("asset")  Asset asset,
	@RequestParam ("id")int assetId,@RequestParam("plusquantity")int plusQuantity,Model model) throws AssetException{	
		
		
		System.out.println(asset);
		int q=asset.getQuantity();
		int q1=q+plusQuantity;
		asset.setQuantity(q1);
		asset.setStatus("Available");
        Asset assetResponse =services.updateAssetAdd(asset);
        System.out.println(assetResponse);
		
        try {
			List<Asset> assetList = services.getAssetDetailsListAdmin();
			model.addAttribute("asset",assetList);

		} catch (AssetException e) {
			e.printStackTrace();
		}

		return "showAssets";
	}
	
	@RequestMapping("createAssetRequestData.do")
	public ModelAndView createAssetRequestData(HttpSession session, HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		List<Employee> availEmpsList = null;
		List<Asset> availAssetsDetail = null;
		
		session = request.getSession(false);
		int mgrId= (int) session.getAttribute("mgrId");
		
		try {
			availEmpsList = services.getAvailableEmployees(mgrId);
		
			Map<Integer, String> empIdAndNameMap=  new HashMap<Integer, String>();
			for(Employee emp:availEmpsList){
				empIdAndNameMap.put(emp.getEmpno(), emp.getEname());			
			}
			
			availAssetsDetail= services.getAvailableAssetsDetails();
			Map<Integer, String> assetNameDetailMap=  new HashMap<Integer, String>();
			for(Asset asset:availAssetsDetail){
				String assetDesc=asset.getAssetName()+", "+asset.getAssetDesc();
				assetNameDetailMap.put(asset.getAssetId(), assetDesc);
			}

			//session.setAttribute("availableEmps", assetNameDetailMap);
			//session.setAttribute("availAssetsDetail", empIdAndNameMap);
			
			//model.addObject("availableEmps", employees);
			//model.addObject("availAssetsDetail", availAssetsDetail );
			model.addObject("assetMap", assetNameDetailMap);
			model.addObject("emplMap", empIdAndNameMap);
			model.addObject("request", new Request()); 
			model.setViewName("assetRequestForm");
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return model;		
	}
	
	
	@RequestMapping("getManagerHome.do")
	public ModelAndView showManagerHome(){
		ModelAndView model = new ModelAndView("managerHomePage");	
		return model;
	}
	
	
	@RequestMapping("insertRequestData.do")
	public ModelAndView insertAssetRequestData(@ModelAttribute("request")Request req,
			HttpSession session,HttpServletRequest request
			){
		ModelAndView modView= null;
		
		
		try {
			System.out.println(req); 
			String reqStrDate= req.getRequestDate().toString();
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(reqStrDate);
			java.sql.Date reqSQLDate = new java.sql.Date(date.getTime());
			System.out.println("SQL DAte: "+reqSQLDate);
			
			boolean validDate= services.isDateValid(reqSQLDate);
			System.out.println(validDate);
			if(validDate){
				
				services.addRequest(req);
				modView= new ModelAndView("successRequest");
				modView.addObject("empId", req.getEmp().getEmpno());
				modView.addObject("empName", req.getEmp().getEname());
				modView.addObject("reqId", req.getRequestId());
				
			}else{
				modView= new ModelAndView("assetRequestForm");
			
				System.out.println("invalid date");
				String message = "Date should be greater than todays date";
				modView.addObject("errorMsg", message);	
				
				List<Employee> availEmpsList = null;
				List<Asset> availAssetsDetail = null;
				
				session = request.getSession(false);
				int mgrId= (int) session.getAttribute("mgrId");
				
				
					availEmpsList = services.getAvailableEmployees(mgrId);
				
					Map<Integer, String> empIdAndNameMap=  new HashMap<Integer, String>();
					for(Employee emp:availEmpsList){
						empIdAndNameMap.put(emp.getEmpno(), emp.getEname());			
					}
					
					availAssetsDetail= services.getAvailableAssetsDetails();
					Map<Integer, String> assetNameDetailMap=  new HashMap<Integer, String>();
					for(Asset asset:availAssetsDetail){
						String assetDesc=asset.getAssetName()+", "+asset.getAssetDesc();
						assetNameDetailMap.put(asset.getAssetId(), assetDesc);
					}

					//session.setAttribute("availableEmps", assetNameDetailMap);
					//session.setAttribute("availAssetsDetail", empIdAndNameMap);
					
					//model.addObject("availableEmps", employees);
					//model.addObject("availAssetsDetail", availAssetsDetail );
					modView.addObject("assetMap", assetNameDetailMap);
					modView.addObject("emplMap", empIdAndNameMap);
					modView.addObject("request",req );	
					
		}
		}catch (AssetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(req);
			
			return modView;
				
	}
	
	@RequestMapping("showAllRequests.do")
	public ModelAndView showRequest(HttpSession session, HttpServletRequest request){
		session=request.getSession(false);
		
		ModelAndView model = new ModelAndView("showAllRequests");
		
			List<Request> reqList= new ArrayList<Request>();
			int mgrId=(int) session.getAttribute("mgrId");
			reqList=services.showAllRequests(mgrId);
			System.out.println("check req list");
			System.out.println(reqList);
			model.addObject("data", reqList);
		
		return model;
	}

	
	@RequestMapping("rejectRequest.do")
	public ModelAndView rejectRequest(@RequestParam("id") int requestId){
		ModelAndView model = null;
		System.out.println("Request Id  = "+requestId);
		
		try {
			Request req = services.rejectRequest(requestId);
			System.out.println(req);
			if(req!=null){
				System.out.println("Rejected Successfully");
				model = new ModelAndView("pendingRequestList");
				List<Request> pendingRequestList = services.getRequestsPendingList();
				System.out.println(pendingRequestList);
				model.addObject("requests", pendingRequestList);
	
			}else
			{
				System.out.println("Failed to reject request");
			}
			
		} catch (AssetException e) {
			e.printStackTrace();
		}
	
		return model;

	}
	
	
	@RequestMapping("/delete.do")
	public ModelAndView removeData(@RequestParam ("id")int assetId)
	{	
		ModelAndView model=new ModelAndView("showAssets");
		
		try {
			
			Asset asset=services.getAssetDetails(assetId);
			boolean boo=services.removeAsset(asset);
			if (boo == true) {
				System.out.println("Asset deleted successfully");
				System.out.println(asset);
				model.setViewName("showAssets");									
				List<Asset> assetList = services.getAssetDetailsListAdmin();
				model.addObject("asset",assetList);
									
			} else {		
				System.out.println("deletion failed");
			}
		}catch(AssetException e) {
			e.printStackTrace();
		}catch(Exception e1){

			try {
					List<Asset> assetList = services.getAssetDetailsListAdmin();
					model.addObject("asset",assetList);
					model.addObject("errorMsg","Cannot Delete This Asset");
					return model;
				} catch (AssetException e) {			
					e.printStackTrace();
				}		
		}	
		return model;	
	}
	
	
	@RequestMapping("approveRequest.do")
	public ModelAndView approveRequest(@RequestParam("id") int requestId){
	
		try {
			boolean boo = services.approveRequest(requestId);
			if(boo == true){
				System.out.println("Request Approved successfully");
			}
			else{
				System.out.println("Request Approval Failed");
			}
		} catch (AssetException e1) {
			e1.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView("pendingRequestList");
		try {
			List<Request> pendingRequestList = services.getRequestsPendingList();
			System.out.println("here");
			System.out.println(pendingRequestList);
			model.addObject("requests", pendingRequestList);
			
		} catch (AssetException e) {
			e.printStackTrace();
		
		}
		return model;
	}
	
	
	@RequestMapping("showReport.do")
	public ModelAndView getAssetReport()  {
		ModelAndView mod=new ModelAndView("report");
		//mod.addObject("assets",new AssetAllocation());
		List<AssetAllocation> allocatedAssets=null;
		List<Asset> availAssetsDetail = null;
		try {
			allocatedAssets = services.getAssetAlloc();
			availAssetsDetail= services.getAssetDetailsListAdmin();
			
		} catch (AssetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
			mod.addObject("allocatedAssets",allocatedAssets);
			mod.addObject("availAssetsDetail",availAssetsDetail);
			System.out.println(allocatedAssets);
			System.out.println(availAssetsDetail);

		
		return mod;
		
	}
	
	@RequestMapping("generateReport.do")
	public ModelAndView getAssetRep()  {
		ModelAndView mod=new ModelAndView("assetreport");
		//mod.addObject("assets",new AssetAllocation());
		List<AssetAllocation> allocatedAssets=null;
		List<Asset> availAssetsDetail = null;
		try {
			allocatedAssets = services.getAssetAlloc();
			availAssetsDetail= services.getAssetDetailsListAdmin();
			
		} catch (AssetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
			mod.addObject("allocatedAssets",allocatedAssets);
			mod.addObject("availAssetsDetail",availAssetsDetail);
			System.out.println(allocatedAssets);
			System.out.println(availAssetsDetail);

		
		return mod;
		
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(){
		
		ModelAndView model = new ModelAndView("login");	
		return model;
	}
	
	
	@RequestMapping("adminHomePage.do")
	public ModelAndView getAdminHomePage(){
		
		ModelAndView model = new ModelAndView("adminHomePage");	
		return model;
	}
	
}
