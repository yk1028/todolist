package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String SELECT_ALL =
			"SELECT id, todo, completed FROM todo";
	static final String SELECT_BY_ID =
			"SELECT id, todo, completed FROM todo where id = :id";
	static final String INSERT = 
			"INSERT INTO todo(TODO, COMPLETED) VALUE(:todo,:completed)";
	static final String UPDATE =
			"UPDATE todo SET\n"
			+ "completed = :completed\n"
			+ "WHERE id = :id";
	static final String COUNT_NOT_COMPLETED=
			"SELECT COUNT(*) FROM todo WHERE completed = 0";
}
