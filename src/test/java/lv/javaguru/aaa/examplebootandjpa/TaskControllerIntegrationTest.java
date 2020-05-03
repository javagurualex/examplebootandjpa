package lv.javaguru.aaa.examplebootandjpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.aaa.examplebootandjpa.task.Task;
import lv.javaguru.aaa.examplebootandjpa.task.TaskController;
import lv.javaguru.aaa.examplebootandjpa.task.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TaskController.class)
public class TaskControllerIntegrationTest {


    /*
    1. Http Request -> @GetMapping
    2. Deserialization -> @RequestBody
    3. Validation -> @Validated

    4. Business logic -> !!!Unit test!!!

    5. Serialization -> OK JSON output
    6. Exception -> @RestControllerAdvice
*/

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenTask_whenGetTask_thenReturnJSONArray() throws Exception {
        Task t = new Task(1L, "name", "desc");

        List<Task> result = Collections.singletonList(t);

        //Given -> some initial context
        //        and more context
        //When -> Something happens
        //        and more happens
        //Then -> some expectation
        //        more expectation

        //Given -> I have 100 apple
        //         and 2 car
        //When -> I sell 20 apple
        //        and 2 car
        //Then -> I should have 80 apple
        //        and 0 car

        given(taskService.getAllTasks()).willReturn(result);
        //vs
        //when(taskService.getAllTasks()).thenReturn(result);
        mvc.perform(
                get("/")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

//     @GetMapping(value = "/", params = {"page", "size"})
//    public List<Task> getByPage(@RequestParam("page") int page,
//                                @RequestParam("size") int size) {
//        return taskService.getByPage(page, size).getContent();
//    }

    @Test
    public void givenTaskURLWithParams_whenGetFirstPage_ResponseOK() throws Exception {
        int page = 1;
        int size = 1;

        Task t = new Task(1L, "name", "desc");

        List<Task> allTasks = Collections.singletonList(t);
        PageRequest of = PageRequest.of(page, size);
        Page<Task> result = new PageImpl<>(allTasks, of, allTasks.size());

        given(taskService.getByPage(page, size)).willReturn(result);

        mvc.perform(
                get("/")
                        .param("page", Integer.toString(page))
                        .param("size", Integer.toString(size))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void givenTaskPOSTUriWithData_whenCreateTask_thenVerifyResponse() throws Exception {
        Task t = new Task(0L, "name", "desc");
        given(taskService.save(any())).willReturn(t);

        MvcResult mvcResult = mvc.perform(
                post("/")
                        .content(objectMapper.writeValueAsString(t))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(t))
                .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    public void givenURL_whenGetError_thenReturnError() throws Exception {
        mvc.perform(get("/mySuperCustomError"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("GET: mySuperCustomError"));
    }
}
