package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.ChatroomController;
import edu.ycp.cs320.groupProject.controller.UserController;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;

//import edu.ycp.cs320.groupProject.controller.NumbersController;
//import edu.ycp.cs320.groupProject.model.Numbers;

public class ChatroomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sharedUser;
	private User u;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().setAttribute("sharedChatroomName", null);

		// TESTING 1 2 3
	//	String user = (String) req.getSession().getAttribute("sharedUser");
		
		//problemo: it's returning null here
		sharedUser = (String) req.getSession().getAttribute("sharedUser");
		
		u = new User();
		
		u.setUsername(sharedUser);
		
		
		if(sharedUser == null){
			System.out.println("    User: <" + sharedUser + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println(" User: <" + sharedUser + "> logged in");
		
		ArrayList<Chatroom> allChatrooms = null;
		ChatroomController roomController = new ChatroomController();

		allChatrooms = roomController.getAllChatroom();
		req.setAttribute("allChatrooms", allChatrooms);
		
		req.getRequestDispatcher("/_view/chatroomList.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserController userController = new UserController();
		ChatroomController roomController = new ChatroomController();
	//	String chatroomName = null;
		String sharedChatroomName = null;
		Chatroom chatroom2 = null;
		
	
		
		
		ArrayList<Chatroom> allChatrooms = null;
		
		allChatrooms = roomController.getAllChatroom();
		req.setAttribute("allChatrooms", allChatrooms);

		Boolean redirect = false;
		
		String roomName = null;
		
		for (Chatroom c: allChatrooms)
		{
			System.out.println("Got into For loop");
			if (req.getParameter(c.getChatroomName())!= null)
				{	
					System.out.println("Got into if statement");
					//add user to chatroom	
					userController.insertUserIntoChatroom(u, c);

					
					
					chatroom2 = c;
					roomName = c.getChatroomName();
					chatroom2.setChatroomName(sharedChatroomName);
					
					//redirect
					redirect=true;
					
					
				}
		}
		
		
		
//		if(req.getParameter("logout") != null){
//			sharedUser = userController.logout();
//			resp.sendRedirect("login");
//		}
		if(req.getParameter("createChatroom") != null){
			resp.sendRedirect("createChatroom");
		}
		if(req.getParameter("refresh") != null){
			resp.sendRedirect("chatroomList");
		}
		
		// Add parameters as request attributes for other servlets during this session
		req.getSession().setAttribute("sharedUser", sharedUser);
		//sharedChatroom = chatroom;
//		System.out.println("C: " + chatroom.getChatroomName());
		String sharedString = chatroom2.getChatroomName();
		req.getSession().setAttribute("sharedChatroomName", roomName);
		
		// Add result objects as request attributes
		//req.setAttribute("errorMessage", errorMessage);

		
		if (redirect==true)
		{
			resp.sendRedirect("chatroom");
		}
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/chatroomList.jsp").forward(req, resp);
			
	}

	
}
