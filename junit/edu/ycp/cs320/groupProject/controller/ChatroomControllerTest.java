package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.model.Chatroom;

public class ChatroomControllerTest {
	private Chatroom model;
	private ChatroomController controller;
	
	
	@Before
	public void setUp(){
		model = new Chatroom();
		controller = new ChatroomController();
		
		model.setMessages("Hello World! This is me!");
		
	}
	
	@Test
	public void testAllMessages(){
		
		
		
	}
	
	@Test
	public void testPermanentlyExitChatroom(){
		
	}

}
