package editor.domain;

import java.util.ArrayList;

public class Canvas extends Drawable implements ISerializable {

    private ArrayList<Element> elements;

    public Canvas() {
        super();

        elements = new ArrayList<>();

        this.pos_x = 75; // Width of the Toolbar
        this.pos_y = 75; // Height of the TopMenu

        this.width = 800 - this.pos_x; // Resizable
        this.height = 600 - this.pos_y; // Resizable

        Notify();
    }

    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

}