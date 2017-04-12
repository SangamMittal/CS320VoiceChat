package edu.ycp.cs320.groupProject.model;

public class Chatroom {
	
	private String chatroomname;
	private String password;
	private boolean hasPassword;
	private int numUser;
	private String messages;
	private int chatroom_id;
	private int admin_id;
	private int messages_id;
	
	public Chatroom(){
		
	}
	
	public void setChatroomID(int chatroom_id) {
		this.chatroom_id = chatroom_id;
	}
	
	public void setAdminID(int admin_id) {
		this.chatroom_id = chatroom_id;
	}
	
	public void setMessagesID(int messages_id) {
		this.messages_id = messages_id;
	}
	
	
	public Chatroom(String name, String password, boolean hasPassword){
		
		this.chatroomname = chatroomname;
		this.password = password;
		this.hasPassword= hasPassword;
		
	}
	
	public void setMaxNumber(int numUser)
	{
		this.numUser = numUser;
	}
	
	public int getMaxNumber()
	{
		return numUser;
	}
	
	public String getChatroomName(){
		return this.chatroomname;
	}
	
	public void setChatroomName(String chatroomname){
		this.chatroomname = chatroomname;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public int getNumUser(){
		return this.numUser;
	}
	
	public int getAdminID(){
		return this.admin_id;
	}
	
	public int getMessagesID(){
		return this.messages_id;
	}
	
	public void setNumUser(int numUser){
		this.numUser = numUser;
	}
	
	public void setMessages(String messag){
		this.messages = messag;
	}
	
	public String getMessages(){
		return this.messages;
	}

}
