package enums;

public enum Instructions {
    COUNT_MONEY("����� $ ����������� ������"),
    GIVE_STOCK(" ����� $ ������ �����"),
    SIT_IN_CHAIR("����� $ ����� � ������"),
    COME("������ $ �������"),
    PROPOSE_MEETING("�������� ��������"),
    OFFER_DINNER("��������� ���������"),
    START_DIALOGUE("����� �������� � ���������� ���������"),
    PRAISE_CITY("������ �����"),
    PRAISE_HUMAN("������ ��������");
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
