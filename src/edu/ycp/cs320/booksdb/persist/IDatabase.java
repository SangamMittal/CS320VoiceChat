package edu.ycp.cs320.booksdb.persist;

import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.groupProject.model.User;

public interface IDatabase {


	public List<User> signUp(User u);

	public User Login(User u);

	
	
}
