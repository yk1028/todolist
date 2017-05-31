package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
@ComponentScan(basePackages = "kr.or.connect.persistence")
public class TodoService {
	@Autowired
	private TodoDao dao;
	
	public Todo findById(Integer id) {
		return dao.selectById(id);
	}

	public Collection<Todo> findAll() {
		return dao.selectAll();
	}
	
	public Todo create(Todo todo) {
		Integer id = dao.insert(todo);
		todo.setId(id);
		todo.setCompleted(0);
		return todo;
	}
	
	public boolean update(Integer id, Integer completed){
		int affected = dao.update(id,completed);
		return affected == 1;
	}
}
