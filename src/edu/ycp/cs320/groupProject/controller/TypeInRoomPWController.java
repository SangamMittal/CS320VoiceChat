package edu.ycp.cs320.groupProject.controller;

import edu.ycp.cs320.groupProject.model.Chatroom;
import edu.ycp.cs320.groupProject.persist.DatabaseProvider;
import edu.ycp.cs320.groupProject.persist.DerbyDatabase;
import edu.ycp.cs320.groupProject.persist.IDatabase;

public class TypeInRoomPWController {

	private IDatabase db = null;
	
	
	public TypeInRoomPWController()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();	
	}
	
	public boolean check(Chatroom c)
	{
		
		Chatroom room = db.selectChatroom(c);
		
		
			if (	room.getPassword().equals(c.getPassword()) )
			{
				return true;
			}
			
			return false;
	}
	
	
}
