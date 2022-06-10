package com.ey.todolist.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.todolist.config.auth.UserPrincipal;
import com.ey.todolist.controller.util.NaturalLanguageParser;
import com.ey.todolist.model.Todo;
import com.ey.todolist.model.User;
import com.ey.todolist.repository.TodoRepository;
import com.ey.todolist.repository.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Todo> todoListByUserId(Long userId) {
		return todoRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Todo> findById(Long todoId) {
		return todoRepository.findById(todoId);
	}

	@Override
	public Todo save(UserPrincipal currentUser, Todo todo) {
		Optional<User> user = userRepository.findById(currentUser.getId());
		todo.setUser(user.get());
		Date today= new Date();
		Date whenToDo = NaturalLanguageParser.parseStringAsDate(todo.getDescription())==null?today:NaturalLanguageParser.parseStringAsDate(todo.getDescription());
		todo.setTargetDate(whenToDo);
		return todoRepository.save(todo);
	}

	@Override
	public void delete(Todo todo) {
		todoRepository.delete(todo);
	}

}
