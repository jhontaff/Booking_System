package backend.ecommerce.ecommerceapi.repository.room;

import backend.ecommerce.ecommerceapi.entity.room.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
