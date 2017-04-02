package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.groupProject.model.User;

//import edu.ycp.cs320.groupProject.controller.NumbersController;
//import edu.ycp.cs320.groupProject.model.Numbers;

public class ChatroomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/chatroomList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		
		if(req.getParameter("logout") != null)
			resp.sendRedirect("login");
		else if(req.getParameter("createChatroom") != null)
			resp.sendRedirect("createChatroom");

	}

	
}
