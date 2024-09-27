package backend.ecommerce.ecommerceapi.controller.authentication;

import backend.ecommerce.ecommerceapi.config.exception.EmailNotFoundException;
import backend.ecommerce.ecommerceapi.dto.authentication.request.UserLoginDto;
import backend.ecommerce.ecommerceapi.dto.authentication.request.UserRegisterDto;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.mapper.UserRegisterMapper;
import backend.ecommerce.ecommerceapi.service.authentication.AuthService;
import backend.ecommerce.ecommerceapi.service.authentication.EmailService;
import backend.ecommerce.ecommerceapi.service.authentication.OtpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value ="/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    private final OtpService otpService;

    @Autowired
    private AuthController(AuthService authService, EmailService emailService, OtpService otpService) {
        this.authService = authService;
        this.emailService = emailService;
        this.otpService = otpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        this.authService.validateUserInfo(userRegisterDto);
        this.authService.hashPassword(userRegisterDto);
        User newUser = UserRegisterMapper.toEntity(userRegisterDto);
        this.authService.register(newUser);
        return new ResponseEntity<>(
                userRegisterDto.getEmail() + " has been created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@Valid @RequestBody UserLoginDto userLoginDto) {
        String userEmail = userLoginDto.getEmail();
        if (this.authService.existsByEmail(userEmail).equals(Boolean.TRUE)) {
            String otp = otpService.generateOtp(userEmail);
            this.emailService.sendEmail(userLoginDto.getEmail(), "Here's your OTP ", otp);
            return new ResponseEntity<>("OTP has been sent to your email", HttpStatus.OK);
        } else {
            throw new EmailNotFoundException("Email not found");
        }
    }
}
