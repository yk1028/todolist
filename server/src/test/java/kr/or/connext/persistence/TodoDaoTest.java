package kr.or.connext.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {
	@Autowired
	private TodoDao dao;

	@Autowired
	public TodoDaoTest(){
		
	}
	
	@Test
	public void shouldInsert() {
		// given
		Todo todo = new Todo("할 일이 많다", 0);

		// when
		Integer id = dao.insert(todo);

		// then
//		Book selected = dao.selectById(id);
//		System.out.println(selected);
//		assertThat(selected.getTitle(), is("Java 웹개발"));
	}

}
