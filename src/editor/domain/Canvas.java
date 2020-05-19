package editor.domain;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.domain.element.Rectangle;

public class Canvas extends Drawable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Element> elements;

    private Rectangle background;

    public Canvas() {
        super();

        Attach(App.view.getCanvasView());

        elements = new ArrayList<>();

        this.pos.x = 75; // Width of the Toolbar
        this.pos.y = 75; // Height of the TopMenu

        this.width = 800 - this.pos.x; // Resizable
        this.height = 600 - this.pos.y; // Resizable

        this.background = new Rectangle(0, 0, width, height, 255, 255, 255, 255);
    }

    @Override
    public void Reattach() {
        super.Reattach();
        Attach(App.view.getCanvasView());
        this.elements.forEach(element -> element.Reattach(App.view.getCanvasView()));
    }

    public ArrayList<Element> getElements() {
        return new ArrayList<Element>(elements);
    }

    public void removeElement(Element element) {
        this.elements.remove(element);
    }

    public void addElement(Element element) {
        element.Detach(App.view.getCanvasView());
        element.Attach(App.view.getCanvasView());
        this.elements.add(element);
    }

    public Element newElement(Element element, Position pos) {
        Element newElement = element.Clone();
        newElement.Attach(App.view.getCanvasView());
        newElement.Update(pos);
        elements.add(newElement);
        Notify();
        return newElement;
    }
    
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    @Override
    public void Draw(String viewName, Position pos) {
        App.view.drawRectangle(viewName, this.background, pos.x, pos.y);

        getElements().forEach(element -> element.Draw(viewName, new Position(pos.x + element.pos.x, pos.y + element.pos.y)));

        if(App.model.getSelectionRectangle() != null) {
            App.model.getSelectionRectangle().Draw(viewName, new Position(pos.x + App.model.getSelectionRectangle().pos.x, pos.y + App.model.getSelectionRectangle().pos.y));
        }
    }

    public Optional<Element> getElementAt(int x, int y) {
        return this.elements.stream().filter(element -> element.isClicked(x, y)).findFirst();
    }

    public void SetOnTop(Element element) {
        this.elements.remove(element);
        this.elements.add(element);
    }

    @Override
    public String toString() {
        String str = "Canvas";
        for(Element elem: elements) str += "\n" + elem.toString();
        return str;
    }

}