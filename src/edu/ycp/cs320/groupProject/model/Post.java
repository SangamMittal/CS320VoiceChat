package edu.ycp.cs320.groupProject.model;

public class Post {
	
	private String text;
	private int messages_id;
	
	
	
	public Post( )
	{
		
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
	


}
