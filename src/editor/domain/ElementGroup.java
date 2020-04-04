package editor.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ElementGroup extends Element {

    @Override
    public void Add(Element element) throws InvalidCompositeAddition {
        throw new NotImplementedException();
    }

    @Override
    public void Remove(Element element) throws InvalidCompositeAddition {
        throw new NotImplementedException();
    }
}