package com.Task.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Task.model.TaskDao;
import com.Task.model.TaskDaoImpl;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteTaskServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		if (idParam != null && !idParam.isEmpty()) {
			try {
				int taskId = Integer.parseInt(idParam);

				TaskDao taskDao = new TaskDaoImpl("jdbc:mysql://localhost:3306/task", "root", "root");

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
