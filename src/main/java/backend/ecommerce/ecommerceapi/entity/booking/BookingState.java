package backend.ecommerce.ecommerceapi.entity.booking;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "booking_state")
public class BookingState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_state_id")
    private Long bookingStateId;

    @Enumerated(EnumType.STRING)
    private EBookingState state;

    @OneToMany(mappedBy = "bookingState")
    private List<Booking> bookings;

}
