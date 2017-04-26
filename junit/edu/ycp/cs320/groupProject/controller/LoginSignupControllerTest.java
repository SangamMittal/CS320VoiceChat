package edu.ycp.cs320.groupProject.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.model.User;

public class LoginSignupControllerTest {
	private User user;
	private LoginSignupController loginController;
	private UserController userController;
	
	
	@Before
	public void setUp(){
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
		loginController = new LoginSignupController();
		userController = new UserController();
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	
	@Test
	public void testLogin() {
		loginController.signUp(user);
		if(loginController.login(user) == false){
			fail("Login failed");
		}
		
		userController.deleteAccount(user);
	}
	
	@Test
	public void testSignUp(){
		if(loginController.signUp(user) == false){
			fail("Sign up failed");
		}
		
		userController.deleteAccount(user);
	}

}
