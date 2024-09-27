package backend.ecommerce.ecommerceapi.dto.booking;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateBookingStateDto {

    private Long bookingId;
    private Long bookingStateId;
}
