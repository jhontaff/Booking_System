package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.user.UserDto;
import backend.ecommerce.ecommerceapi.dto.user.UserIdDto;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.service.user.UserService;

import java.util.stream.Collectors;

public class UserMapper {

    private final UserService userService;
    private final RoleMapper roleMapper;

    public UserMapper(UserService userService,
                      RoleMapper roleMapper) {
        this.userService = userService;
        this.roleMapper = roleMapper;
    }

    public UserIdDto toIdDto(User user) {
        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setUserId(user.getUserId());
        return userIdDto;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRolesDto(user.getRoles().stream().map(
                roleMapper::toDto).collect(Collectors.toSet()));
        return userDto;
    }

    public User toEntity(UserIdDto userIdDto) {
        User user = this.userService.getUserById(userIdDto.getUserId());
        user.setUserId(userIdDto.getUserId());
        return user;
    }
}
