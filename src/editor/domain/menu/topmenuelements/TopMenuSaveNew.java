package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.menu.TopMenuElement;
import editor.domain.operation.SaveCanvas;

/**
* A button that triggers the saving of the whiteboard in a new file.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenuSaveNew extends TopMenuElement {

    private static final long serialVersionUID = 1L;

    public TopMenuSaveNew(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(String viewName, Position ref) {
        if(!isClicked(App.appController.CurrentMousePos())) 
            App.view.drawImage(viewName, "assets/img/save_new.png", ref.x, ref.y, width, height);
        else
            App.view.drawImage(viewName, "assets/img/save_new_hover.png", ref.x, ref.y, width, height);
    }

    @Override
    public void onClick() {
        AppController.currentCanvasPath = App.controller.getChosenSaveFile();
        (new SaveCanvas()).Do();
    }
    
}