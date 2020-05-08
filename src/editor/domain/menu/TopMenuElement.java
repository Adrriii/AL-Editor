package editor.domain.menu;

import editor.domain.Drawable;
import editor.domain.Position;

public abstract class TopMenuElement extends Drawable {
    
    public TopMenuElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void onClick();
    
}