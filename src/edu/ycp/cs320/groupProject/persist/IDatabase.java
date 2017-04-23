package edu.ycp.cs320.groupProject.persist;

import java.util.List;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

public interface IDatabase {

//done
	public Boolean signUp(User u);
//done
	public boolean Login(User u);
	
	//josh	
	public User deleteUser(User u);
	//josh
	public Boolean deleteChatroom(Chatroom c, User u);
	//josh
	public Boolean createChatroom(Chatroom c, User u);
	//josh
	public List<Post> selectMessages(Chatroom c);
	//rathana
	public Boolean removeUserFromChatroom(Chatroom c, User u);
	//rathana
	public User selectAdminFromChatroom(Chatroom c);
	//rathana
	public Boolean changeAdmin(Chatroom c, User u);
	//brad
	public Boolean insertMessages(Chatroom c, Post p);
	//brad
	public List<Chatroom> selectAllChatrooms();
	//brad
	public Boolean insertUserIntoChatroom(User u, Chatroom c);
	
	
	//josh-- selectUser
	
	public User selectUser(User u);
	
<<<<<<< HEAD
	//josh-- selectChatroom
	
	
	public Chatroom selectChatroom(Chatroom c);
	
=======
>>>>>>> branch 'master' of https://github.com/bruser15/CS320VoiceChat.git
	
	
	
	
	

	
	
}
