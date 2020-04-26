package lv.javaguru.aaa.examplebootandjpa.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/tasks")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        log.info("Request getAllTasks");
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/", params = {"description"})
    public List<Task> getByDesc(@RequestParam("description") String desc) {
        log.info("Request getByDesc");
        return taskService.getAllByDescription(desc);
    }

    @GetMapping(value = "/getAllStartsWithB")
    public List<Task> getDescStartWithB() {
        log.info("Request getDescStartWithB");
        return taskService.getDescStartWithB();
    }

    @GetMapping(value = "/getAllStartsWithA")
    public List<Task> getDescStartWithA() {
        log.info("Request getDescStartWithA");
        return taskService.getDescStartWithA();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        log.info("Request getTask with id: " + id);
        return taskService.getTask(id);
    }

    @GetMapping(value = "/", params = {"page", "size"})
    public List<Task> getByPage(@RequestParam("page") int page,
                                @RequestParam("size") int size) {
        return taskService.getByPage(page, size).getContent();
    }

    @PostMapping("/")
    public Task saveTask(@RequestBody Task task) {
        log.info("Request saveTask with " + task);
        return taskService.save(task);
    }

}
