package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.*;

import java.util.List;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;

//hey
public class LoginSignupController {
	
	private IDatabase db = null;

	public LoginSignupController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}
	
	
	
	// Login Method: Check to see if the given user exist. 
	// If it does, does it matched the password. If it does, return the user.
	public Boolean login(User u){
		//User userDatabase = new User();
		
	
		 Boolean isCorrect =  db.Login(u) ;
		
		// Contact the database and return the user
		// userDatabase = someMethod from DerbyDatabase // check if exist by username only
		// it should return null if that user does not exist

		return isCorrect;
		
	}//end of login method
	
	// SignUp Method: Check to see if 
	public Boolean signUp(User u){
	
		Boolean isCorrect =  db.signUp(u) ;
		
		return isCorrect;
				
	}//end of signUp method
	

}
