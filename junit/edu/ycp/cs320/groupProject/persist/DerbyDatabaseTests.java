package edu.ycp.cs320.groupProject.persist;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert.*;


import edu.ycp.cs320.groupProject.model.User;

public class DerbyDatabaseTests {
	
	private IDatabase db = null;

	ArrayList<User>  users   = null;
	Boolean usernameFound= false;
	User u = new User("student", "ycp", false);

	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
		
		
	}
	
	
	
	

}
