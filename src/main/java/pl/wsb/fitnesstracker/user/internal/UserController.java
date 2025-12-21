package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUsersByMail(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThanSomething(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userService.findUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(userId, user);
        return userMapper.toDto(updatedUser);
    }
}

