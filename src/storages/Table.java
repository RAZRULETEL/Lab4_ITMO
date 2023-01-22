package storages;

import java.util.Arrays;

import interfaces.IStorage;
import interfaces.Putable;
import place.Place;

public class Table  implements IStorage {
    private Putable[] storage;
    private String name;
    private Place location;

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

    public void setLocation(Place location) {
        this.location = location;
    }

    public Table(String name, Place location) {
        this.name = name;
        this.location = location;
        storage = new Putable[0];
    }

    public Place getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public Putable[] getItemsList() {
        return storage;
    }

    @Override
    public Putable getItem(Putable item) {
        int indx = -1;
        if(storage == null)
            storage = new Putable[0];
        for(int i = 0; i < storage.length; i++)
            if(storage[i] != null && storage[i].equals(item))
                indx = i;
        if(indx > -1) {
            Putable buff = storage[indx];
            storage[indx] = null;
            return buff;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Table{" +
                "storage=" + Arrays.toString(storage) +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
