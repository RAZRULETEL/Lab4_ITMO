package event;

import java.util.Random;

public class Event {
    private static final String[] EVENTS = {"����� ������� �������� ������","���� ��������","�������� 3 �������"};

    public static String getEvent(){
        if(new Random().nextInt(10) < 3)
            return EVENTS[new Random().nextInt(EVENTS.length)];
        return null;
    };

}
