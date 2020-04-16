package editor.domain.menu;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.Menu;

public abstract class InteractionMenu extends Menu {

    protected ArrayList<Interaction> interactions;

    public InteractionMenu(int x, int y) {
        Attach(App.view.getMainView());

        interactions = new ArrayList<>();

        this.pos_x = x;
        this.pos_y = y;
    }

    protected abstract void SetInteractions();

    public void onClick(int x, int y) {
        interactions.forEach(interaction -> { if (interaction.isClicked(x, y)) interaction.onClick(); });
    }

    @Override
    public void Draw(int x, int y) {
        int curr_y = y;

        Iterator<Interaction> iter = interactions.iterator();

        while(iter.hasNext()) {
            Interaction item = iter.next();
            item.Draw(x, curr_y);
            curr_y += item.height;
        }
    }

}