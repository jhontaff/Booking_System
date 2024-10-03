package backend.ecommerce.ecommerceapi.service.user.implement;

import backend.ecommerce.ecommerceapi.dto.role.RoleDto;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.mapper.RoleMapper;
import backend.ecommerce.ecommerceapi.repository.UserRepository;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id %d not found".formatted(userId))
        );
    }

    @Override
    public Integer getUserId(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email %s not found".formatted(email))
        );
        return user.getUserId().intValue();
    }

    @Override
    public Set<RoleDto> getUserRoles(Long userId) {
        User user = this.getUserById(userId);
        return user.getRoles().stream().map(roleMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

}
