package com.ey.todolist.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ey.todolist.config.auth.UserPrincipal;
import com.ey.todolist.model.Todo;
import com.ey.todolist.repository.TodoRepository;
import com.ey.todolist.service.TodoService;
import com.ey.todolist.service.TodoServiceImpl;

public class TodoControllerTest {

	@Rule  
	public MockitoRule mockitorule = MockitoJUnit.rule();
	
	@InjectMocks
	private TodoController todoController;
	
	@Mock
	private TodoService todoService;
	
	private UserPrincipal currentUser;
	
	@Before
	public void setUp() throws Exception {
		currentUser = new UserPrincipal(10L, "test", "test", "Test");
	}

	@Test
	public void getTodoListByUserId() {
		Todo todo = new Todo();
		List<Todo> todoData = new ArrayList<>();
				todoData.add(todo);
		String userId = "10";
		when(todoService.todoListByUserId(currentUser.getId())).thenReturn(todoData);
		
		List<Todo> todos = todoController.getTodoListByUserId(currentUser, userId);
		assertEquals(1, todos.size());
		
	}

}
