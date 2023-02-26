package selise.exam.practicaltest.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selise.exam.practicaltest.payload.ApiResponseEntityFactory;
import selise.exam.practicaltest.payload.UserRequestDto;
import selise.exam.practicaltest.service.UserService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final ApiResponseEntityFactory responseFactory;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            return this.responseFactory.successResponse(userService.createUser(userRequestDto), HttpStatus.CREATED);
        } catch (EntityExistsException entityExistsException) {
            return this.responseFactory.errorResponse(entityExistsException.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Valid @NotNull @Min(1) @PathVariable long userId,
                                        @Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            return this.responseFactory.successResponse(userService.updateUser(userId, userRequestDto), HttpStatus.CREATED);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.responseFactory.errorResponse(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
