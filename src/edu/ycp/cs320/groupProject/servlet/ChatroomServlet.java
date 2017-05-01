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
		if(sharedChatroomName ==null){
			//System.out.println("    User: <" + sharedUser + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/chatroom");
			return;
		}
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("     User: <" + sharedUser + "> logged in");
		
		
		req.setAttribute("messages", messages);

		
		
		req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		sharedUser = (String) req.getSession().getAttribute("sharedUser");
		String errorMessage = null;
		String userMessage = null;
		
		User u = new User();
		u.setUsername(sharedUser);
		
		//String chatroom = sharedChatroom;
		
		UserController uController = new UserController();
		ChatroomController cController = new ChatroomController();
		
		System.out.println(sharedUser);
		
		try {
		
			//Is the problem that the post method doesn't know of this usermessage object?
			
			userMessage = (String) req.getParameter("source");
			//Added line
			
				if (pc.messageIsValid(userMessage) == true && userMessage!=null)
				{		
				post.setText(userMessage);
				}
			System.out.println("In the try in ChatroomServlet: the get from post.setText(userMessage) is this:" + post.getText() + "and this is usermessage:" + userMessage + "they should be the same"); 
					
					
		} catch (NumberFormatException e) {
			errorMessage = "Nothing";
		}
		
		
		
		if(req.getParameter("logout") != null){
			resp.sendRedirect("login");
			chatroom2= null;
			post = null;
			sharedUser= null;
			messages = null;
			chatroom2 = new Chatroom();
			post = new Post();
			messages = new ArrayList<String>();
			
		}
		else if(req.getParameter("send") != null){
			System.out.println("In send else-if, this is userMessage: " + post.getText());
	//		if(userMessage != null){
				//It's coming out null here but still printing...
				System.out.println("In userMessage statement, this is userMessage:" + post.getText());
				if(post.getText()!=null)
					pc.post(u, post, chatroom2);
				refresh = true;
				resp.sendRedirect("chatroom");
				req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);
				
				
//			}
		}
		else if (req.getParameter("Refresh")!= null)
		{
			resp.sendRedirect("chatroom");
			req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);
			
			
			chatroom2= null;
			post= null;
			messages = null;
			chatroom2 = new Chatroom();
			post = new Post();
			messages = new ArrayList<String>();
			
			
		}
		
		//Changed to req.getParameter from req.getAttribute(typo I think)
		else if(req.getParameter("exitP") != null){
			cController.permanentlyExitChatroom(u, chatroom2);
			resp.sendRedirect("chatroomList");
			chatroom2= null;
			post= null;
			messages = null;
			chatroom2 = new Chatroom();
			post = new Post();
			messages = new ArrayList<String>();
		
			
		}

		
		
		// Add parameters as request attributes
		//req.setAttribute("model", chatroom);
		
		req.setAttribute("messages", messages);
		req.setAttribute("post", post);

		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		
		
		//Start new
		/*
		posts = pc.getMessage(chatroom2);
		//System.out.println("Got Message? : " + !posts.isEmpty());
		if (posts != null)
		{
		
		
		for(Post po: posts){
			System.out.println(p.getText());
			messages.add(pc.formatMessage(p));
		}
		
		}
		
		else if (posts==null)
		{
			System.out.println("Posts is null");
		}
		*/
		//End new
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/chatroom.jsp").forward(req, resp);
		
		
	}//end doPost


	
	
	
	
}
