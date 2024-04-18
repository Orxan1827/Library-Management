package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.user.UserCreateRequest;
import az.spring.bookstore.dto.request.user.UserDeleteRequest;
import az.spring.bookstore.dto.request.user.UserUpdateRequest;
import az.spring.bookstore.dto.response.user.UserCreateResponse;
import az.spring.bookstore.dto.response.user.UserReadResponse;
import az.spring.bookstore.dto.response.user.UserUpdateResponse;
import az.spring.bookstore.service.userService.UserCreateService;
import az.spring.bookstore.service.userService.UserDeleteService;
import az.spring.bookstore.service.userService.UserReadAllService;
import az.spring.bookstore.service.userService.UserUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreateService createService;

    private final UserUpdateService updateService;

    private final UserReadAllService readAllService;

    private final UserDeleteService deleteService;

    @PostMapping("/create")
    public ResponseEntity<UserCreateResponse> create2(@Valid @RequestBody UserCreateRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createService.createUser(createRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<UserUpdateResponse> update(@Valid @RequestBody UserUpdateRequest updateRequest) {
        return ResponseEntity.status(OK).body(updateService.update(updateRequest));
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<UserReadResponse>> getAllUsers() {
        return ResponseEntity.status(OK).body(readAllService.getAllUsers());
    }

    @PostMapping("/delete")
    public void deleteUser(@Valid @RequestBody UserDeleteRequest deleteRequest) {
        deleteService.deleteUser(deleteRequest);
    }

}
