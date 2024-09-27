package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.booking.RoomDto;
import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.service.room.RoomService;

public class RoomMapper {

    private final RoomService roomService;

    public RoomMapper(RoomService roomService) {
        this.roomService = roomService;
    }

    public RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(room.getRoomId());
        return roomDto;
    }

    public Room toEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        return room;
    }
}
