package enums;

public enum TimeOfDay {
    MORNING,
    EVENING,
    MIDDAY,
    NIGTH;

    @Override
    public String toString() {
        return switch (name()) {
            case "MORNING" -> "����";
            case "EVENING" -> "�����";
            case "MIDDAY" -> "����";
            case "NIGTH" -> "����";
            default -> "error";
        };
    }
}
