package cn.yfbai.todolist.controller;

import cn.yfbai.todolist.TodoListApplication;
import cn.yfbai.todolist.model.TodoItem;
import cn.yfbai.todolist.repository.TodoListRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.comp.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class TodoListControllerTest {

//    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    private TodoListController todoListController;

    private Gson gson;

//    @Before
    public void setup() {
        TodoListRepository.init();
        this.gson = new GsonBuilder().create();
    }

//    @Test
    public void shouldGetTodoItemList() throws Exception {
        this.mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        gson.toJson(TodoListRepository.getTodoList())));
    }

//    @Test
    public void shouldUpdateTodoItem() throws Exception {
        String expectedValue = "hasUpdated";
        TodoItem item = new TodoItem(0, expectedValue, false);

        this.mockMvc
                .perform(
                        put("/api/todos/{id}",item.getId())
                        .content(gson.toJson(item))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        assertEquals(expectedValue, TodoListRepository.getTodoList().get(0).getValue());
    }

//    @Test
    public void shouldDeclineSizeWhenDeleteTodoItem() throws Exception {
        Integer expectedSize = 2;
        TodoItem item = TodoListRepository.getTodoList().get(0);

        this.mockMvc
                .perform(delete("/api/todos/{id}", 0))
                .andExpect(status().isOk());

        assertThat(TodoListRepository.getTodoList().size(), is(expectedSize));
        assertFalse(TodoListRepository.getTodoList().contains(item));
    }

//    @Test
    public void shouldAddTodoItem() throws Exception {
        int expectedSize = 4;
        TodoItem item = new TodoItem("todo 1", false);

        this.mockMvc
                .perform(post("/api/todos").content(gson.toJson(item)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        assertEquals(expectedSize, TodoListRepository.getTodoList().size());
    }

}
