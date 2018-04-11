package com.cg.asset.tests;

import java.sql.SQLException;

import com.cg.asset.daos.AssetDaoImpl;
import com.cg.asset.daos.IAssetDao;
import com.cg.asset.dtos.User;
import com.cg.asset.exception.AssetException;

public class Testdao {

	public static void main(String[] args) {
		IAssetDao dao = new AssetDaoImpl();
		User user;
		try {
			user = dao.getUserDetails("Rohit");
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
