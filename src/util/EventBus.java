package util;

import model.hub.DeviceEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class EventBus {

    private static volatile EventBus instance;
    private final Map<AppConstants.EventType, List<Consumer<DeviceEvent>>> subscribers;

    private EventBus() {
        subscribers = new HashMap<>();
    }

    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void subscribe(AppConstants.EventType type, Consumer<DeviceEvent> handler) {
        subscribers.computeIfAbsent(type, k -> new ArrayList<>()).add(handler);
    }

    public void publish(DeviceEvent event) {
        List<Consumer<DeviceEvent>> handlers = subscribers.get(event.getEventType());
        if (handlers == null) return;
        for (Consumer<DeviceEvent> handler : handlers) {
            handler.accept(event);
        }
    }
}