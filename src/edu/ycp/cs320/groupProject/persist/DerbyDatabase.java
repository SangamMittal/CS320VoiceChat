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
import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
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
					try
					{
						//Do we have many-to-many tables? Looks like userList is the only table with username
						stmt2 = conn.prepareStatement("select username.* from userList where username = ?" );
						stmt2.setString(1, u.getUsername());
						
						List<User> result = new ArrayList<User>();
						
						resultSet = stmt2.executeQuery();
						
						boolean found = false;
						
						
						while (resultSet.next())
						{
							found = true;	
							User user = new User(); //I'm not sure if this is correct
							loadUser(user, resultSet, 1);
							
							//result.add(new User(user.getUsername() ,user.getPassword() , false));
							return true;
							
						}
						
						if (!found)
						{
							System.out.println("<" + u.getUsername() + "> was not found in the books table");
						}
						
						return false;
						
					} finally {
						
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt2);
					}
				} //end execute
				});
	}
				
						
					
		private void loadUser(User u, ResultSet resultSet, int index) throws SQLException {
			
			u.setUserId(resultSet.getInt(index++));
			u.setUsername(resultSet.getString(index++));
			u.setPassword((resultSet.getString(index++)));
			
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
						stmt = conn.prepareStatement(" select username.* from userList where userList.username = ? and userList.password = ?  ");
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
	public Boolean deleteUser(User u)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;			
			try
			{
				stmt = conn.prepareStatement("delete from userList where username = ?" );
				stmt.setString(1, u.getUsername());	
				stmt.executeUpdate();
				
				return true;
				
			} finally {
				DBUtil.closeQuietly(stmt);
			}
		} 
		});	
	}
	
	public Boolean deleteChatroom(Chatroom c)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;			
			try
			{
				stmt = conn.prepareStatement("delete from chatroomList where username = ?" );
				stmt.setString(1, c.getChatroomName());	
				stmt.executeUpdate();
				
				return true;
				
			} finally {
				DBUtil.closeQuietly(stmt);
			}
		} 
		});
		
	}
	
	public Boolean createChatroom(Chatroom c, User u)
	{
		return executeTransaction(new Transaction<Boolean>()
		{
		@Override
		public Boolean execute(Connection conn) throws SQLException
		{
			PreparedStatement stmt= null;			
			try
			{
				stmt = conn.prepareStatement("insert into chatroomList (chatroom_name, password, admin_id, messages_id ) values (?,?,?,?) " );
				stmt.setString(1, c.getChatroomName());	
				stmt.setString(2, c.getPassword());	
				stmt.setInt(3, c.getAdminID());	
				stmt.setInt(4, c.getMessagesID());	
				stmt.executeUpdate();
				
				return true;
				
			} finally {
				DBUtil.closeQuietly(stmt);
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
			List<Post> postList = null;
			try
			{
				
				
				
				stmt = conn.prepareStatement("select * from ?Messages where roomID = ?" );
				
				stmt.setInt(1, getRoomID(c) );
				
				stmt.executeQuery();
				
				while (resultSet.next())
				{
					Post message = new Post();
					
					loadPost(message, resultSet, 1);
					
					postList.add(message);
				}
				
				
				
				return postList;
			
				
			
				
				
				
			} finally {
				DBUtil.closeQuietly(stmt);
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
				stmt = conn.prepareStatement("insert into ?Messages(sender_id, messageString) values(?, ?)" );
				stmt.setInt(1, getRoomID(c));
				stmt.setInt(2, p.getMessagesID());
				stmt.setString(3, p.getText());
				stmt.executeUpdate();
				
				boolean found = false;
				
				stmt2 = conn.prepareStatement("select from ?Messages where sender_id = ? and messageString = ?" );
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
				stmt = conn.prepareStatement("insert into chatroomUser(chatroom_id, user_id) values(?, ?)" );
				stmt.setInt(1, getRoomID(c));
				stmt.setInt(2, u.getUserID());
				stmt.executeUpdate();
				
				boolean found = false;
				
				stmt2 = conn.prepareStatement("select from chatroomUser where chatroom_id = ? and user_id = ?" );
				stmt2.setInt(1, getRoomID(c));
				stmt2.setInt(2, u.getUserID());
				
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
					System.out.println("<" + u.getUsername() + "> was not inserted");
				}
				
				return false;
				
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
				stmt = conn.prepareStatement(" select chatroomList.room_id from chatroomList where chatroom_name = ?");
				stmt.setString(1, c.getChatroomName());
				
				resultSet = stmt.executeQuery();
				
				boolean found = false;
				Integer result = 0;
				
				while (resultSet.next())
				{
					found = true;	
					Chatroom user = new Chatroom();
					loadChatroom(user, resultSet, 1);
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
	
	
	public void createTables()
	{
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3= null;
				PreparedStatement stmt4= null;
				
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
					
				//	stmt4= conn.prepareStatement("create table messagesList (messages_id int primary key generated always as identity (start with 1, increment by 1), textfile_Name varchar(70)" );
					//stmt4.executeUpdate();
					
					
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
				
				try {
					userList = InitialData.getUsers();
					chatroomList = InitialData.getChatroomList();
					chatroomUserList = InitialData.getChatroomUsers();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertChatroom = null;
				PreparedStatement insertUser   = null;
				PreparedStatement insertchatroomUserList= null;

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
