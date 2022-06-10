package com.ey.todolist.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ey.todolist.config.auth.CurrentUser;
import com.ey.todolist.config.auth.UserPrincipal;
import com.ey.todolist.exception.ResourceNotFoundException;
import com.ey.todolist.model.Todo;
import com.ey.todolist.service.TodoService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoService todoService;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/users/{userId}/todos")
	public List<Todo> getTodoListByUserId(@CurrentUser UserPrincipal currentUser, @PathVariable String userId) {
		LOGGER.info("Getting todos for userId={}", currentUser.getId());
		return todoService.todoListByUserId(currentUser.getId());
	}

	@GetMapping("users/{userId}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@CurrentUser UserPrincipal currentUser, @PathVariable String userId, @PathVariable(value = "id") Long todoId)
			throws ResourceNotFoundException {
		LOGGER.info("Retrieving todo by todoId={}", todoId);
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		return ResponseEntity.ok().body(todo);
	}

	@PostMapping("/users/{userId}/todos")
	public Todo createTodo(@CurrentUser UserPrincipal currentUser, @PathVariable String userId, @Valid @RequestBody Todo todo) {
		LOGGER.info("Creating task for user id={}", currentUser.getId());
		todo.setModifiedDate(new Date());
		return todoService.save(currentUser, todo);
	}

	@PutMapping("/users/{userId}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "id") Long todoId,
			@Valid @RequestBody Todo todoDetails) throws ResourceNotFoundException {
		LOGGER.info("Updating task with taskId={}", todoId);
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));

		todo.setDescription(todoDetails.getDescription());
		todo.setDone(todoDetails.isDone());
		todo.setTargetDate(todoDetails.getTargetDate());
		todo.setModifiedDate(new Date());

		final Todo updatedTodo = todoService.save(currentUser, todo);
		return ResponseEntity.ok(updatedTodo);
	}

	@DeleteMapping("/users/{userId}/todos/{id}")
	public Map<String, Boolean> deleteTodo(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "id") Long todoId)
			throws ResourceNotFoundException {
		LOGGER.info("Deleting task with taskId={}", todoId);
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));

		todoService.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
