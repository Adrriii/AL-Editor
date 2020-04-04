package editor.domain;

import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ElementGroup extends Element {

    private ArrayList<Element> elements;

    public ElementGroup() {
        super();

        elements = new ArrayList<>();
    }

    @Override
    public void Add(Element element) throws InvalidCompositeAddition {
        elements.add(element);
    }

    @Override
    public void Remove(Element element) throws InvalidCompositeAddition {
        elements.remove(element);
    }

    @Override
    public void Update() {
        elements.forEach(element -> element.Update());
    }
}