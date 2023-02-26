package selise.exam.practicaltest.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@JsonPropertyOrder({"timeStamp", "statusCode", "success", "data", "message"})
public class ApiResponse {
    @JsonInclude
    private Instant timeStamp;
    @JsonInclude
    private int statusCode;
    @JsonInclude
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
