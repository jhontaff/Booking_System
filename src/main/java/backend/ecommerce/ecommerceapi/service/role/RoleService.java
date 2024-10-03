package backend.ecommerce.ecommerceapi.service.role;

import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.entity.role.Role;

import java.util.Set;

public interface RoleService {

    public Set<RoleDto> getAllRoles();

    public Role getRoleById(Long roleId);

}
