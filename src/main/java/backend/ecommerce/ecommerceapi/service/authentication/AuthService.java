package backend.ecommerce.ecommerceapi.service.authentication;

import backend.ecommerce.ecommerceapi.dto.authentication.request.UserLoginDto;
import backend.ecommerce.ecommerceapi.dto.authentication.request.UserRegisterDto;
import backend.ecommerce.ecommerceapi.dto.authentication.response.UserAuthResponseDto;
import backend.ecommerce.ecommerceapi.entity.user.User;

public interface AuthService {

    Boolean passwordMatch(String password, String confirmPassword);
    Boolean existsByEmail(String email);
    Boolean validateUserInfo(UserRegisterDto userRegisterDto);
    void hashPassword(UserRegisterDto userRegisterDto);
    UserAuthResponseDto register(User user);
    UserAuthResponseDto login(UserLoginDto userLoginDto);
}
