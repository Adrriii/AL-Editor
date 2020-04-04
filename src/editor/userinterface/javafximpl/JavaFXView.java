package editor.userinterface.javafximpl;

import editor.model.Model;
import editor.userinterface.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JavaFXView implements View {

    private Model model;

    public JavaFXView(Model model) {
        this.model = model;
    }

    @Override
    public void Update() {
        throw new NotImplementedException();
    }

}