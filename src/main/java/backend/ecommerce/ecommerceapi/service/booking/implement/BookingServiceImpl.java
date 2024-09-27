package backend.ecommerce.ecommerceapi.service.booking.implement;

import backend.ecommerce.ecommerceapi.config.exception.BookingException;
import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.entity.booking.BookingState;
import backend.ecommerce.ecommerceapi.entity.booking.EBookingState;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.repository.booking.BookingRepository;
import backend.ecommerce.ecommerceapi.repository.booking.BookingStateRepository;
import backend.ecommerce.ecommerceapi.service.booking.BookingService;
import backend.ecommerce.ecommerceapi.service.booking.BookingStateService;
import backend.ecommerce.ecommerceapi.service.booking.ExtraResourceService;
import backend.ecommerce.ecommerceapi.service.room.RoomService;
import backend.ecommerce.ecommerceapi.service.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingStateRepository bookingStateRepository;
    private final BookingStateService bookingStateService;
    private final RoomService roomService;
    private final UserService userService;
    private final ExtraResourceService extraResourceService;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingStateService bookingStateService,
                              RoomService roomService,
                              UserService userService,
                                ExtraResourceService extraResourceService,
                              BookingStateRepository bookingStateRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingStateService = bookingStateService;
        this.roomService = roomService;
        this.extraResourceService = extraResourceService;
        this.userService = userService;
        this.bookingStateRepository = bookingStateRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        List<Booking> bookings = this.bookingRepository.findAll();
        return bookings.stream().filter(
                booking -> booking.getBookedBy().getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Booking> getApprovedBookings() {
        List<Booking> bookings = this.bookingRepository.findAll();
        return bookings.stream().filter(
                booking -> booking.getBookingState().getState().equals(EBookingState.APPROVED))
                .toList();
    }

    @Override
    public List<Booking> getPendingBookings() {
        List<Booking> bookings = this.bookingRepository.findAll();
        return bookings.stream().filter(
                        booking -> booking.getBookingState().getState().equals(EBookingState.PENDING))
                .toList();
    }

    @Override
    public List<Booking> getCancelledBookings() {
        List<Booking> bookings = this.bookingRepository.findAll();
        return bookings.stream().filter(
                        booking -> booking.getBookingState().getState().equals(EBookingState.CANCELLED))
                .toList();
    }

    @Override
    public List<Booking> getRejectedBookings() {
        List<Booking> bookings = this.bookingRepository.findAll();
        return bookings.stream().filter(
                        booking -> booking.getBookingState().getState().equals(EBookingState.REJECTED))
                .toList();
    }

    @Override
    public Boolean isRoomAvailable(Long roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
        List<Booking> bookings = this.getApprovedBookings();
        if (bookings.stream().noneMatch(
                booking -> booking.getRoom().getRoomId().equals(roomId) &&
                        booking.getCheckIn().equals(checkIn) &&
                        booking.getCheckOut().equals(checkOut)
        )){
            return true;

        } else  {
            throw new BookingException("Room is not available");
        }
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return this.bookingRepository.findById(bookingId).orElseThrow(
                () -> new BookingException("Booking with id " + bookingId + " not found")
        );
    }

    @Override
    public void validateDateBooking(Booking booking) {
        if (booking.getCheckIn().isBefore(LocalDateTime.now())) {
            throw new BookingException("Check in date is in the past");
        }
        if (booking.getCheckOut().isBefore(booking.getCheckIn())) {
            throw new BookingException("Check out date is before check in date");
        }
    }

    @Override
    public void updateExtraResourceFromBooking(Long extraResourceId) {
        ExtraResource extraResource = this.extraResourceService.getExtraResourceById(extraResourceId);
        List<Booking> bookings = this.getAllBookings().stream().filter(
                booking -> booking.getExtraResource().contains(extraResource)
        ).toList();
        for (Booking booking : bookings) {
            if (booking.getBookingState().getState().equals(EBookingState.APPROVED)) {

                extraResourceService.minusUpdateExtraResource(extraResourceId);

                if (booking.getCheckOut().isBefore(LocalDateTime.now())) {
                extraResourceService.plusUpdateExtraResource(extraResourceId);
                }
            }
        }
    }

    @Override
    public void validateExtraResources(List<ExtraResource> extraResources) {
        extraResources.forEach(
                extraResource -> {
                    try {
                        this.extraResourceService.isExtraResourceAvailable(extraResource.getExtraResourceId());
                        updateExtraResourceFromBooking(extraResource.getExtraResourceId());
                    } catch (BookingException e) {
                        throw new BookingException(e.getMessage());
                    }
                });
    }

    @Override
    public void createBooking(Booking booking) {
        BookingState bookingState = this.bookingStateRepository.findByState(EBookingState.PENDING).orElseThrow(
                () -> new BookingException("Booking state not found")
        );
        booking.setBookingState(bookingState);
        this.bookingRepository.save(booking);
    }

    @Override
    public void changeRoom(Long bookingId, Long newRoomId) {
        Booking booking = getBookingById(bookingId);
        Room room = this.roomService.getRoomById(newRoomId);
        booking.setRoom(room);
    }

    @Override
    public void changeBookingState(Long bookingId, Long newBookingStateId) {
        Booking booking = getBookingById(bookingId);
        BookingState newBookingState = this.bookingStateService.getBookingStateById(newBookingStateId);
        if (booking.getBookingState()==(newBookingState)) {
            throw new BookingException("Booking state is the same as the current booking state");
        } else {
        booking.setBookingState(newBookingState);
        this.bookingRepository.save(booking);
        }
    }

    @Override
    public void changeCheckInDate(Long bookingId, LocalDateTime newCheckInDate) {
        Booking booking = getBookingById(bookingId);
        if (newCheckInDate.isBefore(LocalDateTime.now())) {
            throw new BookingException("Check in date is in the past");
        }
        if (newCheckInDate.isAfter(booking.getCheckOut())) {
            throw new BookingException("Check in date is after check out date");
        }
        booking.setCheckIn(newCheckInDate);
    }

    @Override
    public void changeCheckOutDate(Long bookingId, LocalDateTime newCheckOutDate) {
        Booking booking = getBookingById(bookingId);
        if (newCheckOutDate.isBefore(LocalDateTime.now())) {
            throw new BookingException("Check out date is in the past");
        }
        if (newCheckOutDate.isBefore(booking.getCheckIn())) {
            throw new BookingException("Check out date is before check in date");
        }
        booking.setCheckOut(newCheckOutDate);
    }

    @Override
    public void changeOwner(Long bookingId, Long newUserId) {
        Booking booking = getBookingById(bookingId);
        booking.setBookedBy(this.userService.getUserById(newUserId));
    }
}