package editor.userinterface.views;

import editor.application.App;
import editor.model.Toolbar;
import editor.userinterface.ViewScope;

/**
* The left part of the window, containing the available tools.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewToolbar extends ViewScope {

    @Override
    public String GetScopeName() {
        return "toolbar";
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        Toolbar toolbar = App.model.getToolbar();

        toolbar.Draw(GetScopeName());

        invalidated = false;
    }

}