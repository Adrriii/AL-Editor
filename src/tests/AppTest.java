package tests;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Element;
import editor.model.Position;
import editor.model.element.Rectangle;
import editor.controller.operation.AddToolbarElement;
import editor.controller.operation.CreateElement;
import editor.controller.operation.MoveElement;

public class AppTest {

    boolean started = false;
    
    public void AppStart() {
        if(started) return;

        String[] args = new String[1];
        args[0] = "tests";
        // Start the program in test mode
        App.main(args);

        started = true;
    }

    @Test
    public void ModelTest() {
        // Tries to intialize the model
        AppStart();
        
        assertTrue(App.model != null);
        assertTrue(App.model.getCanvas() != null);
        assertTrue(App.model.getToolbar() != null);
        assertTrue(App.model.getTopMenu() != null);
    }

    @Test
    public void ActionControlTest() {
        // Tests if the action manager (not the operations) is working as intended 
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
        // Tests if adding and removing toolbar elements works correctly
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

    @Test
    public void CanvasTest() {
        // Tests if handling elements in the whiteboard works correctly
        AppStart();

        Rectangle rec = new Rectangle(10, 20);

        AppController.actionControl.Do(new CreateElement(rec, new Position(4,3)));

        Optional<Element> elemOk = App.model.getCanvas().getElementAt(5, 4);
        Optional<Element> elemNo = App.model.getCanvas().getElementAt(1, 1);

        assertTrue(!elemNo.isPresent()); // test getElementat
        assertTrue(elemOk.isPresent()); // element is present and obtainable

        Element elem = elemOk.get();

        assertTrue(elem.width == 10);
        assertTrue(elem.height == 20); // basic properties preserved

        AppController.actionControl.Do(new MoveElement(elem, elem.pos, new Position(6,5)));

        elemOk = App.model.getCanvas().getElementAt(7, 6);
        elemNo = App.model.getCanvas().getElementAt(4, 3);

        assertTrue(!elemNo.isPresent()); // test getElementat when previouis position still here
        assertTrue(elemOk.isPresent()); // element is present and obtainable at new position

        AppController.actionControl.Undo();

        elemOk = App.model.getCanvas().getElementAt(5, 4);
        elemNo = App.model.getCanvas().getElementAt(1, 1);

        assertTrue(!elemNo.isPresent()); // test getElementat undo
        assertTrue(elemOk.isPresent()); // element is present and obtainable undo
    }
}