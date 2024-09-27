package backend.ecommerce.ecommerceapi.entity.booking;

import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User bookedBy;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    @ManyToOne
    @JoinColumn(name = "booking_state_id", nullable = false)
    private BookingState bookingState;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "Booking_ExtraResource",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_resource_id", referencedColumnName = "extra_resource_id"))
    private List<ExtraResource> extraResource;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    
}
