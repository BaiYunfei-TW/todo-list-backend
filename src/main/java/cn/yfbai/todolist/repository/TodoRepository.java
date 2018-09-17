package cn.yfbai.todolist.repository;

import cn.yfbai.todolist.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

    boolean existsByValue(String value);
}
