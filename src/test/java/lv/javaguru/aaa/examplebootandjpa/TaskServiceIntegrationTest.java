package lv.javaguru.aaa.examplebootandjpa;

import lv.javaguru.aaa.examplebootandjpa.task.Task;
import lv.javaguru.aaa.examplebootandjpa.task.TaskRepository;
import lv.javaguru.aaa.examplebootandjpa.task.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = TestContextConfiguration.class)
@TestPropertySource("classpath:application-integration-test.properties")
public class TaskServiceIntegrationTest {

    @TestConfiguration
    public static class TaskServiceIntegrationTestContextConfiguration {
        @Bean
        public TaskService taskService(final TaskRepository taskRepository) {
            return new TaskService(taskRepository);
        }

        @Bean
        public TaskRepository taskRepository() {
            return mock(TaskRepository.class);
        }
    }

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Before
    public void setUp() {
        Task t = new Task(0L, "name", "desc");

        Mockito.when(taskRepository.findByName(t.getName())).thenReturn(Collections.singletonList(t));
    }

    @Test
    public void findByName() {
        String name = "name";
        List<Task> result = taskService.findTaskByName(name);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(name);
    }

}
