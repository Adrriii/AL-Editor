package editor.domain.menu;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.Menu;
import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.menu.topmenuelements.*;

public class TopMenu extends Menu {

    private Rectangle background;
    private ArrayList<TopMenuElement> elements;

    private int nextPosX;

    private int elementWidth;
    private int elementHeight;
    private int elementPaddingY;
    private int elementPaddingX;
    
    public TopMenu() {
        super();

        Attach(App.view.getTopMenuView());

        this.pos.x = 0;
        this.pos.y = 0;

        this.nextPosX = 0;

        this.width = 800; // Resizable
        this.height = 75; // Constant

        elementPaddingX = 10;
        elementPaddingY = 10;
        elementHeight = height - elementPaddingY * 2;
        elementWidth = elementHeight;

        this.background = new Rectangle(pos.x, pos.y, width, height, 211, 211, 211, 255);
        this.elements = new ArrayList<>();
    
        addElement(new TopMenuLoad(elementWidth, elementHeight));
        addElement(new TopMenuSave(elementWidth, elementHeight));
        addElement(new TopMenuSaveNew(elementWidth, elementHeight));
        addElement(new TopMenuUndo(elementWidth, elementHeight));
        addElement(new TopMenuRedo(elementWidth, elementHeight));
    }

    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    public void onClick(int x, int y) {
        elements.forEach(element -> { if (element.isClicked(x, y)) element.onClick(); });
    }

    public void addElement(TopMenuElement element) {
        element.pos.x = nextPosX + elementPaddingX;
        element.pos.y = pos.y + elementPaddingY;
        nextPosX = element.pos.x + element.width;

        this.elements.add(element);

        this.width = nextPosX - pos.x;
    }

    public void removeElement(TopMenuElement element) {
        this.elements.remove(element);
        this.width -= element.width + elementPaddingX;
    }

    @Override
    public void Draw(Position pos) {
        App.view.drawRectangle(this.background, pos.x, pos.y);
        
        int curr_x = pos.x;

        Iterator<TopMenuElement> iter = elements.iterator();

        while(iter.hasNext()) {
            TopMenuElement item = iter.next();
            item.Draw(new Position(curr_x + elementPaddingX, pos.y + elementPaddingY));
            curr_x += item.width + elementPaddingX;
        }
    }
}