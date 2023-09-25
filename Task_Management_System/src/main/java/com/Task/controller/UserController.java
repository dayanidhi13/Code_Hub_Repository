package com.Task.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.Task.model.User;
import com.Task.model.UserDao;
import com.Task.model.UserDaoImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {

		userDao = new UserDaoImpl("jdbc:mysql://localhost:3306/task", "root", "root");
	}

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("login")) {
			loginUser(request, response);
		} else if (action.equals("register")) {
			registerUser(request, response);
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDao.getUserByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("dashboard.jsp");
		} else {
			// Handle incorrect login credentials
			response.sendRedirect("login.jsp?error=1");
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User existingUser = userDao.getUserByUsername(username);

		if (existingUser == null) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);

			boolean success = userDao.addUser(newUser);

			if (success) {

				response.sendRedirect("login.jsp");
			} else {

				response.sendRedirect("register.jsp?error=2");
			}
		} else {

			response.sendRedirect("register.jsp?error=1");
		}

	}

}
