package util;

import java.util.List;

public class AppConstants {

    private AppConstants(){}
    public static final String APP_NAME = "JRyms";

    public static final double DEFAULT_TEMP = 22.0;
    public static final int MIN_TEMP = 16;
    public static final int MAX_TEMP = 30;

    public static final List<String> DEFAULT_ROOM_NAMES = List.of("Living Room", "Bedroom", "Kitchen");

    public enum EventType{
        DEVICE_ADDED,
        DEVICE_REMOVED,
        TEMP_CHANGED,
        MODE_CHANGED,
        DEVICE_TOGGLE,
        DEVICE_STATE_CHANGED,
        NOTIFICATION
    }
}
