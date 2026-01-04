package org.example.dormtaskmanagerapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dormtaskmanagerapi.Dto.RoomResponse;
import org.example.dormtaskmanagerapi.Dto.UserShortResponse;
import org.example.dormtaskmanagerapi.entity.Room;
import org.example.dormtaskmanagerapi.entity.repository.RoomRepository;
import org.example.dormtaskmanagerapi.entity.repository.TaskRepository;
import org.example.dormtaskmanagerapi.entity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    public static final Logger log = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public Room createRoom(Room room) {
        log.info("Creating room {}", room);
        roomRepository.save(room);
        return room;
    }
    public List<Room> getAllRooms() {
        log.info("Getting all rooms");
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("Room List is empty");
        }
        return rooms;
        
    }
    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        List<UserShortResponse> users = userRepository.findByRoomId(id)
                .stream()
                .map(u -> new UserShortResponse(u.getId(), u.getName()))
                .toList();

        return new RoomResponse(room.getId(), room.getName(), users);
    }
    
    public Room deleteRoomById(Long id) {
        log.info("Deleting room by id {}", id);
        Room room = roomRepository.findById( id)
                .orElseThrow(()->new EntityNotFoundException("Room with id " + id + " not found"));
        if(userRepository.existsByRoomId(id)) {
            throw  new IllegalStateException("cannot delete room with users");
        }
        if(taskRepository.existsByRoomId(id)) {
            throw  new IllegalStateException("cannot delete room with tasks");
        }
        roomRepository.delete(room);
        return room;
    }
}
