package edu.ycp.cs320.groupProject.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;
import edu.ycp.cs320.groupProject.model.Post;


public class ChatroomController {
	
	private IDatabase db = null;

	public ChatroomController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}
	
	public ArrayList<Post> allMessages(Chatroom chatroom){
		List<Post> messagesList = db.selectMessages(chatroom);
		ArrayList<Post> allMessages = null;
		
		if (messagesList.isEmpty()) {
			System.out.println("No messages found in chatroom: " + chatroom.getChatroomName());
			return null;
		}
		else {
			allMessages = new ArrayList<Post>();
			for (Post message : messagesList) {
				allMessages.add(message);
				System.out.println(message.getText());
			}			
		}
		
		return allMessages;
	}
	
	public boolean permanentlyExitChatroom(User user, Chatroom chatroom){
		boolean success = false;
		
		// Contact the databse
		// Database Method: check to see if the user exist in that chatroom
		//			If the user does, then remove it from the database and return true
		//			else do nothing and return false;
		String admin = db.selectAdminFromChatroom(chatroom).getUsername();
		if(user.getUsername().equals(admin)){
			System.out.println("Cannot delete yourself");
			System.out.println("Transfer your admin control to someone first!");
			success = false;
		}
		else{
			success = db.removeUserFromChatroom(chatroom, user);
		}
		
		return success;
		
	}
	
	public boolean create(Chatroom c, User u){
		boolean success = false;
		boolean nameTaken = false;
		// Contact the database
		// Database Method: check to see if chatroom name is taken
		//				if not create the chatroom
		List<Chatroom> listOfChatrooms = db.selectAllChatrooms();
		for(Chatroom room: listOfChatrooms){
			if(room.getChatroomName().equals(c.getChatroomName())){
				nameTaken = true;
				break;
			}
		}
		
		if(nameTaken == true){
			System.out.println("Chatroom name already taken!");
		}
		else{
			db.createChatroom(c, u);
		}
		
		return success;
	}
	
	public ArrayList<Chatroom> getAllChatroom(){
		List<Chatroom> listChatrooms = null;
		ArrayList<Chatroom> allChatrooms = null;
		//Contact the database
		// selectAllChatrooms
		listChatrooms = db.selectAllChatrooms();
		
		if (listChatrooms.isEmpty()) {
			System.out.println("No Chatroom Found");
			return null;
		}
		else {
			allChatrooms = new ArrayList<Chatroom>();
			for (Chatroom room : listChatrooms) {
				allChatrooms.add(room);
				System.out.println("Room: " + room.getChatroomName());
			}			
		}
		
		return allChatrooms;
	}
	
	
	
	/*
	public void appearVoiceIcon()
	{
		
	}
	
	public void popUpAppear(String msg)
	{
		
	}
	*/

}
