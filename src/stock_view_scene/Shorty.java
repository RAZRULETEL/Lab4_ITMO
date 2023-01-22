package stock_view_scene;

import java.util.Objects;
import java.util.Random;

import interfaces.IHaveSize;

public class Shorty implements IHaveSize {
    private boolean isOnMelon;
    private int size;

    public Shorty(int size) {
        this.size = size;
    }

    void tryClimbMelon(Melon m){
        if(isOnMelon)
            System.out.println("��������� ��� ������� �� �����");
        else {
            System.out.println("��������� �������� ������� ��������� ��������");
            if (new Random().nextInt(10) < 3) {
                System.out.println("��������� ������� �� �����");
                m.addClimbedShorty(this);
                isOnMelon = true;
            }
        }
    }
    void dance(){
        if(isOnMelon)
            System.out.println("��������� ��������� �� ������");
        else
            System.out.println("��������� ���������");
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shorty shorty = (Shorty) o;
        return isOnMelon == shorty.isOnMelon && size == shorty.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOnMelon, size);
    }

    @Override
    public String toString() {
        return "���������{" +
                "��������=" + isOnMelon +
                ", ������=" + size +
                '}';
    }
}
