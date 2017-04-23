package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;

public class PostController {
	
	private IDatabase db = null;
	
	public PostController()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	
	
	
	public String post(User u, String post, Chatroom c)
	{
		db.insertMessages(c, post);
		
		//throw new UnsupportedOperationException("TODO - implement");
		
	}
	
	public String getMessage()
	{
		throw new UnsupportedOperationException("TODO - implement");
		//return something;
	}
	
	

}
