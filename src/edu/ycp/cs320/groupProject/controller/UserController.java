package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;

public class UserController {
	
	// 
	public void sendMessage(User user, String message, Chatroom chatroom)
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
	public Chatroom createChatroom(Chatroom c)
	{
		Chatroom chatR = null;
		// Contact Database
		// chatR = db.someMethodName(c);
		/* Database Method:
		 * 			Select stmt to see if chatroom name already exist
		 * 			if not
		 * 				insert it into database and return the chatroom
		 * 			if already exist
		 * 				return null
		 */
		
		return chatR;
	}
	
	public boolean deleteAccount(User user, Chatroom c)
	{
		boolean success = false;
		// Contact Database
		// Database Method:
		// 			Check to see if this user is the admin of this chatroom
		//			If yes, then remove the chatroom from that list and return true
		//			If not, return false
		
		return success;
	}
	
	public Chatroom CreateAccount(User u, Chatroom c)
	{
		Chatroom chatR = null;
		// Contact the database
		// Database Method:
		//			Check to see if chatroom name already exist
		//			If not, use insert stmt to put it in the database, return that chatroom
		//			If yes, return null;
		
		return chatR;
	}
	

	

}
