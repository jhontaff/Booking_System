package backend.ecommerce.ecommerceapi.service.user;

import backend.ecommerce.ecommerceapi.entity.user.User;

public interface UserService {

    public User getUserById(Long userId);

    public Integer getUserId(String email);
}
