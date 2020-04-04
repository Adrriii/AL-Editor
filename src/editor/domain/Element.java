package editor.domain;

import editor.userinterface.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Element implements IDrawable, ISerializable, IControllable, IClonable {

	@Override
	public void Attach(View view) {
		throw new NotImplementedException();
	}

	@Override
	public void Detach(View view) {
		throw new NotImplementedException();
	}

	@Override
	public void Notify() {
		throw new NotImplementedException();
	}

	@Override
	public void Draw() {
		throw new NotImplementedException();
    }
    
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