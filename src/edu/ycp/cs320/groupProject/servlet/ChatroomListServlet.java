package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
/*		Numbers model = new Numbers();
		NumbersController controller = 	new NumbersController();
		
		
		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		try {
			Double first = getDoubleFromParameter(req.getParameter("first"));
			Double second = getDoubleFromParameter(req.getParameter("second"));
			Double third = getDoubleFromParameter(req.getParameter("third"));
			

			if (first == null || second == null || third == null) {
				errorMessage = "Please specify three numbers";
			} else {
				model = new Numbers(first, second, third);
				controller.setModel(model);
				controller.add();
			}
		} catch (NumberFormatException e) 
		{
			errorMessage = "Invalid double";
		}
		
		// Add parameters as request attributes
		//req.setAttribute("first", model.getFirst());
		//req.setAttribute("second", model.getSecond());
		//req.setAttribute("third", model.getThird());
		req.setAttribute("model", model);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", model.getResult());
*/
		if(req.getParameter("logout") != null)
			resp.sendRedirect("login");
		else if(req.getParameter("createChatroom") != null)
			resp.sendRedirect("createChatroom");

		// Forward to view to render the result HTML document
		//req.getRequestDispatcher("/_view/chatroomList.jsp").forward(req, resp);
	}

	
}
