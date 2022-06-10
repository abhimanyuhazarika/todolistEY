package com.ey.todolist.service;

import java.util.List;
import java.util.Optional;

import com.ey.todolist.config.auth.UserPrincipal;
import com.ey.todolist.model.Todo;

public interface TodoService {
	
	List<Todo> todoListByUserId(Long userId);

	Optional<Todo> findById(Long todoId);

	Todo save(UserPrincipal currentUser, Todo todo);

	void delete(Todo todo);

}
