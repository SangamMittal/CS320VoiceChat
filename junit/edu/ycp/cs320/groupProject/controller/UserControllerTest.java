package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class UserControllerTest {
	
	private Chatroom c = new Chatroom();
	UserController uc = new UserController();
	ChatroomController cc = new ChatroomController();
	PostController pc = new PostController();
	private Post p = new Post();
	private User u = new User();
	LoginSignupController lsc = new LoginSignupController();
	

	@Test
	public void testSendMessage() {
		
	User u = new User();
	Post p = new Post();
	Chatroom c = new Chatroom();
	
	p.setText("BLAHBLAHBLAH");
	
	u.setUsername("UsernameTest");
	u.setPassword("Password");
	
	c.setChatroomName("Hello");
	c.setPassword("test");
	
		lsc.signUp(u);
		cc.create(c, u);	
		pc.post(u, p, c);
		
		
		//fail("Not yet implemented");
		
		//Contact database to create a new user & chatroom and delete it at the end
		
		Boolean sent= false;
//	c.setAdminID(1);
//	c.setChatroomID(2);
//	c.setChatroomName("Muskets");
//	c.setMessages("Hello");
//	c.setMessagesID(3);
//	c.setPassword("pass");
	
//	p.setMessagesID(3);
//	p.setRoomID(2);
//	p.setSenderID(1);
//	p.setText("Hello");
//	p.setSenderName("Josh");
	
//	u.setChatroomId(2);
//	u.setPassword("pass");
//	u.setUsername("Josh");
//	u.setUserId(1);
//	u.setNum(4);
//	u.setAdmin(false);
	
//	cc.create(c, u);
//	lsc.signUp(u);
	
	
	
	sent = uc.sendMessage(u, p, c);
	
	if (sent==true)
	{
		
	}
	
	else if (sent==false)
	{
		fail("Boolean returned false");
	}
	
	cc.deleteChatroom(c, u);
	uc.deleteAccount(u);
	
	
	}
	
	@Test
	public void testCreateChatroom()
	{
		User u = new User();
		Post p = new Post();
		Chatroom c = new Chatroom();
		
		u.setUsername("UsernameTest");
		u.setPassword("Password");
		
		//Should I have these lines?
		c.setChatroomName("Hello");
		c.setPassword("test");
		
		//I think I need to manually create the user here?
		lsc.signUp(u);
	
	
		Boolean created= false;
		/*
		c.setAdminID(1);
		c.setChatroomID(2);
		c.setChatroomName("Muskets");
		c.setMessages("Hello");
		c.setMessagesID(3);
		c.setPassword("pass");
		
		u.setChatroomId(2);
		u.setPassword("pass");
		u.setUsername("Josh");
		u.setUserId(1);
		u.setNum(4);
		u.setAdmin(false);
		*/
		
		
		created= uc.createChatroom(c, u);
		
		if (created==false)
		{
			fail("uc.createChatroom returned false");
		}
		
		else if (created==true)
		{
			
		}
		
		cc.deleteChatroom(c, u);
		uc.deleteAccount(u);
		
		
	}
	
	@Test
	public void testdeleteAccount()
	{
		//Contact database to create a new user & chatroom and delete it at the end
		User deleted = new User();
		
		User u = new User();
		u.setUsername("UsernameTest");
		u.setPassword("Password");
		lsc.signUp(u);
	
		/*
		Chatroom c = new Chatroom();
		c.setChatroomName("Chatroom");
		c.setPassword("Password");
		cc.create(c, u);
		*/

		
		
	/*	
		c.setAdminID(1);
		c.setChatroomID(2);
		c.setChatroomName("Muskets");
		c.setMessages("Hello");
		c.setMessagesID(3);
		c.setPassword("pass");
		
		u.setChatroomId(2);
		u.setPassword("pass");
		u.setUsername("Josh");
		u.setUserId(1);
		u.setNum(4);
		u.setAdmin(false);
	*/
	deleted= uc.deleteAccount(u);
	
	if (deleted.getUsername().equals(u.getUsername()))
	{
		
	}
	
	else 
	{
		System.out.println("Model getter returns:"+  deleted.getUsername());
		fail("It returns null");
	}
	
	uc.deleteAccount(u);
	
	
	
}
	/*
	@Test
	public void testCreateAccount()
	{
		Boolean created=false;
		c.setAdminID(1);
		c.setChatroomID(2);
		c.setChatroomName("Muskets");
		c.setMessages("Hello");
		c.setMessagesID(3);
		c.setPassword("pass");
		
		u.setChatroomId(2);
		u.setPassword("pass");
		u.setUsername("Josh");
		u.setUserId(1);
		u.setNum(4);
		u.setAdmin(false);
		
	created=uc.CreateAccount(u, c);	
	
	if (created==false)
	{
		fail("uc.CreateAccount returned false");
	}
		
	}
	
	*/
	
	
}
