package backend.ecommerce.ecommerceapi.service.room.implement;

import backend.ecommerce.ecommerceapi.config.exception.RoomException;
import backend.ecommerce.ecommerceapi.entity.room.Room;
import backend.ecommerce.ecommerceapi.repository.room.RoomRepository;
import backend.ecommerce.ecommerceapi.service.room.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getRoomById(Long roomId) {
        return this.roomRepository.findById(roomId).orElseThrow(
                () -> new RoomException("Room not found")
        );
    }

    @Override
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }


}
