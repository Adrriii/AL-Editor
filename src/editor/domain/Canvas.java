package editor.domain;

import java.util.ArrayList;

import editor.domain.element.Rectangle;

public class Canvas extends Drawable implements ISerializable {

    private ArrayList<Element> elements;

    public Canvas() {
        super();

        elements = new ArrayList<>();

        this.pos_x = 75; // Width of the Toolbar
        this.pos_y = 75; // Height of the TopMenu

        this.width = 800 - this.pos_x; // Resizable
        this.height = 600 - this.pos_y; // Resizable

        elements.add(new Rectangle(130,160));
    }

    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

    public void newElement(Element element, int pos_x, int pos_y) {
        Element newElement = element.Clone();
        newElement.pos_x = pos_x;
        newElement.pos_y = pos_y;
        elements.add(newElement);
        Notify();
    }

}