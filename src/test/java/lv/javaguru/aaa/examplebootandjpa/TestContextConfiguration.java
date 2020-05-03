package lv.javaguru.aaa.examplebootandjpa;

import lv.javaguru.aaa.examplebootandjpa.task.TaskRepository;
import lv.javaguru.aaa.examplebootandjpa.task.TaskService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestContextConfiguration {

    @Bean
    public TaskService taskService(final TaskRepository taskRepository) {
        return new TaskService(taskRepository);
    }

    @Bean
    public TaskRepository taskRepository() {
        return mock(TaskRepository.class);
    }
}
