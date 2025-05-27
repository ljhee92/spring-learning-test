package cholog;

public class Todo {

    // TODO: Todo 객체가 가지는 필드들을 정의
    private Long id;

    private Long userId;

    private String title;

    private boolean completed;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
