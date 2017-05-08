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

public class ChatroomTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sharedUser;
	private String sharedChatroomName; // Changes here
	private Chatroom chatroom2;
	private PostController pc = new PostController();
	private Post post = new Post();
	private ArrayList<Post> posts = new ArrayList<Post>();
	private ArrayList<String> messages = new ArrayList<String>();
	private boolean refresh;
	
	public Post getChatroomServletPost()
	{
		return post;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		///if(refresh == true){
		//	resp.addHeader("Refresh", "1");
			refresh = false;
		//}

		//	String user = (String) req.getSession().getAttribute("sharedUser");
	
		//Only have 1 second to type in a message
		//resp.setIntHeader("Refresh", 10);
		
		
		sharedUser=	(String) req.getSession().getAttribute("sharedUser");
		sharedChatroomName = (String) req.getSession().getAttribute("sharedChatroomName");
		//System.out.println("pc.getMessage?");
		chatroom2 = new Chatroom();
		chatroom2.setChatroomName(sharedChatroomName);
		chatroom2.setChatroomName(chatroom2.getChatroomName());
		
		
		posts = pc.getMessage(chatroom2);
		//System.out.println("Got Message? : " + !posts.isEmpty());
		if (posts != null)
		{
		
		messages = null;
		messages = new ArrayList<String>();
		for(int i = posts.size()-1; i >= 0; i--){
			System.out.println(posts.get(i).getText());
			messages.add(pc.formatMessage(posts.get(i)));
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
		if(sharedChatroomName ==null){
			//System.out.println("    User: <" + sharedUser + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/chatroomText");
			return;
		}
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("     User: <" + sharedUser + "> logged in");
		
		
		req.getSession().setAttribute("messages", messages);
		

		
		
		req.getRequestDispatcher("/_view/chatroomText.jsp").forward(req, resp);

	}
	
	
	
	
	
	
}
