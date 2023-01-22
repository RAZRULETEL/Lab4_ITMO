package stock_view_scene;

import java.util.Arrays;

public class Image {
    private String[] objects;

    public Image(String[] objects) {
        this.objects = objects;
    }

    public String getImageDescription() {
        return Arrays.toString(objects);
    }
    public void setImageDescription(String[] desc) {
        objects = desc;
    }
}
