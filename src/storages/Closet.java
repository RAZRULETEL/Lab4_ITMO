package storages;

import java.util.Arrays;
import java.util.Objects;

import interfaces.IStorage;
import interfaces.Putable;

public class Closet implements IStorage {
    private Putable[] storage;
    private String name;
    private Feature[] features;

    public Closet(String name, Feature[] features) {
        this.name = name;
        this.features = features;
    }

    @Override
    public String toString() {
        String out = "";
        String cont = "";
        for(Feature feat : features)
            out += feat.toString()+" ";
        if(storage != null)
        for(Putable put : storage)
            out += put.toString()+" ";
        return out + " "+name+" "+cont;
    }
    Feature[] getFeatures() {return this.features;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Closet closet = (Closet) o;
        return Arrays.equals(storage, closet.storage) && Objects.equals(name, closet.name) && Arrays.equals(features, closet.features);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(storage);
        result = 31 * result + Arrays.hashCode(features);
        return result;
    }

    @Override
    public void addItem(Putable item) {
        if(storage == null || storage.length == 0) {
            storage = new Putable[]{item};
            return;
        }
        int length = this.storage.length;
        Putable[] newArray = new Putable[length + 1];
        System.arraycopy(this.storage, 0, newArray, 0, length);
        newArray[length] = item;
        this.storage = newArray;
    }

    @Override
    public Putable[] getItemsList() {
        return storage;
    }

    @Override
    public Putable getItem(Putable item) {
        int indx = -1;
        for(int i = 0; i < storage.length; i++)
            if(storage[i].equals(item))
                indx = i;
        if(indx > -1) {
            Putable buff = storage[indx];
            storage[indx] = null;
            return buff;
        }
        return null;
    }

    public Putable getItem(int item) {
        if(item < 0 || item > storage.length)
            return null;
        else
            return storage[item];
    }
}
