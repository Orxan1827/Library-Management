package az.spring.bookstore.mapper;

import az.spring.bookstore.dto.request.UserCreateRequest;
import az.spring.bookstore.dto.response.UserCreateResponse;
import az.spring.bookstore.dto.response.UserReadResponse;
import az.spring.bookstore.dto.response.UserUpdateResponse;
import az.spring.bookstore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User mapRequestToUser(UserCreateRequest createRequest);

    UserCreateResponse mapUserToResponse(User user);

    UserUpdateResponse mapUserToUpdatedResponse(User user);

    UserReadResponse mapUserReadResponse(User user);

}
