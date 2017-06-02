package kr.or.connext.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.connect.todo.TodoApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
@SpringBootTest(classes = TodoApplication.class)
@Transactional
public class TodoControllerTest {
	@Autowired
	WebApplicationContext wac;
	MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = webAppContextSetup(this.wac)
			.alwaysDo(print(System.out))
			.build();	
	}
	
	@Test
	public void shouldReadList() throws Exception{
		mvc.perform(
				get("/api/todos/")
				)
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldRead() throws Exception{
		//db에 존재하는것로 테스트 
		mvc.perform(
				get("/api/todos/162")
				.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("162"));
	}

	@Test
	public void shouldCountNotCompleted() throws Exception{
		mvc.perform(
				get("/api/todos/count")
				)
				.andExpect(status().isOk());
	}

	@Test
	public void shouldCreate() throws Exception {
		String requestBody = "{\"todo\":\"test\"}";
		mvc.perform(
			post("/api/todos/")
				.contentType(MediaType.APPLICATION_JSON)
	 			.content(requestBody)
			)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.todo").value("test"))
			.andExpect(jsonPath("$.completed").value(0));
	}
	
	@Test
	public void shouldUpdateCompleted() throws Exception {
		String requestBody = "{\"completed\":\"1\"}";

		mvc.perform(
			put("/api/todos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
		)
		.andExpect(status().isNoContent());
	}

	@Test
	public void shouldDelete() throws Exception {
		mvc.perform(
			delete("/api/todos/1")
		)
		.andExpect(status().isNoContent());
	}
	
	@Test
	public void shouldDeleteCompleted() throws Exception {
		mvc.perform(
			delete("/api/todos/completed")
		)
		.andExpect(status().isNoContent());
	}

}
