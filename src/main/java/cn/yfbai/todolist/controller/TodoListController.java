package cn.yfbai.todolist.controller;

import cn.yfbai.todolist.model.TodoItem;
import cn.yfbai.todolist.repository.TodoListRepository;
import cn.yfbai.todolist.repository.TodoRepository;
import cn.yfbai.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoListController {

    @Autowired
    TodoService todoService;

    @GetMapping("")
    public List<TodoItem> getTodoList() {
        return todoService.findAll();
    }

    @PutMapping("/{id}")
    public TodoItem update(@PathVariable Integer id, @RequestBody TodoItem item) {
        return todoService.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        todoService.delete(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem add(@RequestBody TodoItem item) {
        return todoService.save(item);
    }
}
