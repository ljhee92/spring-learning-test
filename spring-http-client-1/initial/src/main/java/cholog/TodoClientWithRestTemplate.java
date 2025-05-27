package cholog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/todos/";

    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(Long id) {
        // TODO: restTemplate을 사용하여 요청을 보내고 결과를 Todo로 변환하여 반환
        try {
            ResponseEntity<Todo> response = restTemplate.getForEntity(BASE_URL + id, Todo.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
            throw new TodoException.NotFound(id);
        }
    }
}
