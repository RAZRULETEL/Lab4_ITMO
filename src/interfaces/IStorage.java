package interfaces;

public interface IStorage {
    void addItem(Putable item);
    Putable[] getItemsList();
    Putable getItem(Putable item);
}
