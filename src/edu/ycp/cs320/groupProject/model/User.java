package edu.ycp.cs320.groupProject.model;

import java.io.*;
import java.net.*;
import java.util.TreeMap;

public class User{
	
	private String username;
	private String password;
 	private boolean admin;
 	private boolean isItLogined;
	private TreeMap<String, String> UserList;
	private int user_id;
	private int chatroom_id;
	private int num;
 	

	// Blank constructor
	public User(){
		
	}
	
	//constructor
	public User(String username, String password, boolean admin)
	{
		this.username = username;
		this.password= password;
		this.admin = admin;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public boolean getAdmin()
	{
		return admin;
	}
	
	public void setUsername(String username)
	{
		this.username= username;
		
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	
	public void setIsItLogined(boolean loginState){
		this.isItLogined = loginState;
	}
	
	public boolean getIsItLogined(){
		return isItLogined;
	}


	
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
	public void setChatroomId(int chatroom_id) {
		this.chatroom_id = chatroom_id;
	}
	
	
	public void setNum(int num) {
		this.num = num;
	}
	
		
		
	


}
