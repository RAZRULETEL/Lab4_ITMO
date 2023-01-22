package place;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import enums.TimeOfDay;
import finances.Stock;
import interfaces.Sellable;
import people.Human;
import exceptions.MissingHumanException;
import time.Time;

public class Bussines extends Place implements Sellable {

    public Bussines(String name, int x, int y) {
        super(name, x, y);
    }
        private int price;
        private int profit;
        private Human[] clients;
        private String name;
        private int lastDayCheckProfit;
        private TimeOfDay lastTimeCheckClients;
        private String productDescription;
        private Human[] workers = new Human[0];

    public Bussines(String name, int x, int y, int price) {
        super(name, x, y);
        this.price = price;
        this.name = name;
        lastDayCheckProfit = Time.getDay();
        lastTimeCheckClients = Time.getTime();
    }

    public int getPrice() {
        return price + profit;
    }

    public int getProfit() {
        class Profit{
            int daysGone = 0;
            int bisPrice = 0;
            int oldProfit = 0;

            public Profit(int days, int price, int oldProfit) {
                this.daysGone = days;
                this.bisPrice = price;
                this.oldProfit = oldProfit;
                lastDayCheckProfit = Time.getDay();
            }

            int calculate(){
                return oldProfit + new Random().nextInt(-bisPrice/30, bisPrice/10)*daysGone;
            }
        }
        Profit prof = new Profit(Time.getDay() - lastDayCheckProfit, price, profit);
        return prof.calculate();
    }
    public void sell(Sellable sell){
        int time = sell.getPrice()/(workers.length+1)/(sell instanceof Stock?100:1);
        if(time < 10)
            System.out.println(sell+" было быстро продано");
        else
            System.out.println(sell+" продавалось не слишком быстро");
    }
        public Human[] getClients() {
            int newClientsCount = (new Random().nextInt(workers.length*5+1)+1)/(lastTimeCheckClients==Time.getTime()?100:1);
            for(int i = 0; i < newClientsCount; i++){
                clients = Human.addHumanToStaticArray(Human.getRandomHuman(this), clients);
            }
            return clients;
        }

        public int getClientsCount() {
            clients = getClients();
            return clients.length;
        }

        public String getName() {
            return name;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public void addWorker(Human who) {
            workers = Human.addHumanToStaticArray(who, workers);
        }

        public Human[] getWorkers() {
            return workers;
        }

        public Human removeWorker(Human who) throws MissingHumanException {
            int indx = -1;
            for(int i = 0; i < workers.length; i++)
                if(workers[i].equals(who))
                    indx = i;
            if(indx > -1) {
                Human buff = workers[indx];
                workers[indx] = null;
                return buff;
            }else
                throw new MissingHumanException(who + " не работает в " + name);
        }

    @Override
    public String toString() {
        return "Bussines{" +
                "price=" + price +
                ", profit=" + profit +
                //", clients=" + Arrays.toString(clients) +
                ", name='" + name + '\'' +
                ", lastDayCheckProfit=" + lastDayCheckProfit +
                ", lastTimeCheckClients=" + lastTimeCheckClients +
                ", productDescription='" + productDescription + '\'' +
                ", workers=" + Arrays.toString(workers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bussines bussines = (Bussines) o;
        return price == bussines.price && profit == bussines.profit && lastDayCheckProfit == bussines.lastDayCheckProfit && Arrays.equals(clients, bussines.clients) && Objects.equals(name, bussines.name) && lastTimeCheckClients == bussines.lastTimeCheckClients && Objects.equals(productDescription, bussines.productDescription) && Arrays.equals(workers, bussines.workers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), price, profit, name, lastDayCheckProfit, lastTimeCheckClients, productDescription);
        result = 31 * result + Arrays.hashCode(clients);
        result = 31 * result + Arrays.hashCode(workers);
        return result;
    }
}

