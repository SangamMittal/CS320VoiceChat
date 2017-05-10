package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.controller.TypeInRoomPWController;
import edu.ycp.cs320.groupProject.model.Chatroom;

public class TypeInRoomPWServlet extends HttpServlet {
	
	private TypeInRoomPWController tirc = new TypeInRoomPWController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String submit= req.getParameter("submit");
		String typed = req.getParameter("box");
	
		
		
		
	
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String name= (String) req.getSession().getAttribute("sharedChatroomName");
		String password = req.getParameter("box");
		boolean checked= false;
		
		Chatroom room = new Chatroom();
		room.setPassword(password);
		room.setChatroomName(name);
		checked = tirc.check(room);
		
		if (checked==true)
		{
			resp.sendRedirect("chatroom");
			
		}
		
		else if (checked == false)
		{
			resp.sendRedirect("typeInRoomPW");
		}
		
		
		
	
	
			}
	
}
