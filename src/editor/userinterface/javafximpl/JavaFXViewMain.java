package editor.userinterface.javafximpl;

import editor.application.App;
import editor.userinterface.ViewScope;

public class JavaFXViewMain implements ViewScope {

    @Override
    public void Update() {

        App.view.getCanvasView().Update();
        App.view.getToolbarView().Update();
        App.view.getTopMenuView().Update();

        if (App.model.getInteractionMenu() != null) App.model.getInteractionMenu().Draw();
    }

}