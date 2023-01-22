package stock_view_scene;

import java.util.Objects;

import interfaces.IHaveSize;

public class Cucumber implements IHaveSize {
    private int size;

    public Cucumber(int size) {
        this.size = size;
    }

    public void mature(){
        size += size*0.1+1;
        System.out.println(" зреет");
    }
    public void compareSizeWith(IHaveSize what){
        if(what.getSize() < size){
            if(what instanceof Shorty)
                System.out.println("Огурцы размером с коротышку");
            else
                System.out.println("Огурцы размером с "+what);
        }
        System.out.println("Огурцы меньше "+what);
    }
    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "Cucumber{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cucumber cucumber = (Cucumber) o;
        return size == cucumber.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
