package selise.exam.practicaltest.exception;

import selise.exam.practicaltest.payload.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Object> uniqueConstraintException(UniqueConstraintException exception) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.CONFLICT, "Value constraint failed.", exception));
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<Object> unexpectedRollbackExceptionException(UnexpectedRollbackException unexpectedRollbackException) {
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected rollback exception occurred.",
                unexpectedRollbackException));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        return new ResponseEntity<Object>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
    }
}
