package selise.exam.practicaltest.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String message;

    public BaseException(String message, Throwable error) {
        super(message, error);
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
}
