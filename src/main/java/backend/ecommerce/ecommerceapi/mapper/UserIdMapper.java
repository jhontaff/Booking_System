package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.user.UserIdDto;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.service.user.UserService;

public class UserIdMapper {

    private final UserService userService;

    public UserIdMapper(UserService userService) {
        this.userService = userService;
    }

    public UserIdDto toDto(User user) {
        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setUserId(user.getUserId());
        return userIdDto;
    }


    public User toEntity(UserIdDto userIdDto) {
        User user = this.userService.getUserById(userIdDto.getUserId());
        user.setUserId(userIdDto.getUserId());
        return user;
    }
}
