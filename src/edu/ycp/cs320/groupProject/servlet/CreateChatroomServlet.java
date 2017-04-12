package edu.ycp.cs320.groupProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import edu.ycp.cs320.groupProject.controller.GuessingGameController;
//import edu.ycp.cs320.groupProject.model.GuessingGame;

public class CreateChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("sharedUser");
		
		if(user == null){
			System.out.println("    User: <" + user + "> not logged in or session timed out");
		
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("     User: <" + user + "> logged in");
		
		req.getRequestDispatcher("/_view/createChatroom.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
/*		GuessingGame model = new GuessingGame();

		GuessingGameController controller = new GuessingGameController();
		controller.setModel(model);
		
		if (req.getParameter("startGame") != null) {
			controller.startGame();
		} else {
			// Reconstruct current GuessingGame model object
			Integer curMin = getInteger(req, "min");
			Integer curMax = getInteger(req, "max");
			
			model.setMin(curMin);
			model.setMax(curMax);

			if (req.getParameter("gotIt") != null) {
				controller.setNumberFound();
			} else if (req.getParameter("less") != null) {
				controller.setNumberIsLessThanGuess();
			} else if (req.getParameter("more") != null) {
				controller.setNumberIsGreaterThanGuess();
			} else {
				throw new ServletException("Unknown command");
			}
		}
		
		req.setAttribute("game", model);
		
		req.getRequestDispatcher("/_view/guessingGame.jsp").forward(req, resp);
*/
		if(req.getParameter("create") != null)
			resp.sendRedirect("chatroom");

		
	}
/*
	private int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}
*/	
	
}
