package util;

public class UIStyles {

    // ── Buttons ───────────────────────────────────────────────────────
    public static final String BTN_PRIMARY =
            "-fx-background-color: #6c63ff;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 9 20 9 20;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(108,99,255,0.4), 8, 0, 0, 3);";

    public static final String BTN_SECONDARY =
            "-fx-background-color: #eef0f4;" +
                    "-fx-text-fill: #2d3436;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 16 8 16;" +
                    "-fx-cursor: hand;";

    public static final String BTN_GHOST =
            "-fx-background-color: transparent;" +
                    "-fx-text-fill: #6c63ff;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-color: #6c63ff;" +
                    "-fx-border-radius: 8;" +
                    "-fx-padding: 7 15 7 15;" +
                    "-fx-cursor: hand;";

    public static final String BTN_SUCCESS =
            "-fx-background-color: #00b894;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 9 20 9 20;" +
                    "-fx-cursor: hand;";

    public static final String BTN_DANGER =
            "-fx-background-color: #ff6584;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 9 20 9 20;" +
                    "-fx-cursor: hand;";

    public static final String BTN_HEADER_GHOST =
            "-fx-background-color: rgba(255,255,255,0.12);" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 7 14 7 14;" +
                    "-fx-cursor: hand;";

    // ── Toggle Buttons ────────────────────────────────────────────────
    public static final String TOGGLE_SELECTED =
            "-fx-background-color: #6c63ff;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 18 8 18;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(108,99,255,0.35), 6, 0, 0, 2);";

    public static final String TOGGLE_UNSELECTED =
            "-fx-background-color: #eef0f4;" +
                    "-fx-text-fill: #636e72;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 18 8 18;" +
                    "-fx-cursor: hand;";

    // ── Cards ─────────────────────────────────────────────────────────
    public static final String CARD =
            "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.07), 12, 0, 0, 3);";

    public static final String CARD_HOVER =
            "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(108,99,255,0.18), 16, 0, 0, 5);";

    // ── Labels ────────────────────────────────────────────────────────
    public static final String LABEL_PAGE_TITLE =
            "-fx-font-size: 22px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #2d3436;";

    public static final String LABEL_SUBTITLE =
            "-fx-font-size: 12px;" +
                    "-fx-text-fill: #636e72;";

    public static final String LABEL_SECTION_HEADER =
            "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #b2bec3;";

    public static final String LABEL_DEVICE_NAME =
            "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #2d3436;";

    // ── Status dot ────────────────────────────────────────────────────
    public static String statusDotStyle(String status) {
        String color = switch (status) {
            case "ONLINE" -> "#00b894";
            case "ERROR"  -> "#d63031";
            default       -> "#b2bec3";
        };
        return "-fx-background-color: " + color + ";" +
                "-fx-background-radius: 5;" +
                "-fx-pref-width: 8;" +
                "-fx-pref-height: 8;";
    }
}