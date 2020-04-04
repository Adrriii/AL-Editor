package editor.userinterface.javafximpl;

import java.util.ArrayList;

import editor.domain.IControllable;
import editor.userinterface.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JavaFXController implements Controller {

    private ArrayList<IControllable> controllables;

    public JavaFXController() {
        controllables = new ArrayList<>();
    }

    @Override
    public void Attach(IControllable controllable) {
        controllables.add(controllable);
    }

    @Override
    public void Detach(IControllable controllable) {
        controllables.remove(controllable);
    }

    @Override
    public void Notify() {
        throw new NotImplementedException();
    }

}