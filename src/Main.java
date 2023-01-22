import enums.Instructions;
import enums.TimeOfDay;
import finances.Money;
import exceptions.NegativeBalanceException;
import finances.Stock;
import objects.Car;
import objects.Clothes;
import objects.Phone;
import objects.PhotoCamera;
import people.Face;
import people.Human;
import exceptions.MissingHumanException;
import people.PeopleGroup;
import place.Bussines;
import place.Place;
import storages.Closet;
import storages.Feature;
import storages.FireResistance;
import storages.Shawl;
import storages.Table;
import time.Time;

public class Main {
    public static void main(String[] args){
        Money m = new Money(100);
        int b=  -200;
        try {
            m.changeBy(b);
        }catch (NegativeBalanceException ex){
            m.changeBy(-m.getBalance());
        }
        Bussines kontora = new Bussines("Контора", 0, 0, 5000);
        Place bank = new Place("Банк", 0, 1);
        Phone phone = new Phone("Телефон", kontora);
        Human pos = new Human("Посетитель", new Money(100), kontora, null);

        Human miga = new Human("Мига", new Money(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5}), bank);
        Human zhulio = new Human("Жулио", new Money(1000), bank, kontora);
        Human kozklik = new Human("Козлик", new Money(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5}), kontora);
        Human neznayka = new Human("Незнайка", new Money(100), kontora);



        Shawl platok = new Shawl("носовой платок", false);
        platok.addItem(new Money(100).changeBy(10));
        platok.knot();
        pos.getItemToHands(platok);
        pos.putInPocket();




        pos.getFromPocket(platok);
        miga.walkTo(kontora);
        zhulio.walkTo(kontora);
        kozklik.sayPersonally(miga);
        miga.comeTo(pos);
        miga.greet(pos);
        miga.askName(pos);
        Human photo = new Human("Фотограф", new Money(100), null);
        photo.getItemToHands(new PhotoCamera("Фотокамера"));
        miga.call(phone, photo);
        pos.unKnot(platok);
        Table tabl = new Table("стол", kontora);
        pos.putInStorage(platok.getItem(new Money(100)), tabl);
        zhulio.command(neznayka, Instructions.COUNT_MONEY);
        zhulio.command(kozklik, Instructions.COUNT_MONEY);
        zhulio.command(neznayka, Instructions.GIVE_STOCK, pos);
        pos.inspectItemInHands();
        miga.sayPersonally(zhulio);
        zhulio.command(miga, Instructions.GIVE_STOCK,new Stock("акции Общества гигантских растений", 5, 10) , pos);
        pos.putInStorage(pos.getItemFromHands(), platok);
        platok.knot();
        pos.getItemToHands(platok);
        pos.putInPocket();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        photo.command(pos, Instructions.SIT_IN_CHAIR);
        photo.makePhoto();
        miga.sendOut(pos);
        Car car = new Car(kontora);
        Place ad_master = new Place("рекламная мастерская", 1, 1);
        //car.addPassenger(miga);
        //zhulio.drive(ad_master, car);
        Human arter = new Human("художник", new Money(100), ad_master);
        zhulio.explain("где и какие плакаты надо установить", arter);
        try {
            car.driveTo(kontora);
        } catch (MissingHumanException e) {

            car.addPassenger(new Human("Водитель", new Money(100), car.getLocation()));
            try {
                car.driveTo(kontora);
            } catch (MissingHumanException ex) {
                ex.printStackTrace();
            }
        }
        Time.nextDayTime();
        System.out.println("В "+kontora+" уже было "+kontora.getClientsCount()+" клиентов");
        Face suned = new Face( "обветренное, загореллое", Face.FacialExpression.NEUTRAL);
        Clothes noSole = new Clothes().setBoots("лапти", Clothes.DurabilityStage.WORN_OUT, false).addClothes("одежонка", Clothes.DurabilityStage.MANY_USED, 5);
        Place village = new Place("деревня", 100, -200);
        Human village_liver = new Human("житель", new Money(100).changeBy(10), kontora, village, suned, null);
        System.out.println(village_liver);
        village_liver.putInStorage(village_liver.getOwnMoney(), tabl);
        neznayka.bendOver(tabl);
        kozklik.bendOver(tabl);
        neznayka.countMoney((Money) tabl.getItem(new Money(100).changeBy(10)));
        kozklik.countMoney((Money) tabl.getItem(new Money(100).changeBy(10)));
        neznayka.getItemToHands(new Stock("акции Общества гигантских растений",1, 90));
        neznayka.giveItemFromHands(village_liver);
        for(int i = 0; i < 20; i++)
            village_liver.worry();
        miga.sendOut(village_liver);
        miga.hug(neznayka);
        miga.hug(kozklik);
        Time.nextDay();
        Time.nextDayTime();
        System.out.println("Торговля акциями принесла "+kontora.getProfit());
        Human korot = new Human("один из покупателей", new Money(100), kontora, new Place("деревня", 100, 200));
        korot.dreamAboutWork(new Bussines("завод", 10,20));
        Bussines fabric = new Bussines("фабрика", 20,20, 500);
        korot.dreamAboutWork(fabric);
        korot.addWork(fabric);
        for (int i = 0; i< 100 ; i++)
            korot.workDay();
        Bussines field = new Bussines("нормальный участок", 100,200, 5000);
        korot.tryToBuy(field);
        korot.getItemToHands(korot.getOwnMoney());
        korot.giveItemFromHands(neznayka);
        korot.getItemToHands(new Stock("акции Общества гигантских растений", 1, korot.getOwnMoney().getBalance()));



        PeopleGroup all = new PeopleGroup("городские жители");

        Closet fireProofCloset = new Closet("шкаф",new Feature[]{new FireResistance("огнеупорный", 1)});
        PeopleGroup interested = new PeopleGroup("Желающие купить акции");
        for (int i = 0; i < 5; i++)
            interested.walkTo(kontora);
        Stock big_plants_stocks = new Stock("акции Общества гигантских растений", 5, 1);
        PeopleGroup nezn_and_kozl = new PeopleGroup(new Human[]{neznayka, kozklik});
        PeopleGroup buyers = new PeopleGroup("покупатели");
        nezn_and_kozl.sellFromTo(big_plants_stocks, TimeOfDay.MORNING, TimeOfDay.EVENING, buyers);
        //miga.drive(bank);
        miga.exchangeMoney();
        miga.putInStorage(miga.getOwnMoney(), fireProofCloset);
        Stock oil_comm = new Stock("акции нефтяного сообщества", 1, 1);
        for (int i = 0; i < 10; i++)
            buyers.walkToInTime(kontora, TimeOfDay.NIGTH);
        all.think(big_plants_stocks);
        all.remember(oil_comm);
        PeopleGroup sellers = new PeopleGroup("Каждый продавший");
        sellers.remember(oil_comm);


        Closet saving = new Closet("Шкаф",new Feature[]{});
        saving.addItem(new Money(1000));
        Human saver = new Human("кто-то", new Money(100), kontora);
        saver.checkSaving(saving);
        saver.walkTo(kontora);
        Closet chest = new Closet("Сундук",new Feature[]{new FireResistance("огнеупорный", 1)});
        chest.addItem(new Stock("акции Общества гигантских растений", 1, 20000000));
        kontora.sell((Stock)chest.getItem(0));
        if(kontora.getProfit()> 1000)
            System.out.println("А за акции охотно платили");
        Human bogach = new Human("Богач", new Money(1000000), null);
        bogach.think(new Stock("акции Общества гигантских растений", 1, 1));
        Place graben = new Place("ГРабенберг", 1,1);
        Human spruts = new Human("Спрутс", new Money(1_000_000),graben);
        spruts.tryToBuy(new Stock("акции Общества гигантских растений", 1, 1));
        bogach.think(spruts.getOwnMoney());
        Bussines spruts_manu = new Bussines("Спрутсовской мануфактуры", 30,40,100000);
        Bussines spruts_suga = new Bussines("30 сахарных заводов", 31,40,100000);
        Bussines spruts_lati = new Bussines("Несколько латифундий", 32,40,100000);
        spruts_lati.setProductDescription("На всех этих земельных участках работали тысячи коротышек, которые выращивали хлопок для Спрутсовской мануфактуры, сахарную свеклу для его сахарных заводов, а также огромные количества лунной ржи и пшеницы, которыми господин Спрутс вел большую торговлю");
        for (int i = 0; i < 400; i++){
            Human random = Human.getRandomHuman(null);
            random.addWork(spruts_manu);
            random.addWork(spruts_lati);
            random.addWork(spruts_suga);
        }
        spruts.addWork(spruts_lati);
        spruts.addWork(spruts_suga);
        spruts.addWork(spruts_manu);
        Human krabs = new Human("Крабс", new Money(1000), null);
        spruts.command(krabs, Instructions.COME);
        spruts.sendOut(krabs);
        Time.nextDayTime();
        krabs.walkTo(kontora);
        krabs.sayPersonally(miga);
        krabs.sayPersonally(zhulio);
        krabs.command(zhulio, Instructions.OFFER_DINNER, miga);
        miga.walkTo(graben);
        Place rest = new Place("ресторан", 10,30);
        miga.walkTo(rest);
        zhulio.walkTo(rest);
        krabs.command(krabs, Instructions.START_DIALOGUE);
        krabs.command(spruts, Instructions.PRAISE_HUMAN);
        krabs.command(Instructions.PRAISE_CITY, graben);
        krabs.remember(spruts.getOwnMoney());
        krabs.wakeUp();
        krabs.setFace(new Face(null, Face.FacialExpression.NEGATIVE));

Clothes.Boots bot = new Clothes.Boots("", Clothes.DurabilityStage.MANY_USED, false);




    }
}
