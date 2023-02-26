package selise.exam.practicaltest.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selise.exam.practicaltest.entity.Task;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNewTaskCreationResponseDto {
    private long id;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;
    private long userId;

    public static UserNewTaskCreationResponseDto fromDomain(Task task) {
        return UserNewTaskCreationResponseDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .completed(task.isCompleted())
                .userId(task.getUser().getId())
                .build();
    }
}