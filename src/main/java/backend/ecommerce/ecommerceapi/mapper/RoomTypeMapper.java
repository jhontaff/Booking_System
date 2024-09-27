package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.booking.RoomTypeDto;
import backend.ecommerce.ecommerceapi.entity.room.RoomType;
import backend.ecommerce.ecommerceapi.service.room.RoomTypeService;

public class RoomTypeMapper {

    private final RoomTypeService roomTypeService;

    public RoomTypeMapper(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    public RoomTypeDto toDto(RoomType roomType) {
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setRoomTypeId(roomType.getRoomTypeId());
        return roomTypeDto;
    }

    public RoomType toEntity(RoomTypeDto roomTypeDto) {
        return this.roomTypeService.getRoomTypeById(roomTypeDto.getRoomTypeId());
    }
}
