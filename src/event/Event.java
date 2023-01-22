package event;

import java.util.Random;

public class Event {
    private static final String[] EVENTS = {"забил наконец нефтяной фонтан","упал метеорит","началась 3 мировая"};

    public static String getEvent(){
        if(new Random().nextInt(10) < 3)
            return EVENTS[new Random().nextInt(EVENTS.length)];
        return null;
    };

}
