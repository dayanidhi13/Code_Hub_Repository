package com.Task.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Task.model.Task;
import com.Task.model.TaskDao;
import com.Task.model.TaskDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TaskController")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskDao;

	public void init() {

		String jdbcUrl = "jdbc:mysql://localhost:3306/task";
		String jdbcUsername = "root";
		String jdbcPassword = "root";
		taskDao = new TaskDaoImpl(jdbcUrl, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "list"; // Default action
		}

		if ("view".equals(action) || "edit".equals(action)) {

		} else if ("delete".equals(action)) {
			deleteTask(request, response);
			return;
		}

		listTasks(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}

		switch (action) {
		case "add":
			addTask(request, response);
			break;
		case "update":
			updateTask(request, response);
			break;
		case "delete":
			deleteTask(request, response); // Handle delete action
			break;
		default:
			listTasks(request, response);
			break;
		}
	}

	private void listTasks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Task> taskList = taskDao.getAllTasks();
		request.setAttribute("taskList", taskList);
		request.getRequestDispatcher("/tasks.jsp").forward(request, response);
	}

	private void addTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDateStr = request.getParameter("dueDate");
		boolean isComplete = Boolean.parseBoolean(request.getParameter("isComplete"));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dueDate = null;
		try {
			dueDate = dateFormat.parse(dueDateStr);
		} catch (ParseException e) {

			request.setAttribute("error", "Invalid date format");
			request.getRequestDispatcher("/createTask.jsp").forward(request, response);
			return;
		}

		java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());

		Task newTask = new Task(0, title, description, sqlDueDate, isComplete);

		boolean success = taskDao.addTask(newTask);

		if (success) {

			response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
		} else {

			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	private void updateTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		if (idParam != null && !idParam.isEmpty()) {
			int taskId = Integer.parseInt(idParam);

			Task existingTask = taskDao.getTaskById(taskId);

			if (existingTask != null) {

				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String dueDateStr = request.getParameter("dueDate");
				boolean isComplete = Boolean.parseBoolean(request.getParameter("isComplete"));

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dueDate = null;
				try {
					dueDate = dateFormat.parse(dueDateStr);
				} catch (ParseException e) {

					response.sendRedirect(request.getContextPath() + "/error.jsp");
					return;
				}

				java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());

				existingTask.setTitle(title);
				existingTask.setDescription(description);
				existingTask.setDueDate(sqlDueDate);
				existingTask.setComplete(isComplete);

				boolean success = taskDao.updateTask(existingTask);

				if (success) {

					response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
				} else {

					response.sendRedirect(request.getContextPath() + "/error.jsp");
				}
			} else {

				response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
			}
		} else {

			response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
		}
	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		if (idParam != null && !idParam.isEmpty()) {
			try {
				int taskId = Integer.parseInt(idParam);

				boolean success = taskDao.deleteTask(taskId);

				if (success) {

					response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
				} else {

					response.sendRedirect(request.getContextPath() + "/error.jsp");
				}
			} catch (NumberFormatException e) {

				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			} catch (Exception e) {
				e.printStackTrace();

				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}
		} else {

			response.sendRedirect(request.getContextPath() + "/TaskController?action=list");
		}
	}

}
