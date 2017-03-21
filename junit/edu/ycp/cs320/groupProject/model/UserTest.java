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
		assertEquals("name", u.getName());
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
		u.setName("Hello");
		assertEquals("Hello", u.getName());
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
	
	
}
