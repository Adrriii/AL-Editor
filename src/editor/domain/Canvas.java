package editor.domain;

import java.util.ArrayList;
import java.util.Optional;

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

    public void removeElement(Element element) {
        this.elements.remove(element);
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }

    public Element newElement(Element element, int pos_x, int pos_y) {
        Element newElement = element.Clone();
        newElement.Attach(App.view.getCanvasView());
        newElement.Update(pos_x, pos_y);
        elements.add(newElement);
        Notify();
        return newElement;
    }

    @Override
    public void Draw(int x, int y) {
        App.view.drawRectangle(this.background, x, y);

        getElements().forEach(element -> element.Draw(pos_x + element.pos_x, pos_y + element.pos_y));

        if(App.model.getSelectionRectangle() != null) {
            App.model.getSelectionRectangle().Draw(pos_x + App.model.getSelectionRectangle().pos_x, pos_y + App.model.getSelectionRectangle().pos_y);
        }
    }

    public Optional<Element> getElementAt(int x, int y) {
        return this.elements.stream().filter(element -> element.isClicked(x, y)).findFirst();
    }

}