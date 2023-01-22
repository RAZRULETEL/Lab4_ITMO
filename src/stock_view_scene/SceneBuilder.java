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
        Background back = new Background(new String[]{"��������� ��������� ����������� ������","������ �������� ���, ����������� ������� ���������� ������ �������"});
        System.out.println(back.getBackground());
    }
    public void buildSecondSide(){
        Image img = new Image(new String[]{"����������� ������","�������� � ����������� ���������"});
        System.out.println("�� �������� ������� ����� �����������: "+img.getImageDescription());
        System.out.println("��� �� ���� ���������� ��������� � �����, ��� ������� ����������� ����������� ��������");
        System.out.println(stock.getName()+" -- ���� � ��������� � �����������. ���� "+stock.getPrice()+" �������");
    }
}
