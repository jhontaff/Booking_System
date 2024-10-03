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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
