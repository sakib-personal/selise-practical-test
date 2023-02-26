package selise.exam.practicaltest.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import selise.exam.practicaltest.payload.ApiErrorResponse;
import selise.exam.practicaltest.payload.ApiValidationError;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice()
public class ValidationHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {

        List<ApiValidationError> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiValidationError(error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST,
                "error.invalidMethod", errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException constraintViolationException) {

        List<ApiValidationError> errors = constraintViolationException.getConstraintViolations().stream()
                .map(error -> new ApiValidationError(error.getPropertyPath().toString(), error.getMessage()))
                .collect(Collectors.toList());
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST,
                "error.validation", errors, constraintViolationException));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError) {
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
