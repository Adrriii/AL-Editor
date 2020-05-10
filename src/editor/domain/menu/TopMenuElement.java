package editor.domain.menu;

import editor.domain.Drawable;

public abstract class TopMenuElement extends Drawable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TopMenuElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void onClick();
    
}