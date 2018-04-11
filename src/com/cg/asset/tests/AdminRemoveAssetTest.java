package com.cg.asset.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.cg.asset.daos.AssetDaoImpl;
import com.cg.asset.daos.IAssetDao;
import com.cg.asset.dtos.Asset;
import com.cg.asset.exception.AssetException;

public class AdminRemoveAssetTest {
	IAssetDao dao;
	Asset asset;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao = new AssetDaoImpl();
		asset = new Asset();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
		asset = null;
	}

	@Test
	public void testRemoveAsset() {
		try {
			assertTrue(dao.removeAsset(1013));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
