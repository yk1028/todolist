package kr.or.connect.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "kr.or.connect.presentation")
@ComponentScan(basePackages = "kr.or.connect.service")
public class TodoApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
