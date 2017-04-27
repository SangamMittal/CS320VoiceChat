package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.ChatroomController;
import edu.ycp.cs320.groupProject.controller.PostController;
import edu.ycp.cs320.groupProject.controller.UserController;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

//import edu.ycp.cs320.groupProject.controller.NumbersController;
//import edu.ycp.cs320.groupProject.model.Numbers;

public class ChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sharedUser;
	private String sharedChatroom; // Changes here
	private Chatroom chatroom;
	private PostController pc = new PostController();
	private Post post = new Post();
	private ArrayList<Post> posts = new ArrayList<Post>();
	private ArrayList<String> messages = new ArrayList<String>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//	String user = (String) req.getSession().getAttribute("sharedUser");
		
		sharedUser=	(String) req.getSession().getAttribute("sharedUser");
		sharedChatroom = (String) req.getSession().getAttribute("sharedChatroom");
		//System.out.println("pc.getMessage?");
		chatroom = new Chatroom();
		chatroom.setChatroomName(sharedChatroom);
		posts = pc.getMessage(chatroom);
		//System.out.println("Got Message? : " + !posts.isEmpty());
		if (posts != null)
		{
		
		
		for(Post p: posts){
			System.out.println(p.getText());
			messages.add(pc.formatMessage(p));
		}
		
		}
		
		else if (posts==null)
		{
			System.out.println("Posts is null");
		}
		
		if(sharedUser ==null){
			System.out.println("    User: <" + sharedUser + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		if(sharedChatroom ==null){
			//System.out.println("    User: <" + sharedUser + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/chatroom");
			return;
		}
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("     User: <" + sharedUser + "> logged in");
		
		
		req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String errorMessage = null;
		String userMessage = null;
		
		User u = new User();
		u.setUsername(sharedUser);
		
		//String chatroom = sharedChatroom;
		Post p = post;
		UserController uController = new UserController();
		ChatroomController cController = new ChatroomController();
		
		System.out.println(sharedUser);
		
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
				pc.post(u, p, chatroom);
				
				
			}
		}
		else if(req.getAttribute("exitP") != null){
			cController.permanentlyExitChatroom(u, chatroom);
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
