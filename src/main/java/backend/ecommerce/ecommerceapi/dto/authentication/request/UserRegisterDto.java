package backend.ecommerce.ecommerceapi.dto.authentication.request;

import backend.ecommerce.ecommerceapi.entity.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "user name can't be empty")
    @Size(min=4, max=20, message="user name must be between 4 and 20 characters")
    @Pattern(regexp= "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s'-]+$", message="user name can only contain letters")
    private String username;

    @NotBlank(message = "lastname can't be empty")
    @Size(min=4, max=20, message="lastname must be between 4 and 20 characters")
    @Pattern(regexp="^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s'-]+$", message="lastname can only contain letters")
    private String lastname;

    @NotBlank(message = "email can't be empty")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password can't be empty")
    @Size(min=8, max=20, message="password must be between 8 and 20 characters")
    @Pattern(
            regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[_.-])(?=\\S+$)[A-Za-z0-9_.-]+$",
            message="password must contain: a minimum of 1 lower case letter [a-z] and a minimum of 1 upper case letter [A-Z] and a minimum of 1 numeric character [0-9] and a minimum of 1 special character: _ - .")
    private String password;

    @NotBlank(message = "confirm password can't be empty")
    @Size(min=8, max=20, message=" confirm password must be between 8 and 20 characters")
    @Pattern(
            regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[_.-])(?=\\S+$)[A-Za-z0-9_.-]+$",
            message="password must contain: a minimum of 1 lower case letter [a-z] and a minimum of 1 upper case letter [A-Z] and a minimum of 1 numeric character [0-9] and a minimum of 1 special character: _ - .")
    private String confirmPassword;

    private Set<Role> roles;

}
