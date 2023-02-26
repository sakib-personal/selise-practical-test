package selise.exam.practicaltest.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selise.exam.practicaltest.entity.User;
import selise.exam.practicaltest.util.DateUtil;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private long id;
    private String username;
    private String email;
    private String dateOfBirth;
    private String firstName;
    private String lastName;

    public static UserResponseDto fromDomain(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .dateOfBirth(DateUtil.convertDateToString(user.getDateOfBirth()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
