package backend.ecommerce.ecommerceapi.controller;

import backend.ecommerce.ecommerceapi.dto.user.UserAuthResponseDto;
import backend.ecommerce.ecommerceapi.dto.user.UserLoginDto;
import backend.ecommerce.ecommerceapi.dto.user.UserRegisterDto;
import backend.ecommerce.ecommerceapi.entity.User;
import backend.ecommerce.ecommerceapi.mapper.UserMapper;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value ="/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        this.userService.validateUserInfo(userRegisterDto);
        this.userService.hashPassword(userRegisterDto);
        User newUser = UserMapper.toEntity(userRegisterDto);
        this.userService.register(newUser);
        return new ResponseEntity<>(
                userRegisterDto.getEmail() + " has been created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserAuthResponseDto> signin(@Valid @RequestBody UserLoginDto userLoginDto) {
        return new ResponseEntity<>(this.userService.login(userLoginDto), HttpStatus.OK);
    }

}
