package kr.or.connect.todo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService service;
	private final Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	
	
}
