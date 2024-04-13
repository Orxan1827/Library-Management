package az.spring.bookstore.controller;

import az.spring.bookstore.dto.request.UserCreateRequest;
import az.spring.bookstore.dto.response.UserCreateResponse;
import az.spring.bookstore.service.userService.UserCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreateService createService;

    @PostMapping("/create")
    public ResponseEntity<UserCreateResponse> create2(@Valid @RequestBody UserCreateRequest createRequest){
       return ResponseEntity.status(HttpStatus.CREATED).body(createService.createUser(createRequest));
    }

}
