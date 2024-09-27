package backend.ecommerce.ecommerceapi.repository.room;

import backend.ecommerce.ecommerceapi.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
