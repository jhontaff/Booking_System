package backend.ecommerce.ecommerceapi.dto.authentication.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequestDto {

    private UserLoginDto userLoginDto;
    private String otp;
}
