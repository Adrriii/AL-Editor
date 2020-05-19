package editor.userinterface.views;

import editor.application.App;
import editor.domain.Toolbar;
import editor.userinterface.ViewScope;

/**
* The left part of the window, containing the available tools.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewToolbar implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        Toolbar toolbar = App.model.getToolbar();

        toolbar.Draw(GetScopeName());

        invalidated = false;
    }

    @Override
    public String GetScopeName() {
        return "toolbar";
    }

}