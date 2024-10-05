package backend.ecommerce.ecommerceapi.controller;


import backend.ecommerce.ecommerceapi.dto.booking.BookingDto;
import backend.ecommerce.ecommerceapi.dto.booking.UpdateBookingStateDto;
import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.mapper.BookingMapper;
import backend.ecommerce.ecommerceapi.service.booking.BookingService;
import backend.ecommerce.ecommerceapi.service.booking.ExtraResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final ExtraResourceService extraResourceService;

    public BookingController(BookingService bookingService,
                             BookingMapper bookingMapper,
                             ExtraResourceService extraResourceService) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.extraResourceService = extraResourceService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = this.bookingMapper.toEntity(bookingDto);
        this.bookingService.validateDateBooking(booking);
        List<ExtraResource> extraResources = booking.getExtraResource();
        for (ExtraResource extraResource : extraResources) {
            this.extraResourceService.isExtraResourceAvailable(extraResource.getExtraResourceId());
        }
        this.bookingService.isRoomAvailable(booking.getRoom().getRoomId(), booking.getCheckIn(), booking.getCheckOut());
        this.bookingService.createBooking(booking);
        return new ResponseEntity<>("Booking created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/update-booking-state")
    public ResponseEntity<String> updateBookingState(@RequestBody UpdateBookingStateDto updateBookingStateDto) {
        this.bookingService.changeBookingState(updateBookingStateDto.getBookingId(),updateBookingStateDto.getBookingStateId());
        List<ExtraResource> extraResources = this.bookingService.getBookingById(updateBookingStateDto.getBookingId()).getExtraResource();
        if (updateBookingStateDto.getBookingStateId() == 4) {
            for (ExtraResource extraResource : extraResources) {
                this.extraResourceService.minusUpdateExtraResource(extraResource.getExtraResourceId());
            }
        }
        return new ResponseEntity<>("Booking state updated successfully", HttpStatus.OK);
    }

    @GetMapping("/get-all-bookings")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<Booking> bookings = this.bookingService.getAllBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-booking/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable Long bookingId) {
        Booking booking = this.bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(bookingMapper.toDto(booking), HttpStatus.OK);
    }

    @GetMapping("/get-approved-bookings")
    public ResponseEntity<List<BookingDto>> getApprovedBookings() {
        List<Booking> bookings = this.bookingService.getApprovedBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-pending-bookings")
    public ResponseEntity<List<BookingDto>> getPendingBookings() {
        List<Booking> bookings = this.bookingService.getPendingBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-rejected-bookings")
    public ResponseEntity<List<BookingDto>> getRejectedBookings() {
        List<Booking> bookings = this.bookingService.getRejectedBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-canceled-bookings")
    public ResponseEntity<List<BookingDto>> getCanceledBookings() {
        List<Booking> bookings = this.bookingService.getCancelledBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/get-done-bookings")
    public ResponseEntity<List<BookingDto>> getDoneBookings() {
        List<Booking> bookings = this.bookingService.getDoneBookings();
        return new ResponseEntity<>(bookings.stream().map(
                bookingMapper::toDto).toList(), HttpStatus.OK);
    }


}
