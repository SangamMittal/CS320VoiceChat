package edu.ycp.cs320.groupProject.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.groupProject.model.Admin;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;

public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> UserList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("userList.csv");
		try {
			// auto-generated primary key for authors table
			Integer user_id = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();
				user.setUserId(user_id++);				
				user.setUsername(i.next());
				user.setPassword(i.next());
				UserList.add(user);
			}
			return UserList;
		} finally {
			readUsers.close();
		}
	}
	
	//for the chatroomUser list, what are we getting? and what type is the list?
	public static List<User> getChatroomUsers() throws IOException {
		List<User> ChatroomUserList = new ArrayList<User>();
		ReadCSV readChatroomUsers = new ReadCSV("chatroomUser.csv");
		try {
			// auto-generated primary key for authors table
			Integer num = 1;
			while (true) {
				List<String> tuple = readChatroomUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();
				user.setNum(num++);				
				user.setChatroomId(Integer.parseInt(i.next()));
				ChatroomUserList.add(user); //is it correct to add to the userList?
			}
			return ChatroomUserList;
		} finally {
			readChatroomUsers.close();
		}
	}
	
	public static List<Post> getPosts() throws IOException {
		List<Post> PostList = new ArrayList<Post>();
		ReadCSV readPosts = new ReadCSV("messagesList.csv");
		try {
			// auto-generated primary key for authors table
			Integer messages_id = 1;
			while (true) {
				List<String> tuple = readPosts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Post post = new Post();
				post.setMessagesID(messages_id++);				
				post.setText(i.next());
				
				PostList.add(post);
			}
			return PostList;
		} finally {
			readPosts.close();
		}
	}
	
	public static List<Chatroom> getChatroomList() throws IOException {
		List<Chatroom> chatroomList = new ArrayList<Chatroom>();
		ReadCSV readChatrooms = new ReadCSV("chatroomList.csv");
		try {
			// auto-generated primary key for books table
			Integer chatroomId = 1;
			while (true) {
				List<String> tuple = readChatrooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Chatroom chatroom = new Chatroom();
				chatroom.setChatroomID(chatroomId++);
				chatroom.setChatroomName(i.next());
				chatroom.setPassword(i.next());
				chatroom.setAdminID(Integer.valueOf(i.next())); //?
				chatroom.setMessagesID( Integer.valueOf(i.next()) ); //are these two correct?
				
				
				chatroomList.add(chatroom);
			}
			return chatroomList;
		} finally {
			readChatrooms.close();
		}
	}
}
