package edu.ycp.cs320.groupProject.model;

public class Chatroom {
	
	private String name;
	private String password;
	private boolean hasPassword;
	private int numUser;
	private String messages;
	
	public Chatroom(){
		
	}
	
	public Chatroom(String name, String password, boolean hasPassword){
		
		this.name = name;
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
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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
