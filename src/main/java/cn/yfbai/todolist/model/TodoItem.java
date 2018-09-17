package cn.yfbai.todolist.model;

import javax.persistence.*;

@Entity
@Table
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String value;

    @Column
    private Boolean checked;

    public TodoItem(Integer id, String value, Boolean checked) {
        this.id = id;
        this.value = value;
        this.checked = checked;
    }

    public TodoItem() {
    }

    public TodoItem(String value, Boolean checked) {
        this.value = value;
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public TodoItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public TodoItem setValue(String value) {
        this.value = value;
        return this;
    }

    public Boolean isChecked() {
        return checked;
    }

    public TodoItem setChecked(boolean checked) {
        this.checked = checked;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TodoItem)) {
            return false;
        }
        TodoItem item = (TodoItem) obj;
        return item.getValue().equals(this.value) && item.isChecked().equals(this.checked);
    }

}