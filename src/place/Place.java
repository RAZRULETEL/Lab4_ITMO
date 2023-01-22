package place;

import java.util.Arrays;
import java.util.Objects;

import people.Human;
import exceptions.MissingHumanException;
import people.PeopleGroup;

public class Place {
    private String name;
    private int x;
    private int y;
    private Human[] members;
    public void attractAttentionOf(PeopleGroup com){
        System.out.println(this.name+" привлекала внимание "+com.getName());
    }
    public Place(String name, int x, int y){
        this.name=name;
        this.x=x;
        this.y=y;
    }
    public void addMember(Human memb){
        if(members==null){
            members=new Human[1];
            members[0] = memb;
            return;
        }

        int length = this.members.length;
        Human[] newArray = new Human[length + 1];
        for (int i = 0; i < length; i++) {
            newArray[i] = this.members[i];
        }
        newArray[length] = memb;
        this.members = newArray;
        if(this.members.length > 3)
            System.out.println("Людей около "+this.name+" становилось всё больше");
        if(this.members.length > 7)
            System.out.println("Люди толпились около "+this.name+" от нечего делать");
    }
    public void removeMember(Human memb) throws MissingHumanException {
        int indx = 0;
        if(Arrays.asList(this.members).contains(memb))
        {
            Human[] arr = new Human[this.members.length - 1];
            for (int i = 0; i < this.members.length; i++) {
                if (!memb.equals(this.members[i])) {
                    arr[indx++] = this.members[i];

                }
            }
            this.members = arr;
        }else throw new MissingHumanException(memb + " не находится в "+this);
    }
    public String getName(){
        return this.name;
    }
    public int getDistanceTo(int x, int y){
        return (int)Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return x == place.x && y == place.y && Objects.equals(name, place.name) && Arrays.equals(members, place.members);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, x, y);
        result = 31 * result + Arrays.hashCode(members);
        return result;
    }
}
