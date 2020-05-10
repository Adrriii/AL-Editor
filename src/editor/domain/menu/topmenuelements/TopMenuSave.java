package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.menu.TopMenuElement;
import editor.domain.operation.SaveCanvas;

public class TopMenuSave extends TopMenuElement {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TopMenuSave(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(Position ref) {
        if (AppController.currentCanvasPath != null) { // Can save
            if(!isClicked(App.appController.CurrentMousePos())) 
                App.view.drawImage(App.getFilePath("assets/img/save.png"), ref.x, ref.y, width, height);
            else
                App.view.drawImage(App.getFilePath("assets/img/save_hover.png"), ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(App.getFilePath("assets/img/save_grey.png"), ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        (new SaveCanvas()).Do();
    }
    
}