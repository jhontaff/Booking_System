package backend.ecommerce.ecommerceapi.entity.booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extra_resource")
public class ExtraResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_resource_id")
    private Long extraResourceId;

    private String resourceName;

    private Integer quantity;

    @ManyToMany(mappedBy = "extraResource")
    private List<Booking> bookings;
}
