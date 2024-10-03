package backend.ecommerce.ecommerceapi.service.role.impl;

import backend.ecommerce.ecommerceapi.config.exception.RoleException;
import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.entity.role.Role;
import backend.ecommerce.ecommerceapi.mapper.RoleMapper;
import backend.ecommerce.ecommerceapi.repository.RoleRepository;
import backend.ecommerce.ecommerceapi.service.role.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    @Override
    public Set<RoleDto> getAllRoles() {
        return this.roleRepository.findAll().stream().map(
                roleMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Role getRoleById(Long roleId){
        return this.roleRepository.findById(roleId).orElseThrow(
                () -> new RoleException("Role with id %d not found".formatted(roleId))
        );
    }
}

