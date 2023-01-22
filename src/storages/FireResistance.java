package storages;

import java.util.Objects;

public class FireResistance extends Feature{
    private String name;
    private int resistEfficiency;

    public FireResistance(String name, int efficiency) {
        super(name);
        this.name = name;
        this.resistEfficiency = efficiency;
    }
    String getName() {return this.name;}
    int getEfficiency(){return this.resistEfficiency;}
    @Override
    public String toString() {return name + " "+ resistEfficiency;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireResistance that = (FireResistance) o;
        return resistEfficiency == that.resistEfficiency && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resistEfficiency);
    }
}
