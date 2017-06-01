package kr.or.connext.persistence;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.TodoApplication;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@Transactional
public class TodoDaoTest {
	@Autowired
	private TodoDao dao;

	@Autowired
	public TodoDaoTest() {
	}

	@Test
	public void shouldSelectAll() {
		List<Todo> allTodos = dao.selectAll();
		assertThat(allTodos, is(notNullValue()));
	}

	@Test
	public void shouldInsertAndSelect() {
		// given
		Todo todo = new Todo("test~");

		// when
		Integer id = dao.insert(todo);

		// then
		Todo selected = dao.selectById(id);
		assertThat(selected.getTodo(), is("test~"));
	}

	@Test
	public void shouldUpdateCompleted() {
		// Given
		Todo todo = new Todo("test");
		Integer id = dao.insert(todo);

		// When
		todo.setId(id);
		todo.setCompleted(1);
		int affected = dao.updateCompleted(todo);

		// Then
		assertThat(affected, is(1));
		Todo updated = dao.selectById(id);
		assertThat(updated.getCompleted(), is(1));
	}

	@Test
	public void shouldDeleteById() {
		// given
		Todo todo = new Todo("test");
		Integer id = dao.insert(todo);

		// when
		int affected = dao.deleteById(id);

		// Then
		assertThat(affected, is(1));
	}

	@Test
	public void shouldCountNotCompletedTodos() {
		// given
		int count = dao.countNotCompletedTodos();
		Todo todo = new Todo("test");

		// when
		dao.insert(todo);

		// Then
		assertThat(dao.countNotCompletedTodos(), is(count + 1));
	}

	@Test
	public void shouldDeleteCompleted() {
		// given
		Todo todo = new Todo("test");
		

		// when
		Integer id = dao.insert(todo);
		todo.setId(id);
		todo.setCompleted(1); //completed 상태로 변경 
		dao.updateCompleted(todo);
		dao.deleteCompleted();

		// Then
		assertThat(dao.countCompletedTodos(), is(0));
		
	}
}
