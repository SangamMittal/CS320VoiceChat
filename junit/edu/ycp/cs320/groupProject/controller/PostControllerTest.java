package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public class PostControllerTest {
	
	
	PostController pc = new PostController();
	ChatroomController cc = new ChatroomController();
	LoginSignupController lsc = new LoginSignupController();
	UserController uc = new UserController();
	Boolean result;

	@Test
	public void testPost() {
		//fail("Not yet implemented");
		Chatroom c= new Chatroom("Bananas", "pw", false);
		c.setChatroomName("Bananas");
		c.setPassword("Dood");
		
		User u = new User("Bluh", "Hi", false);
		u.setUsername("Bluh");
		u.setPassword("Dood");
		
		Post p = new Post();
		
		//Do I need this line?
		p.setText("MUMBO JUMBO");
		
		cc.create(c, u);
		lsc.signUp(u);
		
		result = false;
		
		result = pc.post(u, p, c);
		
		if (result == false)
		{
			fail("pc.post returned false");
		}
		
		else if (result==true)
		{
			
		}
			
		cc.deleteChatroom(c, u);
		uc.deleteAccount(u);
	
	}

	
	@Test public void testGetMessage()
	{
		Chatroom c= new Chatroom("Bananas", "pw", false);
		c.setChatroomName("Bananas");
		c.setPassword("Dood");
		
		User u = new User("Bluh", "Hi", false);
		u.setUsername("Bluh");
		u.setPassword("Dood");
		
		Post p = new Post();
		
		//Do I need this line?
		p.setText("MUMBO JUMBO");
		
		cc.create(c, u);
		lsc.signUp(u);
		
		ArrayList<Post> posts = new ArrayList<Post>();
			
		posts = pc.getMessage(c);
		
		if (posts.isEmpty())
		{
			fail("posts arrayList should contain message(s) but is empty");
		}
			
		cc.deleteChatroom(c, u);
		uc.deleteAccount(u);

	}
	
}
