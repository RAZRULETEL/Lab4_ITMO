package objects;

import java.util.Arrays;
import java.util.Objects;

public class Clothes {
    private ClothesItem[] itemsOfClothes;
    private Boots boots;

    public Clothes() {}

    public DurabilityStage getAverageDurability(){
        if(itemsOfClothes != null && itemsOfClothes.length > 0){
            int avg = 0;
            for(int i = 0; i < itemsOfClothes.length; i++)
                avg += itemsOfClothes[i].getDurability().getIntStage();
            if(boots != null)
                avg += boots.getDurability().getIntStage();
            avg = avg/itemsOfClothes.length;
            return DurabilityStage.getFromInt(avg);
        }
        return null;
    }

    public int getAverageAge(){
        if(itemsOfClothes != null && itemsOfClothes.length > 0){
            int avg = 0;
            for(int i = 0; i < itemsOfClothes.length; i++)
                avg += itemsOfClothes[i].getAge();
            avg = avg/itemsOfClothes.length;
            return avg;
        }
        return 0;
    }

    public ClothesItem[] getAllClothes(){
        return itemsOfClothes;
    }

    public Boots getBoots(){
        return boots;
    }

    public Clothes setBoots(String name, DurabilityStage dur, boolean isHaveSole){
        boots = new Boots(name, dur, isHaveSole);
        return this;
    }

    public Clothes setBoots(Boots bot){
        boots = bot;
        return this;
    }

    public Clothes addClothes(String name, DurabilityStage durab, int age){
        return addClothes(new ClothesItem(name, durab, age));
    }
    public Clothes addClothes(ClothesItem cl){
        if(itemsOfClothes == null || itemsOfClothes.length == 0) {
            itemsOfClothes = new ClothesItem[]{cl};
            return this;
        }
        int length = this.itemsOfClothes.length;
        ClothesItem[] newArray = new ClothesItem[length + 1];
        System.arraycopy(this.itemsOfClothes, 0, newArray, 0, length);
        newArray[length] = cl;
        this.itemsOfClothes = newArray;
        return this;
    }



    public static class ClothesItem {
        private String name;
        private DurabilityStage durability;
        private int age;

        public ClothesItem(String name, DurabilityStage durability, int age) {
            this.name = name;
            this.durability = durability;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public DurabilityStage getDurability() {
            return durability;
        }

        @Override
        public String toString() {
            return "ClothesItem{" +
                    "name='" + name + '\'' +
                    ", износ=" + durability +
                    ", возвраст=" + age +
                    '}';
        }
    }

    public static class Boots {
        private String name;
        private DurabilityStage durability;
        private boolean isHaveSole;

        public Boots(String name, DurabilityStage durability, boolean isHaveSole) {
            this.name = name;
            this.durability = durability;
            this.isHaveSole = isHaveSole;
        }

        public String getName() {
            return name;
        }

        public boolean isHaveSole() {
            return isHaveSole;
        }

        public DurabilityStage getDurability() {
            return durability;
        }

        @Override
        public String toString() {
            return "Boots{" +
                    "name='" + name + '\'' +
                    ", износ=" + durability +
                    ", наличиеѕодошвы=" + isHaveSole +
                    '}';
        }
    }
    public enum DurabilityStage {
        NEW, SLIGHTLY_USED, MEDIUM_USED, MANY_USED, WORN_OUT;
        int getIntStage() {
            return switch(this){
                case NEW -> 4;
                case SLIGHTLY_USED -> 3;
                case MEDIUM_USED -> 2;
                case MANY_USED -> 1;
                case WORN_OUT -> 0;
            };
        }
        static DurabilityStage getFromInt(int st) {
            return switch(st){
                case 4 -> NEW;
                case 3 -> SLIGHTLY_USED;
                case 2 -> MEDIUM_USED;
                case 1 -> MANY_USED;
                case 0 -> WORN_OUT;
                default -> throw new IllegalStateException("Unexpected value: " + st);
            };
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Arrays.equals(itemsOfClothes, clothes.itemsOfClothes) && Objects.equals(boots, clothes.boots);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(boots);
        result = 31 * result + Arrays.hashCode(itemsOfClothes);
        return result;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "itemsOfClothes=" + Arrays.toString(itemsOfClothes) +
                ", boots=" + boots +
                (getAverageAge()>3?", одежонка стара€":"")+
                '}';
    }
}
