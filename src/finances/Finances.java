package finances;

import interfaces.Remembable;
import interfaces.Thinkable;
import people.Human;

public abstract class Finances implements Thinkable, Remembable {
    private Human owner;

    public Human getOwner() {
        return owner;
    }
}
