package cn.yfbai.todolist.repository;

import cn.yfbai.todolist.model.TodoItem;
import com.sun.tools.javac.comp.Todo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoListRepositoryTest {

    @Before
    public void setup() {
        TodoListRepository.init();
    }

    @Test
    public void shouldReturnTodoList() {
        List<TodoItem> todoItemList = TodoListRepository.getTodoList();

        int expectedLength = 3;
        assertEquals(todoItemList.size(), expectedLength);
    }

    @Test
    public void shouldAddTodoList() {
        TodoItem item = new TodoItem();
        TodoListRepository.addItem(item);
        int expectedLength = 4;
        int expectedId = 3;

        List<TodoItem> todoItemList = TodoListRepository.getTodoList();
        assertEquals(todoItemList.size(), expectedLength);
        assertTrue(todoItemList.contains(item));
        assertThat(item.getId(), is(expectedId));
    }

    @Test
    public void shouldRemoveTodoItem() {
        TodoItem item = TodoListRepository.getTodoList().get(0);
        TodoListRepository.remove(item.getId());

        assertFalse(TodoListRepository.getTodoList().contains(item));
    }

    @Test
    public void shouldUpdateTodoItem() {
        String expectedValue = "hasUpdated";
        TodoItem item = new TodoItem(0, expectedValue, false);
        item.setValue(expectedValue);
        TodoListRepository.update(item);

        assertEquals(TodoListRepository.getTodoList().get(0).getValue(), expectedValue);
    }
}
