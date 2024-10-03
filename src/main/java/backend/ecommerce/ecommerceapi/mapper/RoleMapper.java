package backend.ecommerce.ecommerceapi.mapper;


import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.entity.role.Role;

public class RoleMapper {

    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
