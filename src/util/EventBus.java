package util;

import util.AppConstants;
import model.hub.DeviceEvent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public final class EventBus {
    private static volatile EventBus instance;

    private final Map<AppConstants.EventType, List<Consumer<DeviceEvent>>> listenersByType = new ConcurrentHashMap<>();

    private EventBus() {
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

    public void subscribe(AppConstants.EventType type, Consumer<DeviceEvent> listener) {
        listenersByType
                .computeIfAbsent(type, key -> new CopyOnWriteArrayList<>())
                .add(listener);
    }

    public void publish(DeviceEvent event) {
        List<Consumer<DeviceEvent>> listeners = listenersByType.get(event.getEventType());
        if (listeners == null) {
            return;
        }
        for (Consumer<DeviceEvent> listener : listeners) {
            listener.accept(event);
        }
    }
}
