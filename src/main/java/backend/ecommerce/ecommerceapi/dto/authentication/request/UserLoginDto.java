package backend.ecommerce.ecommerceapi.dto.authentication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "email can't be empty")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password can't be empty")
    @Pattern(
            regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[_.-])(?=\\S+$)[A-Za-z0-9_.-]+$",
            message="Invalid password")
    private String password;

}
