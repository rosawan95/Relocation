package com.cg.asset.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdminAddAssetTest.class, AdminRemoveAssetTest.class,
		AdminShowAllTest.class, AssetTest.class, LoginTest.class })
public class AllTests {

}
