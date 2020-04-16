package editor.domain.menu;

import java.util.ArrayList;
import java.util.Iterator;

import editor.application.App;
import editor.domain.Menu;

public abstract class InteractionMenu extends Menu {

    protected ArrayList<Interaction> interactions;

    private int interaction_pos_y;

    public InteractionMenu(int x, int y) {
        Attach(App.view.getMainView());

        interactions = new ArrayList<>();

        this.pos_x = x;
        this.pos_y = y;
        this.interaction_pos_y = y;

        this.height = 0;
        this.width = 0;
    }

    protected abstract void SetInteractions();

    public void onClick(int x, int y) {
        interactions.forEach(interaction -> { if (interaction.isClicked(x, y)) interaction.onClick(); });
    }

    public void addInteraction(Interaction interaction) {
        interaction.pos_x = pos_x;
        interaction.pos_y = interaction_pos_y;
        interaction_pos_y += interaction.height;

        this.interactions.add(interaction);

        this.height = interaction_pos_y;
        this.width = interaction.width > this.width ? interaction.width : this.width;
    }

    public void removeInteraction(Interaction interaction) {
        this.interactions.remove(interaction);
        this.height -= interaction.height;
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