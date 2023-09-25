package com.Task.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Task.model.Task;
import com.Task.model.TaskDao;
import com.Task.model.TaskDaoImpl;

@WebServlet("/addTask")
public class addTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addTask() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDateStr = request.getParameter("dueDate");
		boolean isComplete = Boolean.parseBoolean(request.getParameter("isComplete"));

		Date dueDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dueDate = dateFormat.parse(dueDateStr);
		} catch (ParseException e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Invalid due date format. Please use yyyy-MM-dd.");
			request.getRequestDispatcher("/createTask.jsp").forward(request, response);
			return;
		}

		Task newTask = new Task(0, title, description, new java.sql.Date(dueDate.getTime()), isComplete);

		TaskDao taskDao = new TaskDaoImpl("jdbc:mysql://localhost:3306/task", "root", "root");

		boolean success = taskDao.addTask(newTask);

		if (success) {

			response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
		} else {

			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

}
