package com.cg.asset.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.asset.exception.AssetException;
import com.cg.asset.services.AssetServiceImpl;
import com.cg.asset.services.IAssetService;

public class LoginTest {

	static IAssetService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new AssetServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testAuthenticate01() {
		try {
			assertEquals(1, service.authenticate("Rohit", "rosawan"));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAuthenticate02() {
		try {
			assertEquals(2, service.authenticate("Chintan", "cmistr"));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAuthenticate03() {
		try {
			assertEquals(0, service.authenticate("Rohit", "roo"));
		} catch (AssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
