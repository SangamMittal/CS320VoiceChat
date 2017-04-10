package edu.ycp.cs320.groupProject.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ChatroomTest {
	private Chatroom room;
	
	@Before
	public void setUp(){
		String name = "name";
		String password = "password";
		Boolean hasPassword = true;
		room = new Chatroom(name, password, hasPassword);
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
		assertEquals("name", room.getName());
	}
	
	@Test
	public void testSetName(){
		String a = "Hello";
		room.setName(a);
		assertEquals(a, room.getName());
	}
	
	@Test
	public void testGetPassword(){
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
