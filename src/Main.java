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
        Bussines kontora = new Bussines("�������", 0, 0, 5000);
        Place bank = new Place("����", 0, 1);
        Phone phone = new Phone("�������", kontora);
        Human pos = new Human("����������", new Money(100), kontora, null);

        Human miga = new Human("����", new Money(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5}), bank);
        Human zhulio = new Human("�����", new Money(1000), bank, kontora);
        Human kozklik = new Human("������", new Money(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5}), kontora);
        Human neznayka = new Human("��������", new Money(100), kontora);



        Shawl platok = new Shawl("������� ������", false);
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
        Human photo = new Human("��������", new Money(100), null);
        photo.getItemToHands(new PhotoCamera("����������"));
        miga.call(phone, photo);
        pos.unKnot(platok);
        Table tabl = new Table("����", kontora);
        pos.putInStorage(platok.getItem(new Money(100)), tabl);
        zhulio.command(neznayka, Instructions.COUNT_MONEY);
        zhulio.command(kozklik, Instructions.COUNT_MONEY);
        zhulio.command(neznayka, Instructions.GIVE_STOCK, pos);
        pos.inspectItemInHands();
        miga.sayPersonally(zhulio);
        zhulio.command(miga, Instructions.GIVE_STOCK,new Stock("����� �������� ���������� ��������", 5, 10) , pos);
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
        Place ad_master = new Place("��������� ����������", 1, 1);
        //car.addPassenger(miga);
        //zhulio.drive(ad_master, car);
        Human arter = new Human("��������", new Money(100), ad_master);
        zhulio.explain("��� � ����� ������� ���� ����������", arter);
        try {
            car.driveTo(kontora);
        } catch (MissingHumanException e) {

            car.addPassenger(new Human("��������", new Money(100), car.getLocation()));
            try {
                car.driveTo(kontora);
            } catch (MissingHumanException ex) {
                ex.printStackTrace();
            }
        }
        Time.nextDayTime();
        System.out.println("� "+kontora+" ��� ���� "+kontora.getClientsCount()+" ��������");
        Face suned = new Face( "�����������, ����������", Face.FacialExpression.NEUTRAL);
        Clothes noSole = new Clothes().setBoots("�����", Clothes.DurabilityStage.WORN_OUT, false).addClothes("��������", Clothes.DurabilityStage.MANY_USED, 5);
        Place village = new Place("�������", 100, -200);
        Human village_liver = new Human("������", new Money(100).changeBy(10), kontora, village, suned, null);
        System.out.println(village_liver);
        village_liver.putInStorage(village_liver.getOwnMoney(), tabl);
        neznayka.bendOver(tabl);
        kozklik.bendOver(tabl);
        neznayka.countMoney((Money) tabl.getItem(new Money(100).changeBy(10)));
        kozklik.countMoney((Money) tabl.getItem(new Money(100).changeBy(10)));
        neznayka.getItemToHands(new Stock("����� �������� ���������� ��������",1, 90));
        neznayka.giveItemFromHands(village_liver);
        for(int i = 0; i < 20; i++)
            village_liver.worry();
        miga.sendOut(village_liver);
        miga.hug(neznayka);
        miga.hug(kozklik);
        Time.nextDay();
        Time.nextDayTime();
        System.out.println("�������� ������� �������� "+kontora.getProfit());
        Human korot = new Human("���� �� �����������", new Money(100), kontora, new Place("�������", 100, 200));
        korot.dreamAboutWork(new Bussines("�����", 10,20));
        Bussines fabric = new Bussines("�������", 20,20, 500);
        korot.dreamAboutWork(fabric);
        korot.addWork(fabric);
        for (int i = 0; i< 100 ; i++)
            korot.workDay();
        Bussines field = new Bussines("���������� �������", 100,200, 5000);
        korot.tryToBuy(field);
        korot.getItemToHands(korot.getOwnMoney());
        korot.giveItemFromHands(neznayka);
        korot.getItemToHands(new Stock("����� �������� ���������� ��������", 1, korot.getOwnMoney().getBalance()));



        PeopleGroup all = new PeopleGroup("��������� ������");

        Closet fireProofCloset = new Closet("����",new Feature[]{new FireResistance("�����������", 1)});
        PeopleGroup interested = new PeopleGroup("�������� ������ �����");
        for (int i = 0; i < 5; i++)
            interested.walkTo(kontora);
        Stock big_plants_stocks = new Stock("����� �������� ���������� ��������", 5, 1);
        PeopleGroup nezn_and_kozl = new PeopleGroup(new Human[]{neznayka, kozklik});
        PeopleGroup buyers = new PeopleGroup("����������");
        nezn_and_kozl.sellFromTo(big_plants_stocks, TimeOfDay.MORNING, TimeOfDay.EVENING, buyers);
        //miga.drive(bank);
        miga.exchangeMoney();
        miga.putInStorage(miga.getOwnMoney(), fireProofCloset);
        Stock oil_comm = new Stock("����� ��������� ����������", 1, 1);
        for (int i = 0; i < 10; i++)
            buyers.walkToInTime(kontora, TimeOfDay.NIGTH);
        all.think(big_plants_stocks);
        all.remember(oil_comm);
        PeopleGroup sellers = new PeopleGroup("������ ���������");
        sellers.remember(oil_comm);


        Closet saving = new Closet("����",new Feature[]{});
        saving.addItem(new Money(1000));
        Human saver = new Human("���-��", new Money(100), kontora);
        saver.checkSaving(saving);
        saver.walkTo(kontora);
        Closet chest = new Closet("������",new Feature[]{new FireResistance("�����������", 1)});
        chest.addItem(new Stock("����� �������� ���������� ��������", 1, 20000000));
        kontora.sell((Stock)chest.getItem(0));
        if(kontora.getProfit()> 1000)
            System.out.println("� �� ����� ������ �������");
        Human bogach = new Human("�����", new Money(1000000), null);
        bogach.think(new Stock("����� �������� ���������� ��������", 1, 1));
        Place graben = new Place("����������", 1,1);
        Human spruts = new Human("������", new Money(1_000_000),graben);
        spruts.tryToBuy(new Stock("����� �������� ���������� ��������", 1, 1));
        bogach.think(spruts.getOwnMoney());
        Bussines spruts_manu = new Bussines("������������ �����������", 30,40,100000);
        Bussines spruts_suga = new Bussines("30 �������� �������", 31,40,100000);
        Bussines spruts_lati = new Bussines("��������� ����������", 32,40,100000);
        spruts_lati.setProductDescription("�� ���� ���� ��������� �������� �������� ������ ���������, ������� ���������� ������ ��� ������������ �����������, �������� ������ ��� ��� �������� �������, � ����� �������� ���������� ������ ��� � �������, �������� �������� ������ ��� ������� ��������");
        for (int i = 0; i < 400; i++){
            Human random = Human.getRandomHuman(null);
            random.addWork(spruts_manu);
            random.addWork(spruts_lati);
            random.addWork(spruts_suga);
        }
        spruts.addWork(spruts_lati);
        spruts.addWork(spruts_suga);
        spruts.addWork(spruts_manu);
        Human krabs = new Human("�����", new Money(1000), null);
        spruts.command(krabs, Instructions.COME);
        spruts.sendOut(krabs);
        Time.nextDayTime();
        krabs.walkTo(kontora);
        krabs.sayPersonally(miga);
        krabs.sayPersonally(zhulio);
        krabs.command(zhulio, Instructions.OFFER_DINNER, miga);
        miga.walkTo(graben);
        Place rest = new Place("��������", 10,30);
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
