package edu.ycp.cs320.groupProject.persist;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.ycp.cs320.groupProject.model.Chatroom;
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
	
	@Test
	public void testDeleteUsers() {
	
		User u = new User("student1", "ycp", false);
		Chatroom c = new Chatroom();
		Boolean userInserted = false;
		Boolean LoggedIn= false;

		userInserted = db.insertUserIntoChatroom(u, c);
		
		if (userInserted== true)
		{
			System.out.println("Book inserted; now delete it");
			
		User userDeleted = db.deleteUser(u);
		
		System.out.println("user deleted");
		
		}
		
		LoggedIn=db.Login(u);
		
		if (LoggedIn== true)
		{
			fail("User should not have logged in because user should not exist because user should have been deleted");
		}
		
		
	
	}
	
	
	
	
	

}
