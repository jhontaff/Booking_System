package backend.ecommerce.ecommerceapi.repository;

import backend.ecommerce.ecommerceapi.entity.ERole;
import backend.ecommerce.ecommerceapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole roleName);

}
