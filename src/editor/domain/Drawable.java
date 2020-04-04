package editor.domain;

import java.util.ArrayList;

import editor.application.App;
import editor.userinterface.View;

public abstract class Drawable {

    ArrayList<View> views;

    public Drawable() {
        views = new ArrayList<>();

        Attach(App.view);
    }

    public void Attach(View view) {
        views.add(view);
    }

    public void Detach(View view) {
        views.remove(view);
    }

    public void Notify() {
        views.forEach(view -> view.Update());
    }

}