# Task Management System

## Overview

The Task Management System is a software application designed to address specific user and task management needs. It encompasses several key requirements:

1. **User Management:**
   - Users can register and log in to the system.
   - Each user has a unique username and password.

2. **Task Management:**
   - Users can create new tasks by providing a title, description, and due date.
   - Users can update existing tasks, including changing the title, description, due date, and assignment to another user.
   - Users can mark tasks as complete or incomplete.
   - Users can delete tasks.

## System Architecture

The Task Management System is designed using the Model-View-Controller (MVC) architecture in a Java Enterprise Edition (J2EE) application. Here's an overview of the system's architecture:

### Model (M)

The Model represents the business logic and data of the application. In your case, it includes the User and Task entities.

- **User Entity:** This entity stores user-specific data, such as username, password (hashed and securely stored), and any user-related information. You may also include roles or permissions to manage user access levels.

- **Task Entity:** This entity stores task-related data, including title, description, due date, assignment to a user, and completion status. You may also include additional fields like task creation date and task ID.

- **Data Access Object (DAO):** Implement DAO classes to interact with the database. These classes are responsible for database operations, such as creating, updating, retrieving, and deleting user and task records.

### View (V)

The View represents the user interface and presentation layer of the application.

- **GUI:** Implement a user-friendly graphical user interface using J2EE technologies like JavaServer Pages (JSP). The GUI should allow users to register, log in, create/update/delete tasks, search, filter, and perform other actions as per your requirements.

- **Input Validation and Error Handling:** Ensure that the GUI performs input validation on user inputs and provides meaningful error messages to guide users when they enter invalid data.

### Controller (C)

The Controller acts as an intermediary between the Model and the View. It handles user requests, processes them, and communicates with the Model and View components accordingly.

- **Servlets or Controllers:** In a J2EE application, you can use servlets or controller classes as MVC controllers to handle incoming HTTP requests. These controllers will validate user inputs, interact with the Model to perform CRUD operations on users and tasks, and then update the View to reflect the changes.

### Database

Use a relational database system like MySQL to store user and task data. Create the schema and use two tables: "users" and "tasks."

### Deployment

Deploy the J2EE application on an application server like Apache Tomcat (version 10.1.10) and configure it appropriately for production use.

## Code Structure

The code structure for the Task Management System designed using the MVC architecture includes the following components:

### Model Layer and MySQL Database

For a traditional J2EE application, use JDBC to connect to the database. Implement data access classes to perform CRUD operations on User and Task. These classes will use the database connection to execute SQL queries.

- Example classes:
  - `User.java`
  - `Task.java`
  - `UserDao.java`
  - `TaskDao.java`
  - `UserDaoImpl.java`
  - `TaskDaoImpl.java`

### View Layer and JSPs

JavaServer Pages (JSPs) are used to create the user interface for your Task Management System. JSPs allow you to embed Java code within HTML pages to generate dynamic web content.

- Example JSP files:
  - `login.jsp`
  - `register.jsp`
  - `dashboard.jsp`
  - `tasks.jsp`
  - `deleteTask.jsp`
  - `createTask.jsp`
  - `edit.jsp`
  - `Style.css` for styling.

### Controller and Servlets

Controllers and servlets play a crucial role in handling user requests and interactions. They act as intermediaries between the user interface (UI) and the backend logic, facilitating the flow of data and actions.

- Example controller classes:
  - `UserController.java`
  - `TaskController.java`
  - `DeleteTaskServlet.java`
  - `AddTaskServlet.java`

## Graphical User Interface (GUI)

Creating a user-friendly Graphical User Interface (GUI) is essential for providing a positive user experience. Below are screenshots of the designed GUI:

### Login Page

![Login Page](login_page_screenshot.png)

### Register Page

![Register Page](register_page_screenshot.png)
how to make this for github read md file


# Task Management System

## Introduction

### Overview of the Requirements
The Task Management System is a software application designed to address specific user and task management needs. It encompasses several key requirements:

1. **User Management:**
   - Users can register and log in to the system.
   - Each user has a unique username and password.

2. **Task Management:**
   - Users can create new tasks by providing a title, description, and due date.
   - Users can update existing tasks, including changing the title, description, due date, and assignment to another user.
   - Users can mark tasks as complete or incomplete.
   - Users can delete tasks.

## System Architecture

### Model Layer and MySQL Database
For a traditional J2EE application, you will use JDBC to connect to the database. Implement data access classes to perform CRUD operations on User and Task. These classes will use the database connection to execute SQL queries.

- `User.java`: User entity class.
- `Task.java`: Task entity class.
- `UserDao.java`: User data access interface.
- `TaskDao.java`: Task data access interface.
- `UserDaoImpl.java`: Implementation of UserDao.
- `TaskDaoImpl.java`: Implementation of TaskDao.

### View Layer and JSPs
JavaServer Pages (JSPs) are used to create the user interface for your Task Management System.

- `login.jsp`: Login page.
- `register.jsp`: Registration page.
- `dashboard.jsp`: User dashboard.
- `tasks.jsp`: View task list.
- `createTask.jsp`: Create task form.
- `edit.jsp`: Edit task form.
- `deleteTask.jsp`: Delete task confirmation page.
- `Style.css`: CSS stylesheet for styling.

### Controller and Servlets
Controllers and servlets play a crucial role in handling user requests and interactions. They act as intermediaries between the user interface (UI) and the backend logic.

- `UserController.java`: User-related controller.
- `TaskController.java`: Task-related controller.
- `DeleteTaskServlet.java`: Servlet for deleting tasks.
- `AddTaskServlet.java`: Servlet for adding tasks.

## Graphical User Interface (GUI)

### Screenshots
Here are some screenshots of the GUI:

1. Login page
   ![Screenshot (82)](https://github.com/dayanidhi13/Code_Hub_Repository/assets/145747015/86658016-d628-4db5-9270-270dbd5386b2)

2. Register page
   ![Registration](https://github.com/dayanidhi13/Code_Hub_Repository/assets/145747015/ee24e938-7e41-49b9-914c-9603ebca3db1)

3. Dashboard
  ![Screenshot (83)](https://github.com/dayanidhi13/Code_Hub_Repository/assets/145747015/83170894-4197-4a51-94d1-de786efaf9cb)

4. View Task list

![Screenshot (85)](https://github.com/dayanidhi13/Code_Hub_Repository/assets/145747015/f7b98134-c604-4df8-a328-9f40b8f9e243)

   

6. Create task form
   ![Screenshot (84)](https://github.com/dayanidhi13/Code_Hub_Repository/assets/145747015/e955cf1f-916d-404d-82c5-19a26860c939)




