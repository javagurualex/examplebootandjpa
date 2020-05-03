package lv.javaguru.aaa.examplebootandjpa;

import lv.javaguru.aaa.examplebootandjpa.task.Task;
import lv.javaguru.aaa.examplebootandjpa.task.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //Junit 4
//@ExtendWith() //Junit 5
//RunWith -> connect Spring boot + Junit
@DataJpaTest
public class TaskRepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByNameTest() {
        Task t = new Task(1L, "Name", "Desc");

        testEntityManager.persist(t);
        testEntityManager.flush();

        List<Task> result = taskRepository.findByName(t.getName());

        assertThat(result.size()).isEqualTo(1);

        assertThat(result.get(0).getName()).isEqualTo(t.getName());
    }
}
