package backend.ecommerce.ecommerceapi.service.booking;

import backend.ecommerce.ecommerceapi.entity.booking.BookingState;

import java.util.List;

public interface BookingStateService {
    public List<BookingState> getAllBookingStates();
    public BookingState getBookingStateById(Long bookingStateId);
}

