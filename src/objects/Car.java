package objects;

import java.util.Arrays;
import java.util.Objects;

import people.Human;
import exceptions.MissingHumanException;
import place.Place;

public class Car {
    private Human[] passengers;
    private Place location;

    public Car(Place location) {
        this.location = location;
    }

    public void addPassenger(Human who){
            passengers = Human.addHumanToStaticArray(who, passengers);
    }
    public Human[] getPassengers(){
        return passengers;
    }
    public void removePassenger(Human who) throws MissingHumanException {
        int indx = -1;
        for(int i = 0; i < passengers.length; i++)
            if(passengers[i].equals(who))
                indx = i;
        if(indx > -1) {
            Human buff = passengers[indx];
            passengers[indx] = null;
        }else
            throw new MissingHumanException(who + " не находится в " + this);

    }
    public void driveTo(Place dest) throws MissingHumanException {
        if(passengers == null || passengers.length == 0 || Arrays.stream(passengers).allMatch(Objects::isNull))
            throw new MissingHumanException(this + " не имеет водителя");
        this.location = dest;
        for(int i = 0; i < passengers.length; i++){
            if(passengers[i] != null)
                passengers[i].walkTo(dest);
        }
    }
    public Place getLocation(){
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Arrays.equals(passengers, car.passengers) && Objects.equals(location, car.location);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(location);
        result = 31 * result + Arrays.hashCode(passengers);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "passengers=" + Arrays.toString(passengers) +
                ", location=" + location +
                '}';
    }
}
