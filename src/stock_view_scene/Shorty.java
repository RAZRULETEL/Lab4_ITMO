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
            System.out.println("коротышка уже залезла на арбуз");
        else {
            System.out.println("коротышки пытались залезть использу€ лестницу");
            if (new Random().nextInt(10) < 3) {
                System.out.println("коротышка залезла на арбуз");
                m.addClimbedShorty(this);
                isOnMelon = true;
            }
        }
    }
    void dance(){
        if(isOnMelon)
            System.out.println("коротышка танцевала на арбузе");
        else
            System.out.println("коротышка танцевала");
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
        return " оротышка{" +
                "најрбузе=" + isOnMelon +
                ", размер=" + size +
                '}';
    }
}
