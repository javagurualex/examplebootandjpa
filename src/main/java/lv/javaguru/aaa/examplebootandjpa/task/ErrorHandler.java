package lv.javaguru.aaa.examplebootandjpa.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(TaskException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handler(TaskException e){
        Map<String, String> result = new HashMap<>();

        result.put("error", "TaskException");
        result.put("message", e.getMessage());

        return result;
    }
}
