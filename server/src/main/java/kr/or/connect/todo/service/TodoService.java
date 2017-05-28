package kr.or.connect.todo.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
@ComponentScan(basePackages = "kr.or.connect.persistence")
public class TodoService {
	private TodoDao dao;
	
	public Todo create(Todo todo) {
		Integer id = dao.insert(todo);
		todo.setId(id);
		return todo;
	}
}
