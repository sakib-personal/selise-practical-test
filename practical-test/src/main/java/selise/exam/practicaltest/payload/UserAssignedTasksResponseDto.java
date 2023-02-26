package selise.exam.practicaltest.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selise.exam.practicaltest.entity.Task;
import selise.exam.practicaltest.util.DateUtil;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAssignedTasksResponseDto {
    private long id;
    private String description;
    private String dueDate;
    private boolean completed;
    private long userId;

    public static UserAssignedTasksResponseDto fromDomain(Task task) {
        return UserAssignedTasksResponseDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dueDate(DateUtil.convertDateToString(task.getDueDate()))
                .completed(task.isCompleted())
                .userId(task.getUser().getId())
                .build();
    }
}
