package edu.ycp.cs320.groupProject.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import edu.ycp.cs320.groupProject.persist.DBUtil;
import edu.ycp.cs320.groupProject.persist.InitialData;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.Post;
import edu.ycp.cs320.groupProject.model.User;

//test made method public
import edu.ycp.cs320.groupProject.persist.DerbyDatabase.Transaction;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	public interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	@Override
	public Boolean signUp(User u)
	{
		return executeTransaction(new Transaction<Boolean>()
				{
				@Override
				public Boolean execute(Connection conn) throws SQLException
				{
					PreparedStatement stmt= null;		
					PreparedStatement stmt2= null;
					ResultSet resultSet = null;
					ResultSet resultSet2= null;
					try
					{
						//select all tuples from userList where username is given, so just that tuple
						stmt = conn.prepareStatement("select * from userList where username = ?" );
						stmt.setString(1, u.getUsername());
						
						List<User> result = new ArrayList<User>();
						
						//execute statement
						resultSet = stmt.executeQuery();
						
						boolean found = false;
						
						//for each username,
						while (resultSet.next())
						{
							found = true;	
							User user = new User();
							
							//take the tuple elements we got in the statement and put them in the user object
							loadUser(user, resultSet, 1);
							
							//result.add(new User(user.getUsername() ,user.getPassword() , false));
							return false;
							
						}
						//if the resultSet comes up empty, the username is new
						if (!found)
						{
							//
							System.out.println("<" + u.getUsername() + "> was not found in the userList table");
							
							//insert into userList the username you enter
							stmt2 =conn.prepareStatement("insert into userList (username, password) values (?,?)");
							stmt2.setString(1, u.getUsername());
							stmt2.setString(2, u.getPassword());
							
							stmt2.executeUpdate();
							
							return true;
						}
						
						else return false;
						
						
						
					} finally {
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet2);
						DBUtil.closeQuietly(stmt2);
						
					}
				} //end execute
				});
	}
				
						
					
		private void loadUser(User u, ResultSet resultSet, int index) throws SQLException {
			
			u.setUserId(resultSet.getInt(index++));
			u.setUsername(resultSet.getString(index++));
			u.setPassword(resultSet.getString(index++));
			
		}
	
