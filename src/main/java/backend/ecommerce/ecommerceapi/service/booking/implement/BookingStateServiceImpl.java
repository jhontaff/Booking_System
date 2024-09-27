package backend.ecommerce.ecommerceapi.service.booking.implement;

import backend.ecommerce.ecommerceapi.config.exception.BookingException;
import backend.ecommerce.ecommerceapi.entity.booking.BookingState;
import backend.ecommerce.ecommerceapi.repository.booking.BookingStateRepository;
import backend.ecommerce.ecommerceapi.service.booking.BookingStateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingStateServiceImpl implements BookingStateService {

    private final BookingStateRepository bookingStateRepository;

    public BookingStateServiceImpl(BookingStateRepository bookingStateRepository) {
        this.bookingStateRepository = bookingStateRepository;
    }

    @Override
    public List<BookingState> getAllBookingStates() {
        return this.bookingStateRepository.findAll();
    }

    @Override
    public BookingState getBookingStateById(Long bookingStateId) {
        return this.bookingStateRepository.findById(bookingStateId).orElseThrow(
                () -> new BookingException("Booking state not found")
        );
    }
}
