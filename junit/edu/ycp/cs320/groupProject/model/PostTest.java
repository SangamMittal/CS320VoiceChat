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
		model = new Post("Test message");		
	}
	
	
	@Test
	public void testSetText() 
	{
		model.setText("Test message changed");
		assertEquals("Test message changed", model.getText());
	}
	
	@Test
	public void testgetText()
	{
		model.setText("Test message changed too");
		assertEquals("Test message changed too", model.getText());
	}
	
	
	
	

}
