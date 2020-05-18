package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.operation.AddToolbarElement;
import editor.domain.operation.MoveElement;

public class AppTest {

    boolean started = false;
    
    public void AppStart() {
        if(started) return;

        String[] args = new String[1];
        args[0] = "tests";

        App.main(args);

        started = true;
    }

    @Test
    public void ModelTest() {
        AppStart();
        
        assertTrue(App.model != null);
        assertTrue(App.model.getCanvas() != null);
        assertTrue(App.model.getToolbar() != null);
        assertTrue(App.model.getTopMenu() != null);
    }

    @Test
    public void ActionControlTest() {
        AppStart();

        Rectangle obj = new Rectangle(1, 2);
        App.model.getCanvas().addElement(obj);

        assertTrue(App.model.getCanvas().getElements().get(0) == obj); // Element not cloned

        assertTrue(obj.pos.x == 0);
        assertTrue(obj.pos.y == 0);
        AppController.actionControl.Do(new MoveElement(obj, obj.pos, new Position(10,10)));
        assertTrue(obj.pos.x == 10);
        assertTrue(obj.pos.y == 10);
        AppController.actionControl.Undo();
        assertTrue(obj.pos.x == 0);
        assertTrue(obj.pos.y == 0);
        AppController.actionControl.Redo();
        assertTrue(obj.pos.x == 10);
        assertTrue(obj.pos.y == 10);
        AppController.actionControl.Undo();
        AppController.actionControl.Do(new MoveElement(obj, obj.pos, new Position(30,30)));
        AppController.actionControl.Do(new MoveElement(obj, obj.pos, new Position(40,40)));
        assertTrue(obj.pos.x == 40);
        assertTrue(obj.pos.y == 40);
        AppController.actionControl.Redo();
        assertTrue(obj.pos.x == 40);
        assertTrue(obj.pos.y == 40);
        AppController.actionControl.Undo();
        AppController.actionControl.Undo();
        assertTrue(obj.pos.x == 0);
        assertTrue(obj.pos.y == 0);
        AppController.actionControl.Redo();
        assertTrue(obj.pos.x == 30);
        assertTrue(obj.pos.y == 30);
        AppController.actionControl.Do(new MoveElement(obj, obj.pos, new Position(10,10)));
        AppController.actionControl.Redo(); // Don't redo the 40,40 action !
        assertTrue(obj.pos.x == 10);
        assertTrue(obj.pos.y == 10);
    }

    @Test
    public void ToolbarTest() {
        AppStart();

        // Clear
        while(App.model.getToolbar().getToolbarElements().size() > 0) {
            App.model.getToolbar().removeElement(App.model.getToolbar().getToolbarElements().get(0));
        }

        Rectangle obj = new Rectangle(1, 2);
        
        AppController.actionControl.Do(new AddToolbarElement(obj));
        AppController.actionControl.Do(new AddToolbarElement(obj));

        assertTrue(App.model.getToolbar().getToolbarElements().get(0).getElement() != obj); // Element cloned

        App.model.setSelectedTool(App.model.getToolbar().getToolbarElements().get(0));

        assertTrue(App.model.getToolbar().getToolbarElements().get(0) == App.model.getSelectedTool()); // Tool selected

        AppController.actionControl.Undo();

        assertTrue(App.model.getToolbar().getToolbarElements().size() == 1);

        AppController.actionControl.Redo();

        assertTrue(App.model.getToolbar().getToolbarElements().size() == 2);

        AppController.actionControl.Undo();
        AppController.actionControl.Undo();

        assertTrue(App.model.getToolbar().getToolbarElements().size() == 0);

        AppController.actionControl.Undo();

        assertTrue(App.model.getToolbar().getToolbarElements().size() == 0);
        
        AppController.actionControl.Redo();

        assertTrue(App.model.getToolbar().getToolbarElements().size() == 1);
    }
}