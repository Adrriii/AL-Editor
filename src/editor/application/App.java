package editor.application;

import editor.model.Model;
import editor.userinterface.*;
import editor.userinterface.javafximpl.*;

public class App {
    
    public static View view;
    public static Controller controller;
    public static Model model;

    public static void main(String args[]) {
        model = new Model();

        UserInterface ui = new JavaFXUI();

        App.view = ui.GetView(App.model);
        App.controller = ui.GetController();

        while(true) {
            App.model.Update();
            App.controller.Notify();
            App.view.Update();
        }
    }
}