package edu.ycp.cs320.groupProject.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import edu.ycp.cs320.groupProject.model.Post;

public class PostTest {
	
	private Post model;
	
	@Before
	public void setUp()
	{
		model = new Post();		
	}
	
	@Test
	public void testSetMessagesID(){
		model.setMessagesID(5);
		assertEquals(5, model.getMessagesID());
	}
	
	@Test
	public void testGetMessagesID(){
		model.setMessagesID(10);
		assertEquals(10, model.getMessagesID());
	}
	
	@Test
	public void testSetText() 
	{
		model.setText("Test message changed");
		assertEquals("Test message changed", model.getText());
	}
	
	@Test
	public void testGetText()
	{
		model.setText("Test message changed too");
		assertEquals("Test message changed too", model.getText());
	}
	
	@Test
	public void testSetSenderName(){
		model.setSenderName("Hello");
		assertEquals("Hello", model.getSenderName());
	}
	
	@Test
	public void testGetSenderName(){
		model.setSenderName("lolo");
		assertEquals("lolo", model.getSenderName());
	}
	

}
