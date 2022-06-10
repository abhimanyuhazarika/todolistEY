# TODO List Application
A simple online TODO list application to track daily tasks.

**Features:**

Register as a new user or Sign In.  
User Role Based JWT authentication.  
Registered users can add, view, edit and delete tasks.  
Changes are persistent and can be viewed after sign in again by the same user.  
Last update of each task is displayed.  
Todo list is sorted by target date in ascending order.  
Automatically sets When ToDo the task by reading the task description.  
Uses Natural Language Parsers (natty library) to identify "When to do" of the task. 
  
**Requirements:**

Java 8
TOMCAT 7

**Running the application:**

The war file (contains both frontend and backend code) can be deployed to a TOMCAT server and the application can be accessed through http://localhost:8080 if deployed in local TOMCAT server.
Test Login details: (username: test, password: pwd123)

To add tasks: click the "Add" button and add the task description in the "What and When TODO" textbox and click the green "Save Task" button.
To mark a task as done: click the "Done" checkbox.
To update a task: click the yellow "Update" button and edit the description in the textbox. Click "Save Task" button once changes are final. 
Use time reference in task text for "When To Do" field update.

																
