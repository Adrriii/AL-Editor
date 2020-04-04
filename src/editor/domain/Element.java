package editor.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Element extends Drawable implements ISerializable, IControllable, IClonable {

    public void Add(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }
    
    public void Remove(Element element) throws InvalidCompositeAddition {
        throw new InvalidCompositeAddition();
    }

    @Override
    public void Update() {
        throw new NotImplementedException();
    }

}