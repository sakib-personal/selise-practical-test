package selise.exam.practicaltest.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selise.exam.practicaltest.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    @Min(5)
    @NotBlank
    private String username;
    private String email;
    private LocalDateTime dateOfBirth;
    private String firstName;
    private String lastName;

    public static User toDomain(UserRequestDto userRequestDto) {
        return User.builder()
                .id(0L)
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .dateOfBirth(userRequestDto.getDateOfBirth())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .build();
    }
}
