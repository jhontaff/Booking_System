package backend.ecommerce.ecommerceapi.service.user.implement;

import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.repository.UserRepository;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
