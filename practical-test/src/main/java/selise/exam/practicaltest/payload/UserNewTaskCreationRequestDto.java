package selise.exam.practicaltest.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selise.exam.practicaltest.entity.Task;
import selise.exam.practicaltest.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNewTaskCreationRequestDto {
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;

    public static Task toDomain(User user, UserNewTaskCreationRequestDto userNewTaskCreationRequestDto) {
        return Task.builder()
                .id(0L)
                .description(userNewTaskCreationRequestDto.getDescription())
                .dueDate(userNewTaskCreationRequestDto.getDueDate())
                .completed(userNewTaskCreationRequestDto.isCompleted())
                .user(user)
                .build();
    }
}
