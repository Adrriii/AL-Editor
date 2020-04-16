package editor.domain.menu.interactionmenus;

import editor.domain.Element;

public class GroupElementsInteraction extends ElementInteraction {

    public GroupElementsInteraction(Element element) {
        super("Group", element);
    }

    @Override
    public void onClick() {
        System.out.println("Group elements");
    }

}