package objects;

import java.util.Objects;

import people.Human;
import place.Place;

public class Phone {
    private String name;
    private Place location;


    public Phone(String name, Place location) {
        this.name = name;
        this.location = location;
    }

    public String call(Human who, Place where){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("явилс€ "+who.getName());
                who.walkTo(where);
            }
        }).run();
        return "вызвал "+who.getName();
    }
    public Place getLocation(){
        return location;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return name.equals(phone.name) && location.equals(phone.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }




}

