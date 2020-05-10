package editor.domain.menu.interactionmenus;

import editor.domain.Element;

public class ColorChangeInteraction extends ElementInteraction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ColorChangeInteraction(Element element) {
        super("Change color", element);
    }

    @Override
    public void onClick() {
        System.out.println("Change color");
    }

}