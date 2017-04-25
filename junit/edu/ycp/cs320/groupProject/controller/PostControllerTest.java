package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class PostControllerTest {
	
	
	PostController pc = new PostController();
	Boolean result;

	@Test
	public void testPost() {
		//fail("Not yet implemented");
		
		result = false;
		
		Chatroom c= new Chatroom("Bananas", "pw", false);
		c.setChatroomName("Bananas");
		c.setChatroomID(1);
		c.setMessagesID(2);
		c.setAdminID(500);
		c.setMaxNumber(4);
		c.setMessages("BWAHA");
		c.setPassword("Dood");
		
		User u = new User("Bluh", "Hi", false);
		u.setUsername("Bluh");
		u.setUserId(500);
		u.setAdmin(false);
		u.setChatroomId(1);
		u.setNum(2);
		u.setPassword("Dood");
		
		
		
		Post p = new Post();
		p.setText("Haha Hilarious");
		p.setMessagesID(2);
		p.setRoomID(1);
		p.setSenderID(500);
		p.setSenderName("Bluh");
		
		
		result = pc.post(u, p, c);
		
		if (result == false)
		{
			fail("pc.post returned false");
		}
		
		else if (result==true)
		{
			
		}

	}

}
