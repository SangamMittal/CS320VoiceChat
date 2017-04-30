package edu.ycp.cs320.groupProject.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

//Worked with Bradley and Rathana.
//I helped figure out the fake database with Bradley for the lastNameQuery.
//Bradley, Rathana and I made progress on the InsertBook class, particularly Bradley
//Bradley and Rathana helped me with implementing insertBook in Derby Database.
//Josh: 20%
//Rathana: 35%
//Bradley: 45%


public class FakeDatabase implements IDatabase {
	
	
	
	public FakeDatabase() {
		
		
		// Add initial data
	
		
		
	}
	

	@Override
	public Boolean signUp(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Login(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User deleteUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteChatroom(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createChatroom(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> selectMessages(Chatroom c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeUserFromChatroom(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectAdminFromChatroom(Chatroom c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changeAdmin(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertMessages(Chatroom c, Post p, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chatroom> selectAllChatrooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertUserIntoChatroom(User u, Chatroom c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chatroom selectChatroom(Chatroom c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUserByID(User u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	private Book findBookByAuthorId(int authorId) {
		for (Book book : bookList) {
			if (book.getAuthorId() == authorId) {
				return book;
			}
		}
		return null;
	}
	*/
	
}
