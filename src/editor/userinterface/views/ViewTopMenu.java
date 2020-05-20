package editor.userinterface.views;

import editor.application.App;
import editor.model.menu.TopMenu;
import editor.userinterface.ViewScope;

/**
* The top part of the window, containing some action buttons.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewTopMenu extends ViewScope {

    @Override
    public String GetScopeName() {
        return "topmenu";
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        TopMenu topMenu = App.model.getTopMenu();

        topMenu.Draw(GetScopeName());

        invalidated = false;
    }

}