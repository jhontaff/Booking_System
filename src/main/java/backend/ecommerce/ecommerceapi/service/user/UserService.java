package backend.ecommerce.ecommerceapi.service.user;

import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.entity.user.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User getUserById(Long userId);
    public Integer getUserId(String email);
    public Set<RoleDto> getUserRoles(Long userId);
    public void saveUser(User user);
    public List<User>getAllUsers();
}
