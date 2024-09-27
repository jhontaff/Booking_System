package backend.ecommerce.ecommerceapi.service.room;

import backend.ecommerce.ecommerceapi.entity.room.RoomType;

import java.util.List;

public interface RoomTypeService {

    public List<RoomType> getAllRoomTypes();

    public RoomType getRoomTypeById(Long roomTypeId);


}
