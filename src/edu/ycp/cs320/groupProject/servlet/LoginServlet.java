package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.*;
import edu.ycp.cs320.groupProject.model.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//System.out.println("In the Login servlet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String errorMessage = null;
		String username = null;
		String password = null;
		String password2 = null;
		LoginSignupController controller;
		User user = new User();
		User model = new User();
		String sharedUser = null;
		
		
		
		//String sharedTest = "Shared Testing!";
	    //req.getSession().setAttribute("sharedTest", sharedTest); // add to session

		
		// *********** TESTING *************
		//model.setName("TestingName");
		//model.setPassword("TestingPassword");
		//model = null;
		
		// Decode form parameters and dispatch to controller
		try {
			username = req.getParameter("username");
			password = req.getParameter("password");
			password2 = req.getParameter("password2");
			System.out.println("Username: " + username);
			System.out.println("Password: " + password);

			if (username == null || password == null) {
				errorMessage = "Please enter infomation";
			}
			else if (username.length() > 32 || password.length() > 32){
				errorMessage = "Error: Max 32 Characters";
			}
			else{
				user.setUsername(username);
				user.setPassword(password);
			}
					
					
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		// User click on login button
		if(req.getParameter("login") != null){
			controller = new LoginSignupController();
			//model = controller.login(user);
			boolean logincheck = controller.login(user);
			// if user exist and matched password
			System.out.println("Logined: " + logincheck);
			if(logincheck == true){
				sharedUser = user.getUsername();
				resp.sendRedirect("chatroomList");
			}
			else{
				errorMessage = "Invalid Username and Password";
			}
			
		}//end of user click on login button
		
		
		// User click on signUp button
		else if (req.getParameter("signUp") != null){
			if(password.equals(password2)){
				errorMessage = "Passwords entered do not match!";
				
			}
			else{
				controller = new LoginSignupController();
				//model = controller.signUp(user);			// call function and try it signUp the user
				boolean signupcheck = controller.signUp(user);
				// signUp is successful
				if(signupcheck){
					sharedUser = user.getUsername();
					resp.sendRedirect("chatroomList");
				}
				else{
					errorMessage = "Username already taken!";
				}
			}
		}//end of user click on signUp button

		
		// Add parameters(model) as request attributes for other servlets during this session
		//sharedUser = model;
		
		req.getSession().setAttribute("sharedUser", sharedUser);

		/*
		// Add parameters as request attributes
		req.setAttribute("model", model);
		*/
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
		//********* TESTING ************
		//System.out.println(user.getName());
		//System.out.println(user.getPassword());
		//System.out.println(model.getName());
		//System.out.println(model.getPassword());
		////////////////////////////////////////
		
	}//end of doPost
	
	
	
}
