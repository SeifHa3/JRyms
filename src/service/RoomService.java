package service;

import model.device.SmartDevice;
import model.hub.DeviceRegistry;
import model.room.Room;
import model.room.RoomRegistry;

import java.util.List;

public class RoomService {

    private final RoomRegistry roomRegistry;
    private final DeviceRegistry deviceRegistry;

    public RoomService() {
        this.roomRegistry = RoomRegistry.getInstance();
        this.deviceRegistry = DeviceRegistry.getInstance();
    }

    public Room createRoom(String name, String icon) {
        String id = "room" + System.currentTimeMillis();
        Room room = new Room(id, name, icon);
        roomRegistry.addRoom(room);
        return room;
    }

    public void assignDeviceToRoom(String deviceId, String roomId) {
        SmartDevice device = deviceRegistry.getDevice(deviceId);
        Room room = roomRegistry.getRoom(roomId);
        if (device == null || room == null) return;
        device.setRoom(room);
        room.addDevice(device);
    }

    public List<Room> getAllRooms() {
        return roomRegistry.getAllRooms();
    }
}