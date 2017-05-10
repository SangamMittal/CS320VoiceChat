package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;
//hey
public class UserController {
	
	private IDatabase db = null;
	
	public UserController()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	
	 
	public Boolean sendMessage(User user, Post message, Chatroom chatroom)
	{
		Boolean inserted=false;
		// Appending the message so it's in the form, username: the message...
		String s = user.getUsername() + ": " + message;
		
		// Contact the database
		// Adding to the existing message using method in DerbyDatabase
		inserted = db.insertMessages(chatroom, message, user);
		
		return inserted;
	}
	
	// A user object with null will be return, when they want to logout
	public User logout()
	{	
		return null;
		
		
	}//end of logout
	
	
	
	//should it be return type Chatroom?
	public Boolean createChatroom(Chatroom c, User u )
	{
		Chatroom chatR = null;
		// Contact Database
		Boolean created= false;
		 created = db.createChatroom(c, u);
		/* Database Method:
		 * 			Select stmt to see if chatroom name already exist
		 * 			if not
		 * 				insert it into database and return the chatroom
		 * 			if already exist
		 * 				return null
		 */
		return created;
	}
	
	public User deleteAccount(User user)
	{
		boolean success = false;
		// Contact Database
		// Database Method:
		// 			Check to see if this user is the admin of this chatroom
		//			If yes, then remove the chatroom from that list and return true
		//			If not, return false
		
		User deleted = db.deleteUser(user);
		
		return deleted;
	}
	
	
	//fix
//	public Boolean CreateAccount(User u, Chatroom c)
//	{
//		Chatroom chatR = null;
//		Boolean created= false;
		// Contact the database
		// Database Method:
		//			Check to see if chatroom name already exist
		//			If not, use insert stmt to put it in the database, return that chatroom
		//			If yes, return null;
		
	//	created = db.insertUserIntoChatroom(u, c);
		
		
	//	return created;
//	}
	
	public Boolean insertUserIntoChatroom(User u, Chatroom c)
	{
		Boolean created=false;
		
			created = db.insertUserIntoChatroom(u, c);		
			return created;
		
		
	}
	

	

}