//make return a list of users?
	@Override
	public boolean Login(User u)
	{
		return executeTransaction(new Transaction<Boolean>()
				{
				@Override
				public Boolean execute(Connection conn) throws SQLException
				{
					PreparedStatement stmt= null;	
					ResultSet resultSet = null;
					try
					{
						//select all tuples from userList where username and password are givens (it'll be 1 tuple, then)
						stmt = conn.prepareStatement(" select * from userList where userList.username = ? and userList.password = ?  ");
						stmt.setString(1, u.getUsername() );
						stmt.setString(2, u.getPassword());
						
						List<User> result = new ArrayList<User>();
						
						
						resultSet = stmt.executeQuery();
						
						boolean found = false;
						
						
						while (resultSet.next())
						{
							found = true;	
							User user = new User(); //I'm not sure if this is correct
							loadUser(user, resultSet, 1);
							
							return true;
							
						}
						
						if (!found)
						{
							System.out.println("<" + u.getUsername() + "> was not found in the books table");
						}
						
						return false;
					}
					finally {
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(resultSet);
					}	
				}
				}
				);
	}
	
	//work on: when does it return true vs when does it return false;
	@Override
	public User deleteUser(User u)
	{
		return executeTransaction(new Transaction<User>()
		{
		@Override
		public User execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;	
			PreparedStatement stmt2 = null;
			PreparedStatement stmt3 = null;
			ResultSet resultSet3 = null;
			try
			{
				// Getting that user
				User uReturn = new User();
				uReturn = selectUser(u);
				System.out.println(uReturn.getUsername());
				
				//Deleting that user from userList table
				stmt = conn.prepareStatement("delete from userList where username = ?" );
				stmt.setString(1, u.getUsername());	
				stmt.executeUpdate();
				
				//Delete that user from chatroomUser table
				stmt2 = conn.prepareStatement("delete from chatroomUser where user_id = ?");
				stmt2.setInt(1, uReturn.getUserID());
				stmt2.executeUpdate();
			
				// Try to select that user again
				String uTry = null;
				stmt3 = conn.prepareStatement(" select username from userList where username = ? ");
				stmt3.setString(1, u.getUsername());
				resultSet3 = stmt3.executeQuery();
				if(resultSet3.next())
					uTry = resultSet3.getString(1);
				
				// Check to if removal was successful
				if(uTry == null){
					System.out.println("User: " + u.getUsername() + " is deleted!");
				}
				else{
					System.out.println("UNABLE to delete user: " + u.getUsername());
					uReturn = null;
				}

				return uReturn;
				
			} finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(stmt2);
				DBUtil.closeQuietly(stmt3);
				DBUtil.closeQuietly(resultSet3);

			}
		} 
		});	
	}
	
	@Override
	public User selectUser(User u)
	{
		return executeTransaction(new Transaction<User>()
		{
		@Override
		public User execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;
			ResultSet resultSet= null;
			String username= null;
			User uReturn = new User();
			try
			{
				// Getting the user based on username
				stmt = conn.prepareStatement("select * from userList where username = ?" );
				stmt.setString(1, u.getUsername());	
				resultSet= stmt.executeQuery();
			
				//while (resultSet.next()){
				if(resultSet.next() != false){
					if(u.getUsername().equals(resultSet.getString(2)))
						loadUser(uReturn, resultSet, 1);
				}
				else{
					uReturn = null;
				}
				//}
				

				return uReturn;
				
			} finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(resultSet);
			}
		} 
		});	
	}
	
	@Override
	public Chatroom selectChatroom(Chatroom c)
	{
		return executeTransaction(new Transaction<Chatroom>()
		{
		@Override
		public Chatroom execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;
			ResultSet resultSet= null;
			String chatroomName= null;
			try
			{
				stmt = conn.prepareStatement("select * from chatroomList where chatroom_name = ?" );
				stmt.setString(1, c.getChatroomName());	
				resultSet= stmt.executeQuery();
			
				while (resultSet.next()){
					
				chatroomName = resultSet.getString(2);	
				}
				

				return c;
				
			} finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(resultSet);
			}
		} 
		});	
	}
	
	@Override
	public Boolean deleteChatroom(Chatroom c, User u)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt0= null;
			PreparedStatement stmt= null;
			PreparedStatement stmt2= null;
			PreparedStatement stmt3= null;
			ResultSet resultSet3 = null;
			Boolean success = false;
			try
			{
				// Getting roomID
				int roomID = getRoomID(c);
				
				// Delete chatroom in chatroomUser
				stmt0 = conn.prepareStatement("delete from chatroomUser where chatroomUser.room_id = ?");
				stmt0.setInt(1, roomID);
				stmt0.executeUpdate();
				
				// Delete chatroom in chatroomList
				stmt = conn.prepareStatement("delete from chatroomList where chatroomList.room_id = ?" );
				stmt.setInt(1, roomID);	
				stmt.executeUpdate();
				
				// Delete chatroom messages
				stmt2 = conn.prepareStatement("delete from messagesList where chatroom_id = ?");
				stmt2.setInt(1, roomID);	
				stmt2.executeUpdate();
				
				// Trying to get that deleted chatroom
				String cTry = null;
				stmt3 = conn.prepareStatement("select chatroom_name from chatroomList where chatroomList.chatroom_name = ?");
				stmt3.setString(1, c.getChatroomName());
				resultSet3= stmt3.executeQuery();
				if(resultSet3.next())
					cTry = resultSet3.getString(1);

				// Checking if it was deleted
				if(cTry != null)
				{
					System.out.println("Delete Chatroom method: Entered while loop, so there are chatrooms in the chatroomList, should return false");
					success = false;
				}
				else{
					success = true;
				}
				
				
				return success;
				
			} finally {
				DBUtil.closeQuietly(stmt0);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(stmt2);
				DBUtil.closeQuietly(stmt3);
				DBUtil.closeQuietly(resultSet3);
			}
		} 
		});
		
	}
	
