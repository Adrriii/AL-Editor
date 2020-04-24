package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.menu.TopMenu;
import editor.userinterface.ViewScope;

public class JavaFXViewTopMenu implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        TopMenu topMenu = App.model.getTopMenu();

        topMenu.Draw();
        //drawJavaFXRectangle(menu.pos_x, menu.pos_y, menu.width, menu.height, Color.LIGHTGREY);

        invalidated = false;
    }

}