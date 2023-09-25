package com.Task.model;

import java.util.List;

public interface TaskDao {
	List<Task> getAllTasks();

	Task getTaskById(int taskId);

	boolean addTask(Task task);

	boolean updateTask(Task task);

	boolean deleteTask(int taskId);
}
