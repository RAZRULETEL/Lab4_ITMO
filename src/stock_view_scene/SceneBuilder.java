package stock_view_scene;

import finances.Stock;

public class SceneBuilder {
    private Stock stock;

    public SceneBuilder(Stock stock) {
        this.stock = stock;
    }

    public void buildFirstSide(){
        Melon m = new Melon();
        Shorty[] cl = new Shorty[5];
        for (int i = 0; i < cl.length; i++) {
            cl[i] = new Shorty(3);
        }
        for(int i = 0; i < 20; i++) {
            m.addShorty(new Shorty(3));
            cl[i%5].tryClimbMelon(m);
        }
        System.out.println(m);
        Cucumber cuc = new Cucumber(1);
        for(int i = 0; i < 10; i++)
            cuc.mature();
        cuc.compareSizeWith(new Shorty(3));
        Background back = new Background(new String[]{"виднелись крошечные деревенские домики","словно строевой лес, возвышались колосья гигантской земной пшеницы"});
        System.out.println(back.getBackground());
    }
    public void buildSecondSide(){
        Image img = new Image(new String[]{"космическая ракета","Незнайка в космическом скафандре"});
        System.out.println("На обратной стороне акции изображение: "+img.getImageDescription());
        System.out.println("Тут же было напечатано сообщение о целях, для которых учреждалось акционерное общество");
        System.out.println(stock.getName()+" -- путь к богатству и процветанию. Цена "+stock.getPrice()+" фертинг");
    }
}
