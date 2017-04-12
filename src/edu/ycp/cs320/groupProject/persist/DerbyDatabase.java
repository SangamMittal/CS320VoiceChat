package edu.ycp.cs320.groupProject.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.model.User;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase.Transaction;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	@Override
	public List<User> signUp(User u)
	{
		return executeTransaction(new Transaction<List<User>>()
				{
				@Override
				public List<User> execute(Connection conn) throws SQLException
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
							
							result.add(new User(user.getUsername() ,user.getPassword() , false));
							
							
						}
						
						if (!found)
						{
							System.out.println("<" + u.getUsername() + "> was not found in the books table");
						}
						
						return result;
						
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
	public User Login(User u)
	{
		return executeTransaction(new Transaction<User>()
				{
				@Override
				public User execute(Connection conn) throws SQLException
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
						//	User user = new User(); //I'm not sure if this is correct
							loadUser(u, resultSet, 1);
							
							result.add(new User(u.getUsername() ,u.getPassword() , false));	
							
						}
						
						if (!found)
						{
							System.out.println("<" + u.getUsername() + "> was not found in the books table");
						}
						
						return u;
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
					
					stmt3 = conn.prepareStatement("create table chatroomUser (num int primary key generated always as identity (start with 1, increment by 1),room_id int, user_id int)  ");
					stmt3.executeUpdate();
					
					stmt4= conn.prepareStatement(" create table messagesList (messages_id int primary key generated always as identity (start with 1, increment by 1), textfile_Name varchar(70)" );
		
					
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
	//	db.loadInitialData();
		
		System.out.println("Success!");
	}
}
