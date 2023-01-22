package people;


import java.util.Arrays;
import java.util.Objects;

public class Face{
    private FaceElement[] elems;
    private String features;
    private FacialExpression faceMood;

    public Face(String features, FacialExpression faceMood) {
        this.features = features;
        this.faceMood = faceMood;
    }

    public String[] getElements(){
        String[] out = new String[elems.length];
        for(int i = 0; i < elems.length; i++)
            out[i] = elems[i].toString();
        return out;
    }
    public String getFaceFeatures(){
        return features;
    }
    public String getFullFaceDescription(){
        String out = "";
        for(int i = 0; i < elems.length; i++)
            out += elems[i];
        return "Лицо " + features + ", " + out;
    }

    public void addClothes(String name, String desc){
        if(elems == null || elems.length == 0) {
            elems = new Face.FaceElement[]{new Face.FaceElement(name, desc)};
            //return this;
        }
        int length = this.elems.length;
        Face.FaceElement[] newArray = new Face.FaceElement[length + 1];
        System.arraycopy(this.elems, 0, newArray, 0, length);
        newArray[length] = new Face.FaceElement(name, desc);
        this.elems = newArray;
        //return this;
    }

    public FacialExpression getFaceMood() {
        return faceMood;
    }

    public class FaceElement{
        private String elementName, description;

        public FaceElement(String elementName, String description) {
            this.elementName = elementName;
            this.description = description;
        }

        public String getName() {
            return elementName;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "FaceElement{" +
                    "elementName='" + elementName + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
    public enum FacialExpression{
        POSITIVE, NEGATIVE, NEUTRAL
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Face face = (Face) o;
        return Arrays.equals(elems, face.elems) && Objects.equals(features, face.features) && faceMood == face.faceMood;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(features, faceMood);
        result = 31 * result + Arrays.hashCode(elems);
        return result;
    }

    @Override
    public String toString() {
        return "Face{" +
                "elems=" + Arrays.toString(elems) +
                ", features='" + features + '\'' +
                ", faceMood=" + faceMood +
                '}';
    }
}