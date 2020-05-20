package editor.userinterface.views;

import editor.application.App;
import editor.userinterface.ViewScope;

/**
* The whole window, and floating elements.
* Invalidating it causes all other scopes to be invaldiated as well.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewMain extends ViewScope {

    @Override
    public String GetScopeName() {
        return "main";
    }

    @Override
    public void Tick() {
        if(!App.ready) return;
        if(invalidated) {
            App.view.getCanvasView().Update();
            App.view.getToolbarView().Update();
            App.view.getTopMenuView().Update();
        }

        App.view.getCanvasView().Tick();
        App.view.getToolbarView().Tick();
        App.view.getTopMenuView().Tick();

        if(!invalidated) return;

        App.model.getControls().forEach(control -> control.Draw(GetScopeName()));
        if (App.model.getInteractionMenu() != null) App.model.getInteractionMenu().Draw(GetScopeName());

        invalidated = false;
    }

}