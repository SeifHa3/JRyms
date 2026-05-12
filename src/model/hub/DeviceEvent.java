package model.hub;

import util.AppConstants;
import util.AppConstants;

import java.time.LocalDateTime;

public class DeviceEvent {

    private final String deviceId;
    private final AppConstants.EventType eventType;
    private final LocalDateTime timestamp;
    private Object payload; //Object variable that can be replaced by anything like: Device_added which would be String or Temp_Changed: double

    public DeviceEvent(String deviceId, AppConstants.EventType eventType, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    public String getDeviceId()                  {
        return deviceId;
    }

    public AppConstants.EventType getEventType() {
        return eventType;
    }

    public Object getPayload()                   {
        return payload;
    }
    public LocalDateTime getTimestamp()          {
        return timestamp;
    }
}