package lv.javaguru.aaa.examplebootandjpa.task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(
        name = "Task.findAllByDescStartWithA",
        query = "select t from Task t where t.description like 'A%'"
)
public class Task {

    @Id
//    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    public Task() {}

    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
