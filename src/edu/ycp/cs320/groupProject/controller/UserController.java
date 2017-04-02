package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;

public class UserController {
	
	// 
	public void send(User user, String message, Chatroom chatroom)
	{
		// Appending the message so it's in the form, username: the message...
		String s = user.getName() + ": " + message;
		
		// Contact the database
		// Adding to the existing message using method in DerbyDatabase
		
		
	}
	
	// A user object with null will be return, when they want to logout
	public User logout()
	{	
		return null;
	}//end of logout
	
	
	
	//should it be return type Chatroom?
	public void createChatroom()
	{
		
	}
	
	//Contact database
	public void deleteAccount()
	{
		
	}
	
	public String CreateAccount()
	{
		return null;
	}
	
	public void cancel()
	{
		
	}
	
	public void exitRoom()
	{
		
	}
	
	

}
