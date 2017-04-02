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
		System.out.println("In the Login servlet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String username = null;
		String password = null;
		LoginSignupController controller;
		User user = new User();
		User model = null;
		
		// *********** TESTING *************
		//model.setName("TestingName");
		//model.setPassword("TestingPassword");
		//model = null;
		
		try {
			username = req.getParameter("username");
			password = req.getParameter("second");

			if (username == null || password == null) {
				errorMessage = "Please enter infomation";
			}
			else if (username.length() > 32 || password.length() > 32){
				errorMessage = "Error: Max 32 Characters";
			}
			else{
				user.setName(username);
				user.setPassword(password);
			}
					
					
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		// User click on login button
		if(req.getParameter("login") != null){
			controller = new LoginSignupController();
			//model = controller.login(user);
			
			// if user exist and matched password
			if(model != null){
				resp.sendRedirect("chatroomList");
			}
			else{
				errorMessage = "Invalid Username and Password";
			}
			
		}//end of user click on login button
		
		
		// User click on signUp button
		else if (req.getParameter("signUp") != null){
			controller = new LoginSignupController();
			//model = controller.signUp(user);			// call function and try it signUp the user
			
			// signUp is successful
			if(model != null){
				resp.sendRedirect("chatroomList");
			}
			else{
				errorMessage = "Username already taken!";
			}
		}//end of user click on signUp button

		
		// Add parameters as request attributes
		req.setAttribute("model", model);

		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
		//********* TESTING ************
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(model.getName());
		System.out.println(model.getPassword());
		////////////////////////////////////////
		
	}//end of doPost
	
	
	
}
