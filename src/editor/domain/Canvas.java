package editor.domain;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.element.Rectangle;

public class Canvas extends Drawable implements ISerializable {

    private ArrayList<Element> elements;

    private Rectangle background;

    public Canvas() {
        super();

        Attach(App.view.getCanvasView());

        elements = new ArrayList<>();

        this.pos_x = 75; // Width of the Toolbar
        this.pos_y = 75; // Height of the TopMenu

        this.width = 800 - this.pos_x; // Resizable
        this.height = 600 - this.pos_y; // Resizable

        this.background = new Rectangle(0, 0, width, height, 255, 255, 255, 255);
    }

    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

    public void newElement(Element element, int pos_x, int pos_y) {
        Element newElement = element.Clone();
        newElement.pos_x = pos_x;
        newElement.pos_y = pos_y;
        newElement.Attach(App.view.getCanvasView());
        elements.add(newElement);
        Notify();
    }

    @Override
    public void Draw(int x, int y) {
        App.view.drawRectangle(this.background, x, y);

        getElements().forEach(element -> element.Draw(x, y));

        if(App.model.getSelectionRectangle() != null) {
            App.model.getSelectionRectangle().Draw(x,y);
        }
    }

}