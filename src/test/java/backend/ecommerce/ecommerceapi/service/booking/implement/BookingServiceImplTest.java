package backend.ecommerce.ecommerceapi.service.booking.implement;

import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.entity.booking.BookingState;
import backend.ecommerce.ecommerceapi.entity.booking.EBookingState;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.entity.user.User;
import backend.ecommerce.ecommerceapi.repository.booking.BookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private User user;
    private Booking booking;
    private Booking booking2;
    private  BookingState pendingState;
    private  BookingState approvedState;
    private ExtraResource extraResource;
    private Room room;

    @BeforeEach
    void setUp() {

        extraResource = new ExtraResource();
        extraResource.setExtraResourceId(1L);
        extraResource.setResourceName("Extra Resource 1");

        pendingState = new BookingState();
        pendingState.setBookingStateId(1L);
        pendingState.setState(EBookingState.PENDING);

        approvedState = new BookingState();
        approvedState.setBookingStateId(4L);
        approvedState.setState(EBookingState.APPROVED);

        user = new User();
        user.setUserId(1L);
        user.setEmail("empanada@hotmail.com");

        room = new Room();
        room.setRoomId(1L);
        room.setRoomName("Room 1");

        booking = new Booking();
        booking.setBookingId(1L);
        booking.setCheckIn(LocalDateTime.parse("2024-09-01T00:00:00"));
        booking.setCheckOut(LocalDateTime.parse("2024-09-01T01:00:00"));
        booking.setBookedBy(user);
        booking.setBookingState(pendingState);
        booking.setExtraResource(List.of(extraResource));

        booking2 = new Booking();
        booking2.setBookingId(2L);
        booking2.setCheckIn(LocalDateTime.parse("2024-09-01T02:00:00"));
        booking2.setCheckOut(LocalDateTime.parse("2024-09-01T03:00:00"));
        booking2.setRoom(room);
        booking2.setBookedBy(user);
        booking2.setBookingState(approvedState);
        booking2.setExtraResource(List.of(extraResource));

    }

    @AfterEach
    void tearDown() {
        reset(bookingRepository);
    }

    @Test
    void getAllBookings() {
        List<Booking> bookings = List.of(booking, booking2);
        when(bookingService.getAllBookings()).thenReturn(bookings);
        List<Booking> bookingsResult = bookingService.getAllBookings();
        verify(bookingRepository, times(1)).findAll();
        assertEquals(bookings, bookingsResult);
    }

    @Test
    void getPendingBookings() {
        List<Booking> bookings = List.of(booking);
        when(bookingService.getPendingBookings()).thenReturn(bookings);
        List<Booking> bookingsResult = bookingService.getPendingBookings();
        verify(bookingRepository, times(1)).findAll();
        assertEquals(bookings, bookingsResult);
    }

    @Test
    void isRoomAvailable() {
        List<Booking> bookings = List.of(booking, booking2);
        when(bookingRepository.findAll()).thenReturn(bookings);
        boolean result = bookingService.isRoomAvailable(room.getRoomId(), booking.getCheckIn(), booking.getCheckOut());
        verify(bookingRepository, times(1)).findAll();
        assertTrue(result);
    }

}