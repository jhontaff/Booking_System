package backend.ecommerce.ecommerceapi.dto.user;

import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class UserDto {

    private String username;

    private String lastname;

    private String email;

    private Set<RoleDto> rolesDto;
}
