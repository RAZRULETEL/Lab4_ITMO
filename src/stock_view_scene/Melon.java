package stock_view_scene;

import java.util.Arrays;

public class Melon {
    private Shorty[] nearbyShorties;
    private Shorty[] shortysOn;
    public void addShorty(Shorty who){
        nearbyShorties = addShortyToArray(who, nearbyShorties);
    }
    public void addClimbedShorty(Shorty who){
        shortysOn = addShortyToArray(who, shortysOn);
    }
    public Shorty[] getClimbedShorties(){
        return shortysOn;
    }
    private Shorty[] addShortyToArray(Shorty who, Shorty[] arr){
        if(arr == null || arr.length == 0) {
            arr = new Shorty[]{who};
            return arr;
        }
        int length = arr.length;
        Shorty[] newArray = new Shorty[length + 1];
        System.arraycopy(arr, 0, newArray, 0, length);
        newArray[length] = who;
        return newArray;
    }

    @Override
    public String toString() {
        return "Огромнейший арбуз{" +
                "Рядом коротышек=" + Arrays.toString(nearbyShorties) +
                ", Коротышек уже залезло=" + Arrays.toString(shortysOn) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Melon melon = (Melon) o;
        return Arrays.equals(nearbyShorties, melon.nearbyShorties) && Arrays.equals(shortysOn, melon.shortysOn);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(nearbyShorties);
        result = 31 * result + Arrays.hashCode(shortysOn);
        return result;
    }
}
