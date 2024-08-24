package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.authentication.request.UserRegisterDto;
import backend.ecommerce.ecommerceapi.entity.User;


public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setUsername(userRegisterDto.getUsername());
        user.setLastname(userRegisterDto.getLastname());
        user.setRoles(userRegisterDto.getRoles());
        return user;
    }

    public static UserRegisterDto toDto(User user) {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail(user.getEmail());
        userRegisterDto.setPassword(user.getPassword());
        userRegisterDto.setUsername(user.getUsername());
        userRegisterDto.setLastname(user.getLastname());
        userRegisterDto.setRoles(user.getRoles());
        return userRegisterDto;
    }

}
