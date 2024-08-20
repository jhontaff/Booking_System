package backend.ecommerce.ecommerceapi.repository;

import backend.ecommerce.ecommerceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail(String email);
    Optional<UserDetails> findUserDetailsByEmail(String email);
}
