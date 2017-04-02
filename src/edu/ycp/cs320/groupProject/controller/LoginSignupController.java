package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.*;

import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class LoginSignupController {
	

	
	
	
	// Login Method: Check to see if the given user exist. 
	// If it does, does it matched the password. If it does, return the user.
	public User login(User u){
		User userDatabase = new User();
		
		IDatabase db = DatabaseProvider.getInstance();
		 User u =  db. ;
		
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
		
		IDatabase db = DatabaseProvider.getInstance();
		 User u =  db. ;
		
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