/*
	@Override
	public Boolean deleteChatroom(Chatroom c, User u)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt0= null;
			PreparedStatement stmt= null;
			PreparedStatement stmt2= null;
			try
			{
				// Getting room_id
				int roomID = getRoomID(c);
				
				// Deleting chatroom in chatroomUser table
				stmt0 = conn.prepareStatement("delete from chatroomUser where room_id = ?");
				stmt0.setInt(1, roomID);
				stmt0.executeUpdate();
				
				// Deleting chatroom in chatroomList table
				stmt = conn.prepareStatement("delete from chatroomList where chatroomList.room_id=?" );
				stmt.setInt(1, roomID);	
				stmt.executeUpdate();
				
				// Deleting all chatroom messages
				stmt2 = conn.prepareStatement("delete from messagesList where chatroom_id = ?");
				stmt2.setInt(1, roomID);	
				stmt2.executeUpdate();
				
				// Checking to see if the chatroom are still there
// ***************************************** WORKING HERE ********************************************************				
				
				return true;
				
			} finally {
				DBUtil.closeQuietly(stmt0);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(stmt2);
			}
		} 
		});
		
	}
*/
	@Override
	public Boolean createChatroom(Chatroom c, User u)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt0= null;
			ResultSet resultSet0 = null;
			PreparedStatement stmt= null;
			PreparedStatement stmt2=null;
			ResultSet resultSet2 = null;
			Boolean success = false;
			String roomName = c.getChatroomName();
			try
			{
				// Getting Admin_id
				int adminID = 0;
				stmt0 = conn.prepareStatement(" select user_id from userList where username = ?");
				stmt0.setString(1, u.getUsername());
				resultSet0 = stmt0.executeQuery();
				if(resultSet0.next())
					adminID = resultSet0.getInt(1);
				System.out.println(adminID);
				
				// Insert new chatroom
				stmt = conn.prepareStatement(" insert into chatroomList (chatroom_name, password, admin_id, messages_id ) values (?,?,?,?) " );
				stmt.setString(1, roomName);	
				stmt.setString(2, c.getPassword());	
				stmt.setInt(3, adminID);	
				stmt.setInt(4, c.getMessagesID());	
				stmt.executeUpdate();
				
				//Trying to get that chatroom
				stmt2= conn.prepareStatement(" select chatroom_name from chatroomList where chatroomList.chatroom_name=?  ");
				stmt2.setString(1, roomName);
				resultSet2= stmt2.executeQuery();
				if(resultSet2.next())
				{	
					success= true;
					System.out.println(success);
					System.out.println("In while loop after success is set to true");
				}
				
				return success;
				
				
				
			} finally {
				DBUtil.closeQuietly(stmt0);
				DBUtil.closeQuietly(resultSet0);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(stmt2);
				DBUtil.closeQuietly(resultSet2);

			}
		} 
		});
		
	}
	
	public List<Post> selectMessages(Chatroom c)
	{
		return executeTransaction(new Transaction<List<Post>>()
		{
		@Override
		public List<Post> execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;
			ResultSet resultSet = null;
			List<Post> postList = new ArrayList<Post>();
			try
			{
				stmt = conn.prepareStatement("select * from postContents, chatroomList "
						+ " where postContents.room_ID = ? "
						+ " and chatroomList.room_ID = postContents.room_ID" );
				//setting the second argument to 1 right now rather than getRoomID(c) just for the test's sake
				//until we can figure it out
				//I think there may be bugs in getRoomID(c)
				
				stmt.setInt(1, 1);
				resultSet= stmt.executeQuery();
				
				while (resultSet.next())
				{
					Post message = new Post();		
					loadPost(message, resultSet, 1);
					postList.add(message);
				}
				
				return postList;
				
			} finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(resultSet);
			}
		} 
		});
		
		
		
	}
	
	
	
	public void loadPost(Post p, ResultSet resultSet, int index)
			throws SQLException {
		p.setText(resultSet.getString(index++));
		p.setMessagesID(resultSet.getInt(index++));
	
	}
	
	@Override
	public Boolean insertMessages(Chatroom c, Post p){
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;
			PreparedStatement stmt2= null;
			ResultSet resultSet = null;
			try
			{
				stmt = conn.prepareStatement("insert into messagesList (chatroom_id, sender_id, messageString) values(?, ?, ?)" );
				stmt.setInt(1, getRoomID(c));
				stmt.setInt(2, p.getMessagesID());
				stmt.setString(3, p.getText());
				stmt.executeUpdate();
				
				boolean found = false;
				
				stmt2 = conn.prepareStatement("select from messagesList where chatroom_id = ? and sender_id = ? and messageString = ?" );
				stmt2.setInt(1, getRoomID(c));
				stmt2.setInt(2, p.getMessagesID());
				stmt2.setString(3, p.getText());
				
				resultSet = stmt2.executeQuery();
				
				while (resultSet.next())
				{
					found = true;	
					User user = new User();
					loadUser(user, resultSet, 1);
					
					return true;
					
				}
				
				if (!found)
				{
					System.out.println("<" + p.getText() + "> was not inserted");
				}
				
				return false;
				
			} finally {
				
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt2);
			}
		} //end execute
		});
	}
	private void loadChatroom(Chatroom c, ResultSet resultSet, int index) throws SQLException {
		c.setChatroomID(resultSet.getInt(index++));
		c.setChatroomName(resultSet.getString(index++));
		c.setPassword(resultSet.getString(index++));
		c.setAdminID(resultSet.getInt(index++));
		
	}
	@Override
	public List<Chatroom> selectAllChatrooms(){
		return executeTransaction(new Transaction<List<Chatroom>>()
		{
		@Override
		public List<Chatroom> execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;	
			ResultSet resultSet = null;
			try
			{
				stmt = conn.prepareStatement(" select * from chatroomList");
				
				List<Chatroom> result = new ArrayList<Chatroom>();
				
				resultSet = stmt.executeQuery();
				
				boolean found = false;
				
				
				while (resultSet.next())
				{
					found = true;	
					Chatroom user = new Chatroom();
					loadChatroom(user, resultSet, 1);
					result.add(user);
				}
				
				if (!found)
				{
					System.out.println("There are no Chatrooms.");
				}
				
				return result;
			}
			finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(resultSet);
			}	
		}
		}
		);
	}
	
	@Override
	public Boolean insertUserIntoChatroom(User u, Chatroom c){
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;
			PreparedStatement stmt2= null;
			ResultSet resultSet = null;
			try
			{
				stmt = conn.prepareStatement("insert into chatroomUser(room_id, user_id) values(?, ?)" );
				stmt.setInt(1, getRoomID(c));
				stmt.setInt(2, u.getUserID());
				stmt.executeUpdate();
				
				return true;
				/*
				boolean found = false;
				
				stmt2 = conn.prepareStatement("select * from chatroomUser where room_id = ? and user_id = ?" );
				stmt2.setInt(1, getRoomID(c));
				stmt2.setInt(2, u.getUserID());
				
				resultSet = stmt2.executeQuery();
				
				while (resultSet.next())
				{
					found = true;	
					
					return true;
					
				}
				
				if (!found)
				{
					System.out.println("<" + u.getUsername() + "> was not inserted");
				}
				
				return false;
				*/
			} finally {
				
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt2);
			}
		} //end execute
		});
	}
	
	private int getRoomID(Chatroom c){
		return executeTransaction(new Transaction<Integer>()
		{
		@Override
		public Integer execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;	
			ResultSet resultSet = null;
			try
			{
				stmt = conn.prepareStatement("select chatroomList.room_id from chatroomList where chatroom_name = ?");
				stmt.setString(1, c.getChatroomName());
				
				resultSet = stmt.executeQuery();
				
				boolean found = false;
				Integer result = 0;
				
				while (resultSet.next())
				{
					found = true;	
					result = resultSet.getInt(1);
					
				}
				
				if (!found)
				{
					System.out.println("There are no Chatrooms with that name");
				}
				
				return result;
			}
			finally {
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(resultSet);
			}	
		}
		}
		);
	}
	

	
	
	@Override
	public Boolean removeUserFromChatroom(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				PreparedStatement stmt2 = null;	
				PreparedStatement stmt3 = null;
				ResultSet resultSet3 = null;
				User user = new User();
				Chatroom room = new Chatroom();
				Boolean successORnot = false;
				
				try
				{
					//Getting roomID
					int roomID = 0;
					stmt = conn.prepareStatement(
							" Select room_id from chatroomList where chatroom_name = ? "
					);
					stmt.setString(1, "TestingRemove");
					resultSet = stmt.executeQuery();
					//System.out.println(resultSet.next());
					
					if(resultSet.next())
						roomID = resultSet.getInt(1);
					System.out.println("RoomID: " + roomID);

					
					// Getting the user row
					stmt1 = conn.prepareStatement(
							"Select userList.user_id from userList " +
							" where userList.username = ? "
					);

					stmt1.setString(1, u.getUsername());
					resultSet1 = stmt1.executeQuery();
					resultSet1.next();
					int user_id = resultSet1.getInt(1);



					// Deleting: using user_id and room_id
					stmt2 = conn.prepareStatement(
							"Delete from chatroomUser " +
							" where chatroomUser.user_id = ? and " +
							" chatroomUser.room_id = ? "
					);
					stmt2.setInt(1, user_id);
					stmt2.setInt(2, roomID);
					stmt2.executeUpdate();
					
					// Check to see if it is still there or not
					stmt3 = conn.prepareStatement(
							"Select chatroomUser.user_id from chatroomUser " +
							" where chatroomUser.user_id = ? and " +
							" chatroomUser.room_id = ? "
					);
					stmt3.setInt(1, user_id);
					stmt3.setInt(2, roomID);
					resultSet3 = stmt3.executeQuery();
					
					if(!resultSet3.next())
						successORnot = true;
					else
						successORnot = false;
					
					
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet3);

				}
				return successORnot;

			}//end of execute
		}
		);
	}//end of removeUserFromChatroom



	@Override
	public User selectAdminFromChatroom(Chatroom c) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>()
		{
			@Override
			public User execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt0 = null;
				ResultSet resultSet0 = null;
				PreparedStatement stmt1= null;
				ResultSet resultSet1= null;
				User user = new User();
				Chatroom room = new Chatroom();

				try
				{
					int roomID = getRoomID(c);
					System.out.println("Room ID: "+ roomID);
					// Getting the adminID
					stmt0 = conn.prepareStatement(
							"Select chatroomList.admin_id from chatroomList " +
							" where chatroomList.room_id = ?"
					);
					stmt0.setInt(1, roomID);
					resultSet0 = stmt0.executeQuery();
					resultSet0.next();
					int adminID = resultSet0.getInt(1);
					System.out.println("Admin ID: " + adminID);
					// Get the Admin User
					stmt1 = conn.prepareStatement(
							" select * " +
							" from userList " +
							" where userList.user_id = ?"
					);
					stmt1.setInt(1, adminID);
					resultSet1 = stmt1.executeQuery();
					while (resultSet1.next())
					{
						user = new User();
						loadUser(user, resultSet1, 1);
						return user;
					}
					//loadUser(user, resultSet1, 1);
					
					return user;
				}
				finally {
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(resultSet0);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(resultSet1);
				}	
			}//end of execute
		}
		);
		
	}//end of selectAdminFromChatroom



	@Override
	public Boolean changeAdmin(Chatroom c, User u) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Boolean>()
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException
			{
				PreparedStatement stmt= null;	
				ResultSet resultSet= null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet3= null;
				Boolean successORnot = false;

				User adminNew = new User();
				int adminNewID = 0;
				try
				{
					// Getting the new admin information
					stmt = conn.prepareStatement(
							"select userList.user_id " +
							" from userList " +
							" where userList.username = ?"
					);
					stmt.setString(1, u.getUsername());
					resultSet = stmt.executeQuery();
					//loadUser(adminNew, resultSet, 1);
					if(resultSet.next())
						adminNewID = resultSet.getInt(1);
					System.out.println("New Admin ID: " + adminNewID);
					// Change the admin
					stmt2 = conn.prepareStatement(
							"update chatroomList " +
							" set admin_id = ? " +
							" where chatroomList.chatroom_name = ? "
					);
					stmt2.setInt(1, adminNewID);
					stmt2.setString(2, c.getChatroomName());
					stmt2.executeUpdate();
					
					// Check if the change was successful
					stmt3 = conn.prepareStatement(
							" select chatroomList.admin_id " +
							" from chatroomList " +
							" where chatroomList.chatroom_name = ? "		
					);
					stmt3.setString(1, c.getChatroomName());
					resultSet3 = stmt3.executeQuery();
					if(resultSet3.next()){
						if(resultSet3.getInt(1) == adminNewID){
							successORnot = true;
						}
					}
					
				return successORnot;
				}
				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(resultSet3);

				}	
			}//end of execute
		}
		);

	}// end of changeAdmin
	

	
	public void createTables()
	{
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3= null;
				PreparedStatement stmt4= null;
				PreparedStatement stmt5=null;
				
				try {
					stmt1 = conn.prepareStatement(
					" create table chatroomList (room_id int primary key generated always as identity (start with 1, increment by 1), chatroom_name varchar(32), password varchar(32), admin_id int, messages_id int)"
					);	
					stmt1.executeUpdate();
					
				stmt2 = conn.prepareStatement(
							"create table userList (" +
							"	user_id int " +
							"	primary key	generated always as identity (start with 1, increment by 1), " +
							"	username varchar(32), " +
							"	password varchar(32) " +
							
						
							")"
					);
					
					
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement("create table chatroomUser (num int primary key generated always as identity (start with 1, increment by 1),room_id int, user_id int)");
					stmt3.executeUpdate();
					
					stmt4= conn.prepareStatement("create table messagesList (chatroom_id int, sender_id varchar(32), messageString varchar(70))" );
					stmt4.executeUpdate();
					
					stmt5= conn.prepareStatement("create table postContents (messageString varchar(70), user_id int, room_id int)");
					stmt5.executeUpdate();
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> userList;
				List<Chatroom> chatroomList;
				List<User> chatroomUserList;
				List<Post> messagesList;
				List<Post> postContents;
				
				try {
					userList = InitialData.getUsers();
					chatroomList = InitialData.getChatroomList();
					chatroomUserList = InitialData.getChatroomUsers();
					messagesList= InitialData.getPosts();
					postContents= InitialData.getPosts2();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertChatroom = null;
				PreparedStatement insertUser   = null;
				PreparedStatement insertchatroomUserList= null;
			
				PreparedStatement insertMessagesList= null;
				PreparedStatement insertPostContents= null;

				try {
					// populate authors table (do authors first, since author_id is foreign key in books table)
					insertChatroom = conn.prepareStatement("insert into chatroomList (chatroom_name, password, admin_id, messages_id) values (?, ?, ?, ?)");
					for (Chatroom chatroom : chatroomList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertChatroom.setString(1, chatroom.getChatroomName() );
						insertChatroom.setString(2, chatroom.getPassword());
						insertChatroom.setInt(3, chatroom.getAdminID());
						insertChatroom.setInt(4, chatroom.getMessagesID());
			
						insertChatroom.addBatch();
					}
					insertChatroom.executeBatch();
					
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					insertUser = conn.prepareStatement("insert into userList (username, password) values (?, ?)");
					for (User user : userList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					insertchatroomUserList = conn.prepareStatement("insert into chatroomUser (room_id, user_id) values (?, ?)");
					for (User user: chatroomUserList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertchatroomUserList.setInt(1, user.getChatroomId() );
						insertchatroomUserList.setInt(2, user.getUserID() );
						
			
						insertchatroomUserList.addBatch();
					}
					insertchatroomUserList.executeBatch();
					
					/////////////////////////////////////
					
					insertMessagesList = conn.prepareStatement("insert into messagesList (chatroom_id, sender_id, messageString) values (?, ?, ?)");
					for (Post post: messagesList) {
//						
						insertMessagesList.setInt(1, post.getRoomID() );
						insertMessagesList.setInt(2, post.getSenderID() );
						insertMessagesList.setString(3, post.getText());
						
						
			
						insertMessagesList.addBatch();
					}
					insertMessagesList.executeBatch();
					
					//////////////////////////////////////
					
					insertPostContents = conn.prepareStatement("insert into postContents (messageString, user_id, room_id) values (?, ?, ?)");
					for (Post post: postContents) {
//						
						insertPostContents.setString(1, post.getText() );
						insertPostContents.setInt(2, post.getSenderID() );
						insertPostContents.setInt(3, post.getRoomID());
						
						
			
						insertPostContents.addBatch();
					}
					insertPostContents.executeBatch();
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertChatroom);
					DBUtil.closeQuietly(insertchatroomUserList);
				}
			}
		});
	}
	
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	


	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
}