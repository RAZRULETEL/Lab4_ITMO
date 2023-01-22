package storages;

import java.util.Objects;

import interfaces.IStorage;
import interfaces.Putable;

public class Shawl implements IStorage, Putable {
    private Putable storage;
    private String name;
    private IStorage location;
    private boolean isKnnoted;

    public Shawl(String name, boolean isKnnoted) {
        this.name = name;
        this.isKnnoted = isKnnoted;
    }

    @Override
    public void addItem(Putable item) {
        if(!isKnnoted)
            storage = item;
    }

    @Override
    public Putable[] getItemsList() {
        return new Putable[]{storage};
    }

    @Override
    public Putable getItem(Putable item) {
        if(!isKnnoted) {
            storage = null;
            return item;
        }
        return null;
    }

    @Override
    public void moveTo(IStorage newPlace) {
        this.location = newPlace;
    }
    public Shawl knot(){
        isKnnoted = true;
        System.out.println(" развязал платок");
        return this;
    }
    public Shawl unKnot(){
        isKnnoted = false;
        System.out.println(" завязал платок");
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shawl shawl = (Shawl) o;
        return isKnnoted == shawl.isKnnoted && Objects.equals(storage, shawl.storage) && Objects.equals(name, shawl.name) && Objects.equals(location, shawl.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage, name, location, isKnnoted);
    }

    @Override
    public String toString() {
        return "Shawl{" +
                "storage=" + storage +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", isKnnoted=" + isKnnoted +
                '}';
    }
}
