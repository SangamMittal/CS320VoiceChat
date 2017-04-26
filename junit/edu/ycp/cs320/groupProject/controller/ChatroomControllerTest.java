package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class ChatroomControllerTest {
	private Chatroom chatroom;
	private ChatroomController chatroomController;
	private User user;
	private UserController userController;
	private LoginSignupController loginController;
	private ArrayList<Post> posts;
	private Post post1;
	private Post post2;
	private Post post3;
	
	
	@Before
	public void setUp(){
		chatroom = new Chatroom();
		chatroomController = new ChatroomController();
		user = new User();
		userController = new UserController();
		loginController = new LoginSignupController();
		posts = new ArrayList<Post>();
		post1 = new Post();
		post2 = new Post();
		post3 = new Post();
		
		
		// Creating messages
		post1.setText("Hello1");
		post2.setText("Hello2");
		post3.setText("Hello3");
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
		// Creating the User
		user.setUsername("Username");
		user.setPassword("Password");
		loginController.signUp(user);
		
		// Creating the Chatroom
		chatroom.setChatroomName("ChatroomName");
		chatroom.setPassword("CPassword");
		chatroomController.create(chatroom, user);
		
		// Insert Messages
		userController.sendMessage(user, post1, chatroom);
		userController.sendMessage(user, post2, chatroom);
		userController.sendMessage(user, post3, chatroom);

	}
	
	@After
	public void tearDown() throws Exception {
		userController.deleteAccount(user);
		chatroomController.deleteChatroom(chatroom, user);
	}
	
	@Test
	public void testAllMessages(){
		System.out.println(" TESTING ChatroomController: AllMessages");
		ArrayList<Post> allMessages = chatroomController.allMessages(chatroom);
		
		for(int i = 0; i < posts.size(); i++){
			System.out.println("Message: " + allMessages.get(i).getText());
			if(!allMessages.get(i).getText().equals(posts.get(i).getText())){
				fail("Did not get messages OR Message out of order");
			}
		}
		
	}
	
	@Test
	public void testPermanentlyExitChatroom(){
		// Creating a new User and insert into chatroom
		User user2 = new User();
		user2.setUsername("Username2");
		user2.setPassword("Password2");
		loginController.signUp(user2);
		userController.insertUserIntoChatroom(user2, chatroom);
		
		if(chatroomController.permanentlyExitChatroom(user2, chatroom) == false){
			fail("Unable to delete user from chatroom");
		}
		
		userController.deleteAccount(user2);
		
	}
	
	@Test
	public void testCreate(){
		// Creating new chatroom
		Chatroom chatroom2 = new Chatroom();
		chatroom2.setChatroomName("chatroom2");
		chatroom2.setPassword("password2");
		
		if(chatroomController.create(chatroom2, user) == false){
			fail("Unable to create chatroom");
		}
		
		chatroomController.deleteChatroom(chatroom2, user);
	}
	
	@Test
	public void testDeleteChatroom(){
		// Creating new chatroom
		Chatroom chatroom2 = new Chatroom();
		chatroom2.setChatroomName("chatroom2");
		chatroom2.setPassword("password2");
		chatroomController.create(chatroom2, user);
		
		if(chatroomController.deleteChatroom(chatroom2, user) == false){
			fail("Unable to create chatroom");
		}
		
	}
	
	@Test
	public void testGetAllChatrooms(){
		if(chatroomController.getAllChatroom().size() <= 0){
			fail("Did not get all the chatroom");
		}
	}
	
	
	@Test
	public void testFormatMessage(){
		ArrayList<Post> allMessages = chatroomController.allMessages(chatroom);
		Post p = allMessages.get(0);
		if(chatroomController.formatMessage(p).length() <= 0){
			fail("No Message String return");
		}
	}
	
	
	
	
	

}
