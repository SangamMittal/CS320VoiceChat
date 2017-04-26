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

	ArrayList<Post>   posts   = new ArrayList<Post>();
	
	
	
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
	
	@Test
	public void testSignup(){
		System.out.println("\n*** Testing signUp ***************************************************");
		User u = new User();
		u.setUsername("Hello");
		u.setPassword("World");
		if(!db.signUp(u)){
			fail("User not signed up or name already taken");
		}
		db.deleteUser(u);
	}
	
	@Test
	public void testLogin(){
		System.out.println("\n*** Testing login ***************************************************");
		User user = new User();
		user.setUsername("LoginMan");
		user.setPassword("l0gin");
		db.signUp(user);
		if(!db.Login(user)){
			fail("User not logged in");
		}
		db.deleteUser(user);
	}
	
	@Test
	public void testDeleteUsers() {
		System.out.println("\n*** Testing deleteUser ***************************************************");

		User u = new User("student1", "ycp", false);
		//Chatroom c = new Chatroom();
		//Boolean userInserted = false;
		//Boolean LoggedIn= false;

		//userInserted = db.insertUserIntoChatroom(u, c);
		db.signUp(u);
		
		//if (userInserted== true)
		//{
		System.out.println("User inserted; now must delete it");
			
		User userDeleted = db.deleteUser(u);
		if(userDeleted != null){
			System.out.println("user deleted");
		}
		else if(userDeleted == null){
			fail("User has not been deleted!");
		}

	}// end of testDeleteUsers
	
	@Test
	public void testDeleteChatroom()
	{
		System.out.println("\n*** Testing deleteChatroom ***************************************************");

		User u = new User();
		u.setUsername("testuser");
		Chatroom c = new Chatroom();
		c.setChatroomName("Chatroom");
		
		Boolean deleted = false;
		
		deleted=  db.deleteChatroom(c, u);
		
		if(deleted == false){
			fail("Chatroom has not been deleted!");
		}
		else{
			System.out.println("Chatroom deleted");
		}
		
		
		
		
	}
	
	@Test
	public void testCreateChatroom()
	{
		System.out.println("\n*** Testing createChatroom ***************************************************");
		User u = new User();
		Chatroom c = new Chatroom();
		Boolean created = false;
		
		u.setUsername("Hi2");
		u.setPassword("HI2");
		c.setChatroomName("chatroom");
		db.signUp(u);
		created	= db.createChatroom(c, u);
		db.deleteChatroom(c, u);
		db.deleteUser(u);
		System.out.println(created);
		if (created==false)
		{
			fail("chatroom not created");
		}
		else 
		{
			System.out.println("Chatroom created");
		}
	}
	
	@Test	//Add this test between createChatroom and deleteChatroom tests
	public void testSelectAllChatrooms() {
		System.out.println("\n*** Testing selectAllChatrooms ***************************************************");

		List<Chatroom> roomList = db.selectAllChatrooms();
		if(roomList.isEmpty()){
			System.out.println("No Chatrooms in database");
			fail("No chatrooms returned from method");
		}
	}
	
	@Test
	public void testSelectMessages()
	{
		List<Post> PostList = null;
		
		Chatroom c = new Chatroom("ChatroomName", "password", true);
		c.setChatroomID(1);
	
		//Something should be going into PostList here, otherwise I wouldn't be getting a null pointer exception
		PostList = db.selectMessages(c);
		
		if (PostList.isEmpty())
		{
			fail("PostList empty when it should have messages from chatroom c in it");
		}
		
		else
		{
			posts = new ArrayList<Post>();
			for (Post p : posts) {
				
				posts.add(p);
				System.out.println(p.getText());
			}
		}
		
		
		
		
	}
	
	
	@Test
	public void testRemoveUserFromChatroom() {
		System.out.println("\n*** Testing removeUserFromChatroom *************************************************");
		
		Boolean removeSuccess = false;
		
		// Create new User
		String username = "TestingRemove";
		String password = "TestingRemove";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
		
		// Create new User
		String username2 = "TestingRemove2";
		String password2 = "TestingRemove2";
		User user2 = new User();
		user2.setUsername(username2);
		user2.setPassword(password2);
		System.out.println("	SignUp Success: " + db.signUp(user2));
	
		// Create new Chatroom
		String roomName = "TestingRemove";
		String roomPassword = "TestingRemove";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Insert user into chatroom
		System.out.println("	Insert New User Success: " + db.insertUserIntoChatroom(user2, room));
		
		// Remove user
		removeSuccess = db.removeUserFromChatroom(room, user2);

		// Delete users and chatroom
		System.out.println(" Delete Chatroom Success: " + db.deleteChatroom(room ,user));
		System.out.println(" Delete User Success:" + db.deleteUser(user));
		System.out.println(" Delete User2 Success: " + db.deleteUser(user2));
		
		// Check to see if insert was successful
		if(removeSuccess == false){
			fail("Failed to remove user from chatroom!");
		}
		else{
			System.out.println("Successful in removing user from chatroom");
		}
		

		//db.removeUserFromChatroom(room, user);
		
	}// end testRemoveUserFromChatroom

	@Test
	public void testSelectAdminFromChatroom() {
		System.out.println("\n*** Testing selectAdminFromChatroom *************************************************");
		
		// Create new User
		String username = "TestingSelect";
		String password = "TestingSelect";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
	
		// Create new Chatroom
		String roomName = "TestingSelect";
		String roomPassword = "TestingSelect";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Get the admin of a chatroom
		User userReturn = db.selectAdminFromChatroom(room);
		System.out.println("Returned username: " +userReturn.getUsername());
		
		// Check to see if insert was successful
		if(!userReturn.getUsername().equals(username)){
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
		System.out.println("\n*** Testing changeAdmin ***************************************************");
		
		// Create new User
		String username = "TestingChange";
		String password = "TestingChange";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println("	SignUp Success: " + db.signUp(user));
		
		// Create new User
		String username2 = "TestingChange2";
		String password2 = "TestingChange2";
		User user2 = new User();
		user2.setUsername(username2);
		user2.setPassword(password2);
		System.out.println("	SignUp Success: " + db.signUp(user2));
	
		// Create new Chatroom
		String roomName = "TestingChange";
		String roomPassword = "TestingChange";
		Chatroom room = new Chatroom();
		room.setChatroomName(roomName);
		room.setPassword(roomPassword);
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		
		// Insert user into chatroom
		System.out.println("	Insert New User Success: " + db.insertUserIntoChatroom(user2, room));
		
		// Change Admin
		Boolean changeSuccess = db.changeAdmin(room, user2);
		
		// Check to see if insert was successful
		User u = db.selectAdminFromChatroom(room);
		if(!u.getUsername().equals(username2)){
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
	
	
	@Test	//Add this test between createChatroom and deleteChatroom tests
	public void testInsertMessages() {
		System.out.println("\n*** Testing insertMessages ***************************************************");
		User user = new User();
		user.setUsername("InsertyGuy");
		user.setPassword("inserted");
		System.out.println("	SignUp Success: " + db.signUp(user));
		
		Chatroom room = new Chatroom();
		room.setChatroomName("InsertingRoom");
		room.setPassword("inserted");
		System.out.println("	Create Success: " + db.createChatroom(room, user));
		Post post = new Post();
		post.setText("This is a message!");
		Boolean test = db.insertMessages(room, post, user);
		if(!test){
			fail("Message not inserted");
		}

	}// end testInsertMessages
	
	

		
	@Test //Add this test after createChatroom and Signup, and before deleteChatroom and deleteUser
	public void testInsertUserIntoChatroom() {
		System.out.println("\n*** Testing insertUserIntoChatroom ***************************************************");

	}// end testInsertUserIntoChatroom
	

	

	

	


}