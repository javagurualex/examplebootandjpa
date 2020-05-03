package lv.javaguru.aaa.examplebootandjpa.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Page<Task> getByPage(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size));
    }

    public List<Task> getAllByDescription(String desc) {
        return taskRepository.findAllByDescription(desc);
    }

    public List<Task> getDescStartWithB() {
        return taskRepository.findAllByDescStartWithB();
    }

    public List<Task> getDescStartWithA() {
        return taskRepository.findAllByDescStartWithA();
    }

    public List<Task> findTaskByName(String name) {
        return taskRepository.findByName(name);
    }
}
