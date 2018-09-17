package cn.yfbai.todolist.repository;

import cn.yfbai.todolist.model.TodoItem;
import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoListRepository{

    private static List<TodoItem> TODO_LIST;

    private static int nextId;

    public static List<TodoItem> getTodoList() {
        return TODO_LIST;
    }

    static {
        init();
    }

    public static void init() {
        nextId = 0;
        TODO_LIST = new ArrayList<>();

        _addItem(new TodoItem("jfieowj", false));
        _addItem(new TodoItem("fjiewj", false));
        _addItem(new TodoItem("fjowejfohuafaefw", false));
    }

    private static void _addItem(TodoItem item) {
        item.setId(nextId++);
        TODO_LIST.add(item);
    }

    public static void addItem(TodoItem item) {
        _addItem(item);
    }

    public static void remove(Integer id) {
        TODO_LIST = TODO_LIST.stream()
                .filter(item -> !item.getId().equals(id))
                .collect(Collectors.toList());
    }

    public static TodoItem update(TodoItem item) {
        return TODO_LIST.stream()
                .filter(_item -> _item.getId()
                        .equals(item.getId()))
                .findFirst()
                .get()
                .setValue(item.getValue())
                .setChecked(item.isChecked());
    }
}
