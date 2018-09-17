package cn.yfbai.todolist;

import cn.yfbai.todolist.model.TodoItem;
import cn.yfbai.todolist.repository.TodoRepository;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test_save_item() {
        ResponseEntity<TodoItem> resp = restTemplate.postForEntity("/api/todos", new TodoItem(1, "aaa", true), TodoItem.class);
        TodoItem item = resp.getBody();
        assertThat(resp.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(resp.getBody(), notNullValue());
        assertTrue(todoRepository.existsByValue("aaa"));

        todoRepository.deleteById(item.getId());
    }

    @Test
    public void test_get_all() {
        List<TodoItem> existList = Arrays.asList(
                new TodoItem(1, "item 1", false),
                new TodoItem(2, "item 2", true)
        );
        todoRepository.deleteAll();
        todoRepository.saveAll(existList);

        ResponseEntity<List> response = restTemplate.getForEntity("/api/todos", List.class);
        List<TodoItem> itemList = (List<TodoItem>) response.getBody();

        assertThat(itemList.size(), is(existList.size()));
    }

    @Test
    public void test_update_item() {
        todoRepository.deleteAll();
        TodoItem oldItem = todoRepository.save(new TodoItem(1, "item 1", true));

        oldItem.setChecked(false);
        oldItem.setValue("new value");

        restTemplate.put("/api/todos/{1}", new HttpEntity<>(oldItem), oldItem.getId());

        TodoItem newItem = todoRepository.findById(oldItem.getId()).orElse(null);

        assertThat(newItem, equalTo(oldItem));
    }

    @Test
    public void test_delete_item() {
        todoRepository.deleteAll();
        TodoItem item = todoRepository.save(new TodoItem(1, "item 1", true));

        restTemplate.delete("/api/todos/{1}", item.getId());

        assertFalse(todoRepository.existsById(item.getId()));
    }
}
