package selise.exam.practicaltest.exception;

public class UniqueConstraintException extends BaseException {
    public UniqueConstraintException(String message, Throwable error) {
        super(message, error);
    }

    public UniqueConstraintException(String message) {
        super(message);
    }
}
