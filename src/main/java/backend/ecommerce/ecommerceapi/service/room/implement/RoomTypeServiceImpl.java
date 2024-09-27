package backend.ecommerce.ecommerceapi.service.room.implement;

import backend.ecommerce.ecommerceapi.config.exception.RoomException;
import backend.ecommerce.ecommerceapi.entity.room.RoomType;
import backend.ecommerce.ecommerceapi.repository.room.RoomTypeRepository;
import backend.ecommerce.ecommerceapi.service.room.RoomTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return this.roomTypeRepository.findAll();
    }

    @Override
    public RoomType getRoomTypeById(Long roomTypeId) {
        return this.roomTypeRepository.findById(roomTypeId).orElseThrow(
                () -> new RoomException("Room type not found")
        );
    }
}
