package backend.ecommerce.ecommerceapi.repository.booking;

import backend.ecommerce.ecommerceapi.entity.booking.BookingState;
import backend.ecommerce.ecommerceapi.entity.booking.EBookingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingStateRepository extends JpaRepository<BookingState, Long> {
    public Optional <BookingState> findByState(EBookingState state);
}
