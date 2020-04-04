package editor.domain;

import java.util.ArrayList;

public class Canvas extends Drawable implements ISerializable {

    private ArrayList<Element> elements;

    public Canvas() {
        super();

        elements = new ArrayList<>();
    }

    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

}