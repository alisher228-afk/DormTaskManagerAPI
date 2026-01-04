package org.example.dormtaskmanagerapi.presentation.controller;

import org.example.dormtaskmanagerapi.Dto.RoomResponse;
import org.example.dormtaskmanagerapi.entity.Room;
import org.example.dormtaskmanagerapi.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomResponse getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Room deleteRoomById(@PathVariable Long id) {
        return roomService.deleteRoomById(id);
    }
}
