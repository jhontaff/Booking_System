package backend.ecommerce.ecommerceapi.dto.role;

import backend.ecommerce.ecommerceapi.entity.role.ERole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleDto {

    private Long roleId;
    private ERole roleName;
}
