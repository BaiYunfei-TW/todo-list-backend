package cn.yfbai.todolist.service;

import cn.yfbai.todolist.model.TodoItem;
import cn.yfbai.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoItem save(TodoItem item) {
        return todoRepository.saveAndFlush(item);
    }

    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }

    public List<TodoItem> findAll() {
        return todoRepository.findAll();
    }
}
