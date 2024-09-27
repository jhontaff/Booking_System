package backend.ecommerce.ecommerceapi.repository.booking;

import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraResourceRepository extends JpaRepository<ExtraResource, Long> {
}
