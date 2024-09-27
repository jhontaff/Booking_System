package backend.ecommerce.ecommerceapi.service.room;

import backend.ecommerce.ecommerceapi.entity.room.Room;

import java.util.List;

public interface RoomService {

    public Room getRoomById(Long roomId);

    public List<Room> getAllRooms();

}
