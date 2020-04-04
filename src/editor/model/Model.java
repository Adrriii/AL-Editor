package editor.model;

import editor.domain.*;
import editor.domain.menu.*;

public class Model {

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    public Model() {
        canvas = new Canvas();
        toolbar = new Toolbar();
        topMenu = new TopMenu();
    }

    public void Update() {

    }

}