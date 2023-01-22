package objects;

import java.util.Objects;

import interfaces.IStorage;
import interfaces.Putable;

public class PhotoCamera implements Putable {
    private String name;
    private IStorage location;

    public PhotoCamera(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PhotoCamera{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoCamera that = (PhotoCamera) o;
        return name.equals(that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public void moveTo(IStorage newPlace) {
        location = newPlace;
    }
}
