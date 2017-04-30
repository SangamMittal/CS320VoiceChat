package edu.ycp.cs320.groupProject.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ChatroomTest {
	private Chatroom room;
	
	@Before
	public void setUp(){
		room = new Chatroom();
	}
	
	@Test
	public void testSetMaxNumber(){
		int a = 6;
		room.setMaxNumber(a);
		assertEquals(a, room.getMaxNumber());
	}

	@Test
	public void testGetMaxNumber(){
		int a = 6;
		room.setMaxNumber(a);
		assertEquals(a, room.getMaxNumber());
	}
	
	@Test
	public void testGetName(){
		room.setChatroomName("name");
		assertEquals("name", room.getChatroomName());
	}
	
	@Test
	public void testSetName(){
		String a = "Hello";
		room.setChatroomName(a);
		assertEquals(a, room.getChatroomName());
	}
	
	@Test
	public void testGetPassword(){
		room.setPassword("password");
		assertEquals("password", room.getPassword());
	}
	
	@Test
	public void testSetPassword(){
		String a = "World";
		room.setPassword(a);
		assertEquals(a, room.getPassword());
	}
	
	@Test
	public void testGetNumUser(){
		int num = 5;
		room.setNumUser(5);
		assertEquals(num, room.getNumUser());
	}
	
	@Test
	public void testSetNumUser(){
		int num = 5;
		room.setNumUser(5);
		assertEquals(num, room.getNumUser());
	}
	
	@Test
	public void testSetMessages(){
		String s = "Hello World!";
		room.setMessages(s);
		assertEquals(s, room.getMessages());
	}
	
	@Test
	public void testGetMessages(){
		String s = "Mary Had A Little Lamb";
		room.setMessages(s);
		assertEquals(s, room.getMessages());
	}
	
	
	
	
	
	
	
}//end of ChatroomTest
