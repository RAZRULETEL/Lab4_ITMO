package storages;

public abstract class Feature {
    private String name = "Свойства";
    Feature(String name){
        this.name = name;
    }
    String getName() {return name;}
}
