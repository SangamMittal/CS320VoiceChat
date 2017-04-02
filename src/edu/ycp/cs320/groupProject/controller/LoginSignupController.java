package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.*;

public class LoginSignupController {
	
	// Login Method: Check to see if the given user exist. 
	// If it does, does it matched the password. If it does, return the user.
	public User login(User u){
		User userDatabase = new User();
		
		// Contact the database and return the user
		// userDatabase = someMethod from DerbyDatabase // check if exist by username only
		// it should return null if that user does not exist

		if(userDatabase != null){
			if(userDatabase.getName().equals(u.getName()) && userDatabase.getPassword().equals(u.getPassword())){
				return userDatabase;
			}
		}
		return null;
	}//end of login method
	
	// SignUp Method: Check to see if 
	public User signUp(User u){
		User userDatabase = new User();
		User uReturn = new User();
		// Contact the database and return the user
		// userDatabase = someMethod from DerbyDatabase // check if exist by username only
		// it should return null if that user does not exist
		
		// User not exist yet, so sign up
		if(userDatabase == null){
			
			// Contact the database
			// uReturn = someInsertMethod(u);
			return uReturn;
		}
		else{
			System.out.println("Username is Taken");
		}
		
		return null;
	}//end of signUp method
	

}
