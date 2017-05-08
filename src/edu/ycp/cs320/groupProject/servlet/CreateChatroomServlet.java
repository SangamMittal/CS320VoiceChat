package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.ChatroomController;
import edu.ycp.cs320.groupProject.controller.LoginSignupController;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;

//import edu.ycp.cs320.groupProject.controller.GuessingGameController;
//import edu.ycp.cs320.groupProject.model.GuessingGame;

public class CreateChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("sharedUser");
		
		if(user == null){
			System.out.println("    User: <" + user + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("     User: <" + user + "> logged in");
		
		req.getRequestDispatcher("/_view/createChatroom.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Chatroom room = new Chatroom();
		User user = new User();
		ChatroomController roomController;
		String errorMessage = null;
		Boolean error = false;
		Boolean createChatroomcheck = false;
		
		if(req.getParameter("create") != null){
			// Decode form parameters and dispatch to controller
			try {
				String username = (String) req.getSession().getAttribute("sharedUser");
				String roomName = req.getParameter("roomName");
				String roomPassword = req.getParameter("roomPassword");
				/*
				if(username == null){
					System.out.println("Not Logged In!!");
					error = true;
				}
				*/
				if (roomName == null) {
					errorMessage = "Please enter infomation";
					System.out.println(errorMessage);
					error = true;
				}
				else if (roomName.length() > 32 || roomPassword.length() > 32){
					errorMessage = "Error: Max 32 Characters";
					System.out.println(errorMessage);
					error = true;
				}
				
				user.setUsername(username);
				room.setChatroomName(roomName);
				room.setPassword(roomPassword);
				
						
						
			} catch (NumberFormatException e) {
				errorMessage = "Invalid double";
				System.out.println(errorMessage);
				error = true;
			}
			
			// User click on create button
			if(!error){
				roomController = new ChatroomController();
				createChatroomcheck = roomController.create(room, user);
				// if user exist and matched password
				if(!createChatroomcheck){
					errorMessage = "Chatroom name is already taken";
					System.out.println(errorMessage);
				}		
			}//end of user click on create button
		}
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.getSession().setAttribute("sharedChatroomName", room.getChatroomName());
		
		if(createChatroomcheck){
			resp.sendRedirect("chatroomList");
			
		}
		
		req.getRequestDispatcher("/_view/createChatroom.jsp").forward(req, resp);

	}

	
}
