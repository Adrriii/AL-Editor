package editor.domain;

import java.util.ArrayList;

public abstract class Element extends Drawable implements ISerializable, IControllable, IClonable {

    private ArrayList<ElementProperty> properties;

    public Element() {
        super();

        properties = new ArrayList<>();
    }

    public void Add(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }
    
    public void Remove(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }

    public void Update() {
        Notify();
    }
}