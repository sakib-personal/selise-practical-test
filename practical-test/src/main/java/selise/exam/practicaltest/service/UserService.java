package selise.exam.practicaltest.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import selise.exam.practicaltest.entity.User;
import selise.exam.practicaltest.payload.UserRequestDto;
import selise.exam.practicaltest.payload.UserResponseDto;
import selise.exam.practicaltest.repository.UserRepository;
import selise.exam.practicaltest.util.Constants;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws EntityExistsException {
        User user = UserRequestDto.toDomain(userRequestDto);
        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isEmpty()) {
            return UserResponseDto.fromDomain(userRepository.save(user));
        } else {
            throw new EntityExistsException(Constants.USER_EXISTS);
        }
    }

    @Transactional
    public UserResponseDto updateUser(long userId,
                                      UserRequestDto userRequestDto) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_EXISTS));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        return UserResponseDto.fromDomain(userRepository.save(user));
    }
}
