package backend.ecommerce.ecommerceapi.dto.booking;

import backend.ecommerce.ecommerceapi.dto.user.UserIdDto;
import backend.ecommerce.ecommerceapi.entity.booking.EBookingState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long bookingId;

    private UserIdDto userIdDto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime checkIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime checkOut;

    private List<ExtraResourceDto> extraResourceDto;

    private RoomDto roomDto;

    private EBookingState bookingState;
}
