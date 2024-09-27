package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.booking.BookingDto;
import backend.ecommerce.ecommerceapi.entity.booking.Booking;
import backend.ecommerce.ecommerceapi.service.user.UserService;

public class BookingMapper {

    private final UserService userService;
    private final ExtraResourceMapper extraResourceMapper;
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;


    public BookingMapper(UserService userService,
                          ExtraResourceMapper extraResourceMapper,
                          RoomMapper roomMapper,
                          UserMapper userMapper) {
        this.userService = userService;
        this.extraResourceMapper = extraResourceMapper;
        this.roomMapper = roomMapper;
        this.userMapper = userMapper;
    }


    public BookingDto toDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCheckIn(booking.getCheckIn());
        bookingDto.setCheckOut(booking.getCheckOut());
        bookingDto.setUserDto(this.userMapper.toDto(booking.getBookedBy()));
        bookingDto.setRoomDto(roomMapper.toDto(booking.getRoom()));
        bookingDto.setExtraResourceDto(booking.getExtraResource().stream()
                .map(extraResourceMapper::toDto)
                .toList());
        return bookingDto;
    }

    public Booking toEntity(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setCheckIn(bookingDto.getCheckIn());
        booking.setCheckOut(bookingDto.getCheckOut());
        booking.setBookedBy(this.userService.getUserById(bookingDto.getUserDto().getUserId()));
        booking.setRoom(roomMapper.toEntity(bookingDto.getRoomDto()));
        booking.setExtraResource(bookingDto.getExtraResourceDto().stream()
                .map(extraResourceMapper::toEntity).toList());
        return booking;
    }
}
