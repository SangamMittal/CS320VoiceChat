package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

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

public class ChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User sharedUser;
	private Chatroom sharedChatroom;
	
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
		
		
		req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String errorMessage = null;
		String userMessage = null;
		User user = sharedUser;
		Chatroom chatroom = sharedChatroom;
		UserController uController = new UserController();
		ChatroomController cController = new ChatroomController();
		
		System.out.println(user.getUsername());
		
		try {
			userMessage = req.getParameter("usermessage");
					
					
		} catch (NumberFormatException e) {
			errorMessage = "Nothing";
		}
		
		
		
		if(req.getParameter("logout") != null){
			resp.sendRedirect("login");
		}
		else if(req.getParameter("send") != null){
			if(userMessage != null){
			//	uController.sendMessage(user, userMessage, chatroom);
			}
		}
		else if(req.getAttribute("exitP") != null){
			cController.permanentlyExitChatroom(user, chatroom);
			resp.sendRedirect("chatroomList");
		}

		
		
		// Add parameters as request attributes
		//req.setAttribute("model", chatroom);

		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);
		
		
	}//end doPost


	
	
	
	
}
