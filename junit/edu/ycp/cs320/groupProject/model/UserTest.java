package edu.ycp.cs320.groupProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User u;
	@Before
	public void setUp(){
		u = new User("name", "password", true);
	}
	
	@Test
	public void testGetName(){
		assertEquals("name", u.getUsername());
	}
	
	@Test
	public void testGetPassword(){
		assertEquals("password", u.getPassword());
	}
	
	@Test
	public void testGetAdmin(){
		assertEquals(true, u.getAdmin());
	}
	
	@Test
	public void testSetName(){
		u.setUsername("Hello");
		assertEquals("Hello", u.getUsername());
	}
	
	@Test
	public void testSetPassword(){
		u.setPassword("World");
		assertEquals("World", u.getPassword());
	}
	
	@Test
	public void testSetAdmin(){
		u.setAdmin(false);
		assertEquals(false, u.getAdmin());
	}
	
	@Test
	public void testSetIsItLogined(){
		boolean state = false;
		u.setIsItLogined(state);
		assertEquals(false, u.getIsItLogined());
	}
	
	@Test
	public void testGetIsItLogined(){
		boolean state = true;
		u.setIsItLogined(state);
		assertEquals(true, u.getIsItLogined());
	}
	
	
}
