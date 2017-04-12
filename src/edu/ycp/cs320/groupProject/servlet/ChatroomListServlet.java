package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.UserController;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;

//import edu.ycp.cs320.groupProject.controller.NumbersController;
//import edu.ycp.cs320.groupProject.model.Numbers;

public class ChatroomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User sharedUser;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TESTING 1 2 3
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
		
		
		req.getRequestDispatcher("/_view/chatroomList.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserController userController = new UserController();
		Chatroom chatroom = null;
		Chatroom sharedChatroom = null;
		
		if(req.getParameter("logout") != null){
			sharedUser = userController.logout();
			resp.sendRedirect("login");
			
		}
		else if(req.getParameter("createChatroom") != null){
			resp.sendRedirect("createChatroom");
		}
		
		// Add parameters as request attributes for other servlets during this session
		req.getSession().setAttribute("sharedUser", sharedUser);
		sharedChatroom = chatroom;
		req.getSession().setAttribute("sharedChatroom", sharedChatroom);
		
		
		
		
	}

	
}
