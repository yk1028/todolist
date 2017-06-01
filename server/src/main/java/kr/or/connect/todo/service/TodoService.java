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
	
	public boolean update(Todo todo){
		int affected = dao.update(todo);
		return affected == 1;
	}
	
	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	
	public int countNotComplted(){
		return dao.countTodos();
	}
	
	public boolean deleteCompleted(){
		int affected = dao.deleteCompleted();
		return affected == 1;
	}
}
