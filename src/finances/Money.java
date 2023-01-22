package finances;

import java.util.Arrays;
import java.util.Objects;

import exceptions.NegativeBalanceException;
import interfaces.IStorage;
import interfaces.Putable;

public class Money extends Finances implements Putable {
    private int amount;
    private int[] banknotes;

    public Money(int amount){
        this.amount=amount;
        this.banknotes = new int[(amount-amount%5)/5];
        for(int i = 0; i < this.banknotes.length; i++)
            banknotes[i] = 5;
    }
    public Money(int[] banknotes){
        this.banknotes=banknotes;
        this.amount = Arrays.stream(banknotes).sum();
    }
    public int[] getBanknotes(){return banknotes;}
    public void setBanknotes(int[] banknotes){if(banknotes != null && banknotes.length > 0 && Arrays.stream(banknotes).allMatch(e -> e==5||e==20||e==100)){
        this.banknotes=banknotes;
        this.amount = Arrays.stream(banknotes).sum();
        }
    }
    public Money changeBy(int lost){
        if(this.amount + lost < 0)
            throw new NegativeBalanceException("Баланс не может быть отрицательным");

        this.amount += lost;
        lost = this.amount;
        int[] newBanknotes = new int[(lost-lost%100)/100+lost%100+1];
        int i = 0;
        while (lost >= 0) {
            if(lost >= 100){
                newBanknotes[i] = 100;
                lost -= 100;
            }else{
                newBanknotes[i] = 1;
                lost -= 1;
            }
            i++;
        }
        this.banknotes = newBanknotes;
        return this;
    }
    public int getBalance(){return this.amount;}

    @Override
    public String getHistory(String name) {
        if(name.contains("1000000") && name.contains("Money"))
            System.out.println("Воспоминание о богатстве, которым владел господин Спрутс, заставило расплыться в широчайшей улыбке пухлые, румяные щеки господина Крабса, а его несколько выпученные, блестящие глазки сами собой зажмурились.");

        return " не нашлось историй про деньги";
    }

    @Override
    public String getThoughts(String name) {
        if(name.matches("[0-9]+"))
            System.out.println("Как любят говорить городские он стоит "+name);

        return " думали как заработать денег";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && Arrays.equals(banknotes, money.banknotes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(amount);
        result = 31 * result + Arrays.hashCode(banknotes);
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", banknotes=" + Arrays.toString(banknotes) +
                '}';
    }

    @Override
    public void moveTo(IStorage newPlace) {

    }
}
