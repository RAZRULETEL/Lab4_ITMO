package enums;

public enum Instructions {
    COUNT_MONEY("велел $ пересчитать деньги"),
    GIVE_STOCK(" велел $ выдать акции"),
    SIT_IN_CHAIR("велел $ сесть в кресло"),
    COME("сказал $ подойти"),
    PROPOSE_MEETING("назначил свидание"),
    OFFER_DINNER("предложил поужинать"),
    START_DIALOGUE("начал разговор с отдаленных предметов"),
    PRAISE_CITY("хвалил город"),
    PRAISE_HUMAN("хвалил человека");
    private final String action;

    Instructions(String s) {
        action = s;
    }

    public String getDescription(String firstName, String secondName) {
        if(!action.contains("$"))
            return  firstName +" "+ action+" "+secondName;
        else
            return firstName +" "+ action.replaceFirst("\\$",secondName);

    }
}
