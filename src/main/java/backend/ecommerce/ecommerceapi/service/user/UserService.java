package backend.ecommerce.ecommerceapi.service.user;

import backend.ecommerce.ecommerceapi.dto.user.UserAuthResponseDto;
import backend.ecommerce.ecommerceapi.dto.user.UserRegisterDto;
import backend.ecommerce.ecommerceapi.dto.user.UserLoginDto;
import backend.ecommerce.ecommerceapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Boolean passwordMatch(String password, String confirmPassword);
    Boolean existsByEmail(String email);
    Boolean validateUserInfo(UserRegisterDto userRegisterDto);
    void hashPassword(UserRegisterDto userRegisterDto);
    UserAuthResponseDto register(User user);
    UserAuthResponseDto login(UserLoginDto userLoginDto);
}
