package cholog;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

public class TodoClientWithRestClient {

    private static final String URI = "/todos";

    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo 리스트로 변환하여 반환
        return restClient.get()
                .uri(URI)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public Todo getTodoById(Long id) {
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo로 변환하여 반환
        // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
        return restClient.get()
                .uri(URI + "/" + id)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                    throw new TodoException.NotFound(id);
                })
                .body(Todo.class);

    }
}
