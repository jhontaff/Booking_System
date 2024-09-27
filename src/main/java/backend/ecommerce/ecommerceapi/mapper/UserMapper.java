package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.user.UserDto;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.service.user.UserService;

public class UserMapper {

    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = this.userService.getUserById(userDto.getUserId());
        user.setUserId(userDto.getUserId());
        return user;
    }
}
