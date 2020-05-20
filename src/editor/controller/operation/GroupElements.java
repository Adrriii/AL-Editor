package editor.controller.operation;

import java.util.ArrayList;

import editor.application.App;
import editor.model.Element;
import editor.model.ElementGroup;
import editor.model.InvalidCompositeAddition;
import editor.controller.Operation;


/**
* An operation aiming at adding grouping multiple elements into one ElementGroup.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class GroupElements extends Operation {

    /**
     * The elements that need to be grouped
     */
    protected ArrayList<Element> selected;

    /**
     * The group that has been created
     */
    protected ElementGroup group;

    /**
     * Group elements in a new ElementGroup
     */
    public GroupElements(ArrayList<Element> selected) {
        this.selected = selected;
    }

    @Override
    public void Do() {
        group = new ElementGroup();
        group.Attach(App.view.getCanvasView());

        selected.forEach(element -> {
            try {
                group.Add(element);
                element.Detach(App.view.getCanvasView());
                App.model.getCanvas().removeElement(element);
            } catch (InvalidCompositeAddition e) {
                e.printStackTrace();
            }
        });
        
        App.model.getCanvas().addElement(group);
        App.model.DeselectAll();
        App.model.Select(group);
    }

    @Override
    public void Undo() {
        ArrayList<Element> selected = new ArrayList<>();
        group.Detach(App.view.getCanvasView());
        App.model.getCanvas().removeElement(group);
        
        App.model.DeselectAll();

        new ArrayList<Element>(group.getElements()).forEach(out -> {
            try {
                group.Remove(out);
            }
            catch (InvalidCompositeAddition e) {
                e.printStackTrace();
            }
            App.model.getCanvas().addElement(out);
            out.Attach(App.view.getCanvasView());
            selected.add(out);
            App.model.Select(out);
        });
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}