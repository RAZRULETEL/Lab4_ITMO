package enums;

public enum TimeOfDay {
    MORNING,
    EVENING,
    MIDDAY,
    NIGTH;

    @Override
    public String toString() {
        return switch (name()) {
            case "MORNING" -> "утро";
            case "EVENING" -> "вечер";
            case "MIDDAY" -> "обед";
            case "NIGTH" -> "ночь";
            default -> "error";
        };
    }
}
