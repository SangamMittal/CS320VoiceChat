package edu.ycp.cs320.groupProject.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;

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
	
	
	

}
