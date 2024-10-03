package backend.ecommerce.ecommerceapi.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRoleDto {

    private Long userId;
    private Long roleId;
    private Boolean plusMinus;
}
