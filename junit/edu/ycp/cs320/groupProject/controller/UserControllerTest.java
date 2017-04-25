package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class UserControllerTest {
	
	private Chatroom c = new Chatroom();
	UserController uc = new UserController();
	private Post p = new Post();
	private User u = new User();
	

	@Test
	public void testSendMessage() {
		//fail("Not yet implemented");
		Boolean sent= false;
	c.setAdminID(1);
	c.setChatroomID(2);
	c.setChatroomName("Muskets");
	c.setMessages("Hello");
	c.setMessagesID(3);
	c.setPassword("pass");
	
	p.setMessagesID(3);
	p.setRoomID(2);
	p.setSenderID(1);
	p.setText("Hello");
	p.setSenderName("Josh");
	
	u.setChatroomId(2);
	u.setPassword("pass");
	u.setUsername("Josh");
	u.setUserId(1);
	u.setNum(4);
	u.setAdmin(false);
	
	
	sent = uc.sendMessage(u, p, c);
	
	if (sent==true)
	{
		
	}
	
	else if (sent==false)
	{
		fail("Boolean returned false");
	}
	
	}
	
	@Test
	public void testCreateChatroom()
	{
		
		Boolean created= false;
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
		
		
		created= uc.createChatroom(c, u);
		
		if (created==false)
		{
			fail("uc.createChatroom returned false");
		}
		
		else if (created==true)
		{
			
		}
		
		
	}
	
	@Test
	public void testdeleteAccount()
	{
		User deleted= new User();
		
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
		
	deleted= uc.deleteAccount(u, c);
	
	if (deleted.getUsername()==null)
	{
		
	}
	
	else 
	{
		System.out.println("Model getter returns:"+  deleted.getUsername());
		fail("It didn't return null");
	}
	
}
	
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
	
	
}
