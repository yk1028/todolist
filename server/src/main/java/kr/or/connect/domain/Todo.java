package kr.or.connect.domain;

public class Todo {
	private Integer id;
	private String context;
	private Integer completed; //active:0 , completed:1
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", context=" + context + ", completed=" + completed + "]";
	}
}
