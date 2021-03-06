package edu.ycp.cs320.groupProject.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;
//hey
public class PostController {
	
	private IDatabase db = null;
	private Boolean inserted = false;
	private List<Post> selected= new ArrayList<Post>();
	
	public PostController()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	
	
	public Boolean post(User u, Post post, Chatroom c)
	{
		inserted= db.insertMessages(c, post, u);
		
		return inserted;
		
		//throw new UnsupportedOperationException("TODO - implement");
		
	}
	
	public ArrayList<Post> getMessage(Chatroom c)
	{
		List<Post> postList = db.selectMessages(c);
		ArrayList<Post> postArrayList = null;
		
		if (postList.isEmpty()) {
			System.out.println("No posts yet in this chatroom:" + c.getChatroomName());
			return null;
		}
		else {
			postArrayList = new ArrayList<Post>();
			for (Post p : postList) {
				postArrayList.add(p);
				
				System.out.println(p.getText());
			}			
		}
		
		// return authors for this title
		return postArrayList;
	}
	public String formatMessage(Post p){
		User u = new User();
		u.setUserId(p.getSenderID());
		u = db.selectUserByID(u);
		String message = "[" + u.getUsername() + "]: " + p.getText() + "\n";
		return message;
	}
	
	public boolean messageIsValid(String s)
	{
		//ArrayList<Character> characters = new ArrayList<Character>();
		
		for (int i=0; i<s.length(); i++)
		{
			
			if ( (s.charAt(i) != ' ' ) )
			{
				return true;
			}
			
			
		}
			return false;
		
		
		
	}
	
	

}
