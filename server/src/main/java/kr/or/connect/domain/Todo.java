package kr.or.connect.domain;

public class Todo {
	private Integer id;
	private String todo;
	private Integer completed; //active:0 , completed:1
	
	public Todo(){}
	
	public Todo(String todo) {
		this.todo = todo;
	}
	
	public Todo(Integer id,String todo, Integer completed) {
		this.id = id;
		this.todo = todo;
		this.completed = completed;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + "]";
	}
}
