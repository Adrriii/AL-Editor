package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.menu.TopMenuElement;

public class TopMenuUndo extends TopMenuElement {

    public TopMenuUndo(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(Position ref) {
        if(AppController.actionControl.CanUndo()) {
            App.view.drawImage(App.getFilePath("assets/img/undo.png"), ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(App.getFilePath("assets/img/undo_grey.png"), ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        AppController.actionControl.Undo();
    }
    
}