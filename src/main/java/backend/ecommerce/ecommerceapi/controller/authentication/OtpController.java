package backend.ecommerce.ecommerceapi.controller.authentication;

import backend.ecommerce.ecommerceapi.config.exception.OtpException;
import backend.ecommerce.ecommerceapi.dto.authentication.request.OtpRequestDto;
import backend.ecommerce.ecommerceapi.dto.authentication.response.UserAuthResponseDto;
import backend.ecommerce.ecommerceapi.service.authentication.AuthService;
import backend.ecommerce.ecommerceapi.service.authentication.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/otp")
public class OtpController {

    private final OtpService otpService;
    private final AuthService authService;

    public OtpController(OtpService otpService, AuthService authService) {
        this.otpService = otpService;
        this.authService = authService;
    }

    @PostMapping("/validate")
    public ResponseEntity<UserAuthResponseDto> validateOtp(@RequestBody OtpRequestDto otpRequestDto) {
        String userEmail = otpRequestDto.getUserLoginDto().getEmail();
        String otp = otpRequestDto.getOtp();
        try {
            otpService.validateOtp(userEmail, otp);
            return new ResponseEntity<>(this.authService.login(otpRequestDto.getUserLoginDto()), HttpStatus.OK);
        } catch (OtpException e) {
            throw new OtpException(e.getMessage());
        }
    }
}
