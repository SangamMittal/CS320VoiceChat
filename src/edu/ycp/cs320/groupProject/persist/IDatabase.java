package edu.ycp.cs320.groupProject.persist;

import java.util.List;

import edu.ycp.cs320.groupProject.model.User;

public interface IDatabase {


	public List<User> signUp(User u);

	public boolean Login(User u);

	
	
}
