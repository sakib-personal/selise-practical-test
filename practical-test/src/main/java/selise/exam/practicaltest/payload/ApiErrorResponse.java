package selise.exam.practicaltest.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import selise.exam.practicaltest.util.DateUtil;

import java.time.Instant;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timeStamp", "statusCode", "success", "message", "debugMessage", "errors"})
public class ApiErrorResponse {

    @JsonIgnore
    private HttpStatus status;
    @JsonInclude
    private Instant timeStamp;
    @JsonInclude
    private int statusCode;
    @JsonInclude
    private boolean success;
    @JsonInclude
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<? extends ApiSubError> errors;

    private ApiErrorResponse(HttpStatus status, String message) {
        timeStamp = DateUtil.getStandardCurrentTimeStamp();
        this.success = Boolean.FALSE;
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable exception) {
        this(status, message);
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message, List<? extends ApiSubError> errors) {
        this(status, message);
        this.errors = errors;
    }

    public ApiErrorResponse(HttpStatus status, String message, List<? extends ApiSubError> errors, Throwable exception) {
        this(status, message);
        this.errors = errors;
        this.debugMessage = exception.getLocalizedMessage();
    }
}
