package selise.exam.practicaltest.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import selise.exam.practicaltest.entity.Task;
import selise.exam.practicaltest.entity.User;
import selise.exam.practicaltest.payload.UserAssignedTasksResponseDto;
import selise.exam.practicaltest.payload.UserNewTaskCreationRequestDto;
import selise.exam.practicaltest.payload.UserNewTaskCreationResponseDto;
import selise.exam.practicaltest.repository.TaskRepository;
import selise.exam.practicaltest.repository.UserRepository;
import selise.exam.practicaltest.util.Constants;
import selise.exam.practicaltest.util.DateUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public UserNewTaskCreationResponseDto createNewUserTask(long userId,
                                                            UserNewTaskCreationRequestDto userNewTaskCreationRequestDto)
            throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_NOT_EXISTS));
        return UserNewTaskCreationResponseDto.fromDomain(
                taskRepository.save(UserNewTaskCreationRequestDto.toDomain(user, userNewTaskCreationRequestDto)));
    }

    public List<UserAssignedTasksResponseDto> getUserAssignedTasks(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_NOT_EXISTS));
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(task -> UserAssignedTasksResponseDto.fromDomain(task)).toList();
    }

    @Transactional
    public UserAssignedTasksResponseDto updateUserAssignedTask(long userId,
                                                               long taskId,
                                                               UserNewTaskCreationRequestDto userNewTaskCreationRequestDto)
            throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_NOT_EXISTS));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.TASK_NOT_EXISTS));
        task.setDescription(userNewTaskCreationRequestDto.getDescription());
        task.setDueDate(DateUtil.convertStringToDateTime(userNewTaskCreationRequestDto.getDueDate()));
        task.setCompleted(userNewTaskCreationRequestDto.isCompleted());
        return UserAssignedTasksResponseDto.fromDomain(taskRepository.save(task));
    }

    @Transactional
    public UserAssignedTasksResponseDto updateTaskAsComplete(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.TASK_NOT_EXISTS));
        User user = userRepository.findById(task.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_NOT_EXISTS));
        task.setCompleted(Boolean.TRUE);
        return UserAssignedTasksResponseDto.fromDomain(taskRepository.save(task));
    }

    public List<UserAssignedTasksResponseDto> getTasksByCompleteStatus(boolean isCompleted) {
        return taskRepository.findAllByCompleted(isCompleted).stream()
                .map(task -> UserAssignedTasksResponseDto.fromDomain(task)).toList();
    }

    @Transactional
    public boolean deleteUserAssignedTask(long userId, long taskId) throws EntityNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.TASK_NOT_EXISTS));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(Constants.USER_NOT_EXISTS));
        taskRepository.delete(task);
        return Boolean.TRUE;
    }
}
