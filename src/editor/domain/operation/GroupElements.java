package editor.domain.operation;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;
import editor.domain.Operation;


/**
* An operation aiming at adding grouping multiple elements into one ElementGroup.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class GroupElements extends Operation {

    protected ArrayList<Element> selected;
    protected ElementGroup group;

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