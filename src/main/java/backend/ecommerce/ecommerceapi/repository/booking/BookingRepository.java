package backend.ecommerce.ecommerceapi.repository.booking;

import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
