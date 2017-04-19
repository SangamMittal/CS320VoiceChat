package edu.ycp.cs320.groupProject.persist;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class DerbyDatabaseTests {
	
	private IDatabase db = null;
	private static User user = new User();
	private static Post post = new Post();
	
	ArrayList<User>  users   = null;
	Boolean usernameFound= false;
	User u = new User("student", "ycp", false);

	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user.setUsername("TestMan");
		user.setPassword("tester");
		post.setText("Test this thing");
		post.setSenderName(user.getUsername());
		
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
	
	@Test	//Add this test between createChatroom and deleteChatroom tests
	public void testSelectAllChatrooms() {
		List<Chatroom> roomList = db.selectAllChatrooms();
		if(roomList.isEmpty()){
			System.out.println("No Chatrooms in database");
			fail("No chatrooms returned from method");
		}
	}
	
	@Test
	public void testSignup(){
		if(!db.signUp(user)){
			fail("User not signed up or name already taken");
		}
	}
	
	@Test
	public void testLogin(){
		if(!db.Login(user)){
			fail("User not logged in");
		}
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
	/*	
	@Test //Add this test after createChatroom and Signup, and before deleteChatroom and deleteUser
	public void testInsertUserIntoChatroom() {
		
	}
*/	
/*
	@Test	//Add this test between createChatroom and deleteChatroom tests
	public void testInsertMessages() {
		
	}
*/	
	
	

}
