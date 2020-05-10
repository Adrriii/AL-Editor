package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.menu.TopMenuElement;

public class TopMenuRedo extends TopMenuElement {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TopMenuRedo(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(Position ref) {
        if(AppController.actionControl.CanRedo()) {
            if(!isClicked(App.appController.CurrentMousePos())) 
                App.view.drawImage(App.getFilePath("assets/img/redo.png"), ref.x, ref.y, width, height);
            else
                App.view.drawImage(App.getFilePath("assets/img/redo_hover.png"), ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(App.getFilePath("assets/img/redo_grey.png"), ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        AppController.actionControl.Redo();
    }
    
}