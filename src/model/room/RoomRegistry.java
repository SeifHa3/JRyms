package model.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRegistry {

    private static volatile RoomRegistry instance;
    private final Map<String, Room> rooms;

    private RoomRegistry() {
        rooms = new HashMap<>();
        // pre-populate 3 default rooms
        addRoom(new Room("room1", "Living Room", "🛋"));
        addRoom(new Room("room2", "Bedroom", "🛏"));
        addRoom(new Room("room3", "Kitchen", "🍳"));
    }

    public static RoomRegistry getInstance() {
        if (instance == null) {
            synchronized (RoomRegistry.class) {
                if (instance == null) {
                    instance = new RoomRegistry();
                }
            }
        }
        return instance;
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    public Room getRoom(String id) {
        return rooms.get(id);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }
}