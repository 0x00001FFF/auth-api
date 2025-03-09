package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.UserRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveUserResponseDto;
import dev.sunless.auth_api.dtos.response.UserResponseDto;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userName", source = "requestDto.userName")
    @Mapping(target = "email", source = "requestDto.email")
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "passwordHash", source = "requestDto.password")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User toModel(UserRequestDto requestDto, Set<Role> roles);


    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "roles", source = "user.roles")
    @Mapping(target = "isActive", source = "user.isActive")
    @Mapping(target = "createdDate", source = "user.createdDate")
    @Mapping(target = "lastModified", source = "user.lastModified")
    public abstract UserResponseDto toResponse(User user);


    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "roles", source = "user.roles")
    @Mapping(target = "isActive", source = "user.isActive")
    @Mapping(target = "createdDate", source = "user.createdDate")
    @Mapping(target = "lastModified", source = "user.lastModified")
    @Mapping(target = "deletedAt", source = "user.deletedAt")
    public abstract InactiveUserResponseDto toInactiveResponse(User user);
}
