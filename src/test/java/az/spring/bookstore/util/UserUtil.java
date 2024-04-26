package az.spring.bookstore.util;

import az.spring.bookstore.dto.request.user.UserCreateRequest;
import az.spring.bookstore.dto.request.user.UserUpdateRequest;
import az.spring.bookstore.dto.response.user.UserCreateResponse;
import az.spring.bookstore.dto.response.user.UserReadResponse;
import az.spring.bookstore.dto.response.user.UserUpdateResponse;
import az.spring.bookstore.entity.User;

public class UserUtil {

    private UserUtil() {

    }

    public static UserCreateRequest createRequest(){
       return UserCreateRequest.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static UserCreateResponse createResponse(){
        return UserCreateResponse.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static User user(){
        return User.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static User savedUser(){
        return User.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static User userWithId(){
        return User.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static User updatedUser(){
        return User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .build();
    }

    public static UserReadResponse userReadResponseId(){
        return UserReadResponse.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static UserReadResponse userReadResponse(){
        return UserReadResponse.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static UserUpdateRequest updateRequest(){
        return UserUpdateRequest.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static UserUpdateResponse updateResponse(){
        return  UserUpdateResponse.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .build();
    }

}
