package stock_view_scene;

import java.util.Arrays;

public class Background {
    private String[] objects;

    public Background(String[] objects) {
        this.objects = objects;
    }
    public String getBackground() {
        return Arrays.toString(objects);
    }
}
