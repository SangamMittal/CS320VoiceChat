package edu.ycp.cs320.groupProject.model;

public class Post {
	
	private String text;
	private int messages_id;
	private String user;
	private int sender_id;
	private int room_id;
	
	
	public Post( )
	{
		
	}
	
	public int getRoomID()
	{
		return room_id;
	}
	
	public void setRoomID(int room_id)
	{
		this.room_id = room_id;
	}
	
	public int getSenderID()
	{
		return sender_id;
	}
	
	public void setSenderID(int sender_id)
	{
		this.sender_id = sender_id;
	}
	
	public void setMessagesID(int messages_id) {
		this.messages_id = messages_id;
	}
	
	public int getMessagesID()
	{
		return messages_id;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text= text;
	}
	
	public void setSenderName(String name){
		user = name;
	}
	
	public String getSenderName(){
		return user;
	}
	
}
