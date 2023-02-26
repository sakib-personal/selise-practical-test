package selise.exam.practicaltest.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import selise.exam.practicaltest.payload.ApiResponseEntityFactory;
import selise.exam.practicaltest.payload.UserNewTaskCreationRequestDto;
import selise.exam.practicaltest.service.UserTaskService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users/{userId}/tasks")
public class UserTaskController {

    private final ApiResponseEntityFactory responseFactory;
    private final UserTaskService userTaskService;

    @PostMapping
    public ResponseEntity<?> assignUserNewTask(@Valid @NotNull @Min(1) @PathVariable long userId,
                                               @Valid @RequestBody UserNewTaskCreationRequestDto userNewTaskCreationRequestDto) {
        try {
            return this.responseFactory.successResponse(
                    userTaskService.createNewUserTask(userId, userNewTaskCreationRequestDto), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserAssignedTasks(@Valid @NotNull @Min(1) @PathVariable long userId) {
        try {
            return this.responseFactory.successResponse(
                    userTaskService.getUserAssignedTasks(userId), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateUserAssignedTask(@Valid @NotNull @Min(1) @PathVariable long userId,
                                                    @Valid @NotNull @Min(1) @PathVariable long taskId,
                                                    @Valid @RequestBody UserNewTaskCreationRequestDto userNewTaskCreationRequestDto) {
        try {
            return this.responseFactory.successResponse(
                    userTaskService.updateUserAssignedTask(userId, taskId, userNewTaskCreationRequestDto), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<?> updateTaskAsComplete(@Valid @NotNull @Min(1) @PathVariable long userId,
                                                  @Valid @NotNull @Min(1) @PathVariable long taskId) {
        try {
            return this.responseFactory.successResponse(
                    userTaskService.updateTaskAsComplete(taskId), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", params="completed, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE")
    public ResponseEntity<?> getTasksByCompleteStatus(@Valid @NotNull @Min(1) @PathVariable long userId,
                                                      @Valid @NotNull @RequestParam boolean completed) {
        return this.responseFactory.successResponse(
                userTaskService.getTasksByCompleteStatus(completed), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserAssignedTask(@Valid @NotNull @Min(1) @PathVariable long userId,
                                                    @Valid @NotNull @Min(1) @PathVariable long taskId) {
        try {
            return this.responseFactory.successResponse(
                    userTaskService.deleteUserAssignedTask(userId, taskId), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
