package kr.or.connext.todoserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.TodoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@Transactional
public class TodoApplicationTests {
	@Test
	public void contextLoads() {
	}
}
