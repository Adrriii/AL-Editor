package editor.model.menu.topmenuelements;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Position;
import editor.model.menu.TopMenuElement;
import editor.controller.operation.SaveCanvas;

/**
* A button that triggers the saving of the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenuSave extends TopMenuElement {

    private static final long serialVersionUID = 1L;

    public TopMenuSave(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(String viewName, Position ref) {
        if (AppController.currentCanvasPath != null) { // Can save
            if(!isClicked(App.appController.CurrentMousePos())) 
                App.view.drawImage(viewName, "assets/img/save.png", ref.x, ref.y, width, height);
            else
                App.view.drawImage(viewName, "assets/img/save_hover.png", ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(viewName, "assets/img/save_grey.png", ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        if (AppController.currentCanvasPath == null) return;
        (new SaveCanvas()).Do();
    }
    
}