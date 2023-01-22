package time;


import enums.TimeOfDay;

public class Time {
    private static int year = 0;
    private static int day = 0;
    private static TimeOfDay time = TimeOfDay.MORNING;

    public static int getYear() {
        return year;
    }

    public static int getDay() {
        return day;
    }

    public static TimeOfDay getTime() {
        return time;
    }

    public static void nextDay() {
        day++;
    }

    public static void nextYear() {
        year++;
    }

    public static void nextDayTime() {
        time = switch (time){
            case MORNING -> TimeOfDay.MIDDAY;
            case MIDDAY -> TimeOfDay.EVENING;
            case EVENING -> TimeOfDay.NIGTH;
            case NIGTH -> TimeOfDay.MORNING;
        };
    }
}