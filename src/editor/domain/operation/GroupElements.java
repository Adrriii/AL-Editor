package editor.domain.operation;

import java.util.ArrayList;
import java.util.HashSet;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;
import editor.domain.Operation;

public class GroupElements extends Operation {

    protected HashSet<Element> selected;
    protected ElementGroup group;

    public GroupElements(HashSet<Element> selected) {
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
        selected = new HashSet<>();
        ArrayList<Element> elements = new ArrayList<>(((ElementGroup) group).getElements());

        group.Detach(App.view.getCanvasView());
        App.model.getCanvas().removeElement(group);
        
        App.model.DeselectAll();

        elements.forEach(out -> {
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