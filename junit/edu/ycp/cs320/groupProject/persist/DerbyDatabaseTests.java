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

		/*
		LoggedIn=db.Login(u);
		
		if (LoggedIn== true)
		{
			fail("User should not have logged in because user should not exist because user should have been deleted");
		}
		*/
		
		
	
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
	
	@Test
	public void testDeleteChatroom()
	{
		User u = new User("student123", "ycp", false);
		Chatroom c = new Chatroom();
		Boolean deleted = false;
		
		deleted=  db.deleteChatroom(c, u);
		
		if (deleted == true)
		{
			
		}
		
	}
	
	
	@Test
	public void testRemoveUserFromChatroom() {
		System.out.println("\n*** Testing removeUserFromChatroom ***");
		
		// Create new User
		String username = "TestingRemoveUserFromChatroom";
		String password = "TestingRemoveUserFromChatroom";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
		
		// Create new User
		String username2 = "TestingRemoveUserFromChatroom2";
		String password2 = "TestingRemoveUserFromChatroom2";
		User user2 = new User();
		user.setUsername(username2);
		user.setPassword(password2);
		System.out.println("	SignUp Success: " + db.signUp(user2));
	
		// Create new Chatroom
		String roomName = "TestingRemoveUserFromChatroom";
		String roomPassword = "TestingRemoveUserFromChatroom";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Insert user into chatroom
		System.out.println("	Insert New User Success: " + db.insertUserIntoChatroom(user2, room));
		
		// Remove user
		Boolean removeSuccess = db.removeUserFromChatroom(room, user2) && db.removeUserFromChatroom(room, user);
		
		// Check to see if insert was successful
		if(removeSuccess == false){
			fail("Failed to remove user from chatroom!");
		}
		else{
			System.out.println("Successful in removing user from chatroom");
		}
		
		// Delete users and chatroom
		db.deleteChatroom(room ,user);
		db.deleteUser(user);
		db.deleteUser(user2);
		//db.removeUserFromChatroom(room, user);
		
	}// end testRemoveUserFromChatroom
	
	@Test
	public void testSelectAdminFromChatroom() {
		System.out.println("\n*** Testing selectAdminFromChatroom ***");
		
		// Create new User
		String username = "TestingSelectAdminFromChatroom";
		String password = "TestingSelectAdminFromChatroom";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
	
		// Create new Chatroom
		String roomName = "TestingSelectAdminFromChatroom";
		String roomPassword = "TestingSelectAdminFromChatroom";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Get the admin of a chatroom
		User userReturn = db.selectAdminFromChatroom(room);
		
		// Check to see if insert was successful
		if(userReturn.getUsername() != username){
			fail("Failed to get Admin from chatroom!");
		}
		else{
			System.out.println("Successful in selecting admin from chatroom");
		}
		
		// Delete users and chatroom
		db.deleteChatroom(room, user);
		db.deleteUser(user);

	}// end testSelectAdminFromChatroom
	
	@Test
	public void testChangeAdmin() {
		System.out.println("\n*** Testing changeAdmin ***");
		
		// Create new User
		String username = "TestingChangeAdmin";
		String password = "TestingChangeAdmin";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
		
		// Create new User
		String username2 = "TestingChangeAdmin2";
		String password2 = "TestingChangeAdmin2";
		User user2 = new User();
		user.setUsername(username2);
		user.setPassword(password2);
		System.out.println("	SignUp Success: " + db.signUp(user2));
	
		// Create new Chatroom
		String roomName = "TestingChangeAdmin";
		String roomPassword = "TestingChangeAdmin";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Insert user into chatroom
		System.out.println("	Insert New User Success: " + db.insertUserIntoChatroom(user2, room));
		
		// Change Admin
		Boolean changeSuccess = db.changeAdmin(room, user2);
		
		// Check to see if insert was successful
		if(db.selectAdminFromChatroom(room).getUsername() != username2){
			fail("Failed to change admin!");
		}
		else{
			System.out.println("Successful in changing admin");
		}
		
		// Delete users and chatroom
		db.deleteChatroom(room, user);
		db.deleteUser(user);
		db.deleteUser(user2);
		//db.removeUserFromChatroom(room, user);
		
	}// end testChangeAdmin
	
	@Test
	public void testCreateChatroom()
	{
		User u = new User("student123", "ycp", false);
		Chatroom c = new Chatroom();
		
		
	}

}
