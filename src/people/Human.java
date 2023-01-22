package people;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import enums.Instructions;
import event.Event;
import exceptions.MissingHumanException;
import finances.Money;
import exceptions.NegativeBalanceException;
import finances.Stock;
import interfaces.IStorage;
import interfaces.Putable;
import interfaces.Remembable;
import interfaces.Sellable;
import interfaces.Thinkable;
import objects.Car;
import objects.Clothes;
import objects.Phone;
import objects.PhotoCamera;
import place.Bussines;
import place.Place;
import stock_view_scene.SceneBuilder;
import storages.Shawl;
import time.Time;

public class Human {
    private String name;
    private Money ownMoney;
    private Putable[] pocket;
    private Putable itemInHands;
    private Human[] peopleNearby;
    private int worryLvl;
    private Bussines[] works;
    private int daysWorked;
    private Face face;
    private Place[] locationsVisited;
    private Place location;
    private Clothes clothes;
    private Place homeLocation;

    public Human(String name, Money money, Place location, Place homeLocation, Face face, Clothes clothes){
        this.name = name;
        this.ownMoney = money;
        this.location = location;
        this.face = face;
        this.clothes = clothes;
        this.homeLocation = homeLocation;
    }
    public Human(String name, Money money, Place location, Place homeLocation){
        this.name = name;
        this.ownMoney = money;
        this.location = location;
        this.homeLocation = homeLocation;
    }
    public Human(String name, Money money, Place location){
        this.name = name;
        this.ownMoney = money;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void walkTo(Place place){
        System.out.println(name+" пришёл в "+place.getName());
        this.location = place;
        location.addMember(this);
    }

    public void drive(Place drive, Car car){
        if(Arrays.stream(car.getPassengers()).noneMatch(e -> e.equals(this)))
            car.addPassenger(this);
        StringBuilder names = new StringBuilder("");
        for(Human pass : car.getPassengers())
            names.append(pass.getName()).append(" и ");
        System.out.println(names+" сели в "+car+" и поехали в "+drive.getName());
        try {
            car.driveTo(drive);
        } catch (MissingHumanException e) {
            e.printStackTrace();
        }
        location = drive;
    }
    public void putInStorage(Putable what, IStorage closet){
        String out = this.name+" положил "+what+" в "+closet;
        out += closet.toString()+" ";
        out += what;
        System.out.println(out);
        closet.addItem(what);
        this.ownMoney = null;
    }
    public void exchangeMoney(){
        System.out.println(this.name+" объменивал мелкие деньги на крупные");
        int[] bankn = ownMoney.getBanknotes();
        int[] res = new int[0];
        if(bankn != null) {
            int sum = 0;
            for (int j : bankn)
                sum += j;
            int res_length = (sum-sum%100)/100+(sum%100-sum%20)/20+(sum%20)/5;
            res = new int[res_length];
            for(int i = 0; i < res_length; i++){
                res[i] = (sum-sum%100)/100>i?100:(sum-sum%100)/100+(sum%100-sum%20)/20>i?20:5;
            }
        }
        ownMoney.setBanknotes(res);
    }
    public void comeTo(Human who){
        System.out.println(name+" подошёл к "+who.getName());
        addNearbyPeople(who);
        who.addNearbyPeople(this);
    }
    public void addNearbyPeople(Human who){
        if(peopleNearby == null)
            peopleNearby = new Human[0];
        if(Arrays.stream(peopleNearby).noneMatch(e -> e.equals(who)))
            peopleNearby = addHumanToStaticArray(who, peopleNearby);
    }
    public Human[] getNearbyPeople(){
        return peopleNearby;
    }
    public void call(Phone phone, Human who){
        if(location == phone.getLocation())
            System.out.println(this.name+" подошёл к "+phone.getName()+" и "+phone.call(who, location));
        else
            System.out.println(phone.getName() + " находиться слишком далеко");
    }
    private final static String[] greetings = new String[]{"пожал руку","попривествовал"};
    public void greet(Human who){
        System.out.println(name+" "+greetings[new Random().nextInt(greetings.length-1)] + " "+who.getName());
    }

    private final static String[] sending = new String[]{"похлопал по спине и вывел","выпроводил","взял за рукав и вывел"};
    public void sendOut(Human who){
        System.out.println(name+" "+sending[new Random().nextInt(sending.length-1)] + " "+who.getName());
    }
    public void askName(Human who){
        addNearbyPeople(who);
        System.out.println(this.name + " спросил как звать "+who.getName());
    }

    public Putable[] getPocketItems(){return pocket;}
    public void putInPocket(){
        if(pocket == null ||pocket.length == 0) {
           pocket = new Putable[]{itemInHands};
        }
        int length = pocket.length;
        Putable[] newArray = new Putable[length + 1];
        System.arraycopy(pocket, 0, newArray, 0, length);
        newArray[length] = itemInHands;
        pocket = newArray;
        itemInHands = null;
    }
    public void getFromPocket(Putable what){
        if(Arrays.asList(pocket).contains(what)) {
            for (int i = 0; i < pocket.length; i++) {
                if (pocket[i].equals(what)) pocket[i] = null;
            }
            itemInHands = what;
            System.out.println(name+" сунул руку за пазуху и вытащил "+what);
        }
    }

    private final static String[] sayings = new String[]{"прошептал","сказал","подтянул и сказал"};
    public void sayPersonally(Human who){
        addNearbyPeople(who);
        System.out.println(name+" "+sayings[new Random().nextInt(sayings.length-1)] + " "+who.getName());
    }
    public void command(Human who, Instructions whatDo, Human withWho){
        System.out.println(whatDo.getDescription(name, who.getName())+" "+(withWho!=null?withWho.getName():""));
        if(whatDo == Instructions.GIVE_STOCK) {
            getItemToHands((Putable) new Stock("акции общества гигантских растений", 1, 1));
            giveItemFromHands(withWho);
        }
    }
    public void command(Human who, Instructions whatDo) {
        command(who, whatDo, (Human)null);
    }
    public void command(Human who, Instructions whatDo, Putable put, Human withWho) {
        if(put instanceof Money && whatDo == Instructions.COUNT_MONEY)
            who.countMoney((Money) put);
        if(put instanceof Stock && whatDo == Instructions.GIVE_STOCK){
            System.out.println(who.getName()+" выдал "+((Stock)put).getCount() + " "+withWho.getName());
            who.getItemToHands(put);
            who.giveItemFromHands(withWho);
        }
        command(who, whatDo, withWho);
    }
    public void command(Instructions whatDo, Place withWho){
        System.out.println(whatDo.getDescription(name, withWho.getName()));
    }
    public void inspectItemInHands(){
        System.out.print(name+" осмотрел предмет в руках ");
        if(itemInHands instanceof Stock){
            SceneBuilder scene = new SceneBuilder((Stock) itemInHands);
            scene.buildFirstSide();
            scene.buildSecondSide();
        }else{
            System.out.println("это был "+itemInHands);
        }
    }

    public Putable getItemFromHands(){
        return itemInHands;
    }
    public void getItemToHands(Putable item){
        itemInHands = item;
    }
    public void giveItemFromHands(Human who){
        System.out.println(name+" дал "+who.getName()+" в руки "+itemInHands);
        who.getItemToHands(itemInHands);
        itemInHands = null;
    }
    public void makePhoto(){
        if(itemInHands instanceof PhotoCamera)
            System.out.println(name+" сделал снимок на "+itemInHands);
        else
            System.out.println(name+" не может сделать снимок на "+itemInHands);
    }
    public void explain(String what, Human who){
        System.out.println(name + " объяснил "+who.getName()+" "+what);
    }
    public void bendOver(IStorage stor){
        System.out.println(name+" наклонился над "+stor);
    }
    public void countMoney(Money money){
        if(money == null){
            System.out.println(name+" не может пересчитать "+money);
            return;
        }

        System.out.println(name+" начал пересчитывать деньги");
        if(money.getBanknotes().length > 50) {
            if (Arrays.stream(money.getBanknotes()).allMatch(e -> e < 5))
                System.out.println("деньги были мелкие, в монетах по сантику, по два");
            System.out.println("долго считали деньги, долго, но всё-таки закончили, насчитали: "+money.getBalance());
        }else
            System.out.println("денег было: "+money.getBalance());
    }
    public void worry(){
        worryLvl++;
        if(worryLvl > 10)
            System.out.println(name+" волнуется");
        if(worryLvl > 15)
            System.out.println(name+" так разволновался, что расплакался");
    }
    public void ressure(){
        worryLvl--;
        if(worryLvl < -5)
            System.out.println(name+" засыпает в мечтах");
    }
    public void hug(Human who){
        if(Arrays.asList(peopleNearby).contains(who))
            System.out.println(name+" обнял "+who.getName());
        else
            System.out.println(name+" находиться слишком далеко от "+who.getName());
    }
    public void think(Thinkable thinkable){
        System.out.println(thinkable.getThoughts(ownMoney.getBalance()>100000?"богач":(thinkable instanceof Money?((Money) thinkable).getBalance()+"":"")));
    }
    public void remember(Remembable remember){
        System.out.println(remember.getHistory(remember.toString()));
        if(remember instanceof Money && ((Money) remember).getBalance() > 100_000)
            for(int i = 0; i < 10; i++)
                ressure();
    }
    public void dreamAboutWork(Bussines work){
        System.out.println(name+" мечтал поступить на "+work.getName());
    }
    public void workDay(){
        Time.nextDay();
        try {
            ownMoney.changeBy(works[0].getProfit() / (works[0].getWorkers().length + 1));
        }catch (NegativeBalanceException ex){
            ownMoney.changeBy(-ownMoney.getBalance());
        }
    }
    public void checkNews(){
        System.out.println(name+" проверил новости: "+ Event.getEvent());
    }
    public void addWork(Bussines work){
        System.out.println(name+" устроился работать на "+work.getName());
        if(works == null || works.length == 0) {
            works = new Bussines[]{work};
        }
        int length = works.length;
        Bussines[] newArray = new Bussines[length + 1];
        System.arraycopy(works, 0, newArray, 0, length);
        newArray[length] = work;
        works = newArray;
        work.addWorker(this);
    }
    public boolean isLocationVisited(Place loc){
        return Arrays.asList(locationsVisited).contains(loc);
    }
    public Place getHomeLocation(){
        return homeLocation;
    }
    public void wakeUp(){
        if(worryLvl < -2){
            System.out.println(name+" очухался от мыслей");
            worryLvl = 0;
        }
    }

    public void knot(Shawl sh){
        System.out.print(name);
        sh.unKnot();
    }
    public void unKnot(Shawl sh){
        System.out.print(name);
        sh.knot();
    }

    static public Human[] addHumanToStaticArray(Human str, Human[] arr){
        if(arr == null || arr.length == 0) {
            arr = new Human[]{str};
            return arr;
        }
        int length = arr.length;
        Human[] newArray = new Human[length + 1];
        System.arraycopy(arr, 0, newArray, 0, length);
        newArray[length] = str;
        return newArray;
    }

    public Money getOwnMoney() {
        return ownMoney;
    }

    public void setOwnMoney(Money ownMoney) {
        this.ownMoney = ownMoney;
    }

    public static Human getRandomHuman(Place location){
        return new Human("Коротышка "+new Random().nextInt(10000),new Money(new Random().nextInt(200)), location, location, null, null);
    }

    public void tryToBuy(Sellable sell){
        if(ownMoney.getBalance() > sell.getPrice()*1000){
            System.out.println(name+" заинтересовался в "+sell);
            return;
        }
        if(sell.getPrice() > ownMoney.getBalance())
            System.out.println(sell+" слишком дорогой для "+name);
        else
            System.out.println(name+" купил "+sell);
    }
    public void checkSaving(IStorage stor){
        Money money = null;
        for (Putable put: stor.getItemsList())
            if(put instanceof Money)
                money = (Money) put;
        if(money != null)
            System.out.println(name+" проверил сбережения и нашёл "+money.getBalance()+" денег");
        else
            System.out.println(name+" проверил сбережения и ничего не нашёл");
    }
    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                (ownMoney.getBalance()<30?", бедняк":"")+
                ", face=" + face +
                ", location=" + location +
                ", clothes=" + clothes +
                ", homeLocation=" + (homeLocation != null && homeLocation.getDistanceTo(0,0)>50?"деревенский житель":"городской житель") +
                '}';
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }
    public void checkFaceMood(Human who){
        System.out.println(name+" заметил по выражению лица "+who.getName()+", что"+switch(who.getFace().getFaceMood()){
            case NEUTRAL -> " всё хорошо";
            case NEGATIVE -> " неправ";
            case POSITIVE -> " его догадка верна";
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return worryLvl == human.worryLvl && daysWorked == human.daysWorked && Objects.equals(name, human.name) && Objects.equals(ownMoney, human.ownMoney) && Arrays.equals(pocket, human.pocket) && Objects.equals(itemInHands, human.itemInHands) && Arrays.equals(peopleNearby, human.peopleNearby) && Arrays.equals(works, human.works) && Objects.equals(face, human.face) && Arrays.equals(locationsVisited, human.locationsVisited) && Objects.equals(location, human.location) && Objects.equals(clothes, human.clothes) && Objects.equals(homeLocation, human.homeLocation);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, ownMoney, itemInHands, worryLvl, daysWorked, face, location, clothes, homeLocation);
        result = 31 * result + Arrays.hashCode(pocket);
        result = 31 * result + Arrays.hashCode(peopleNearby);
        result = 31 * result + Arrays.hashCode(works);
        result = 31 * result + Arrays.hashCode(locationsVisited);
        return result;
    }
}
