package editor.application;

import editor.model.Model;
import editor.userinterface.*;
import editor.userinterface.javafximpl.*;

public class App {
    
    public static View view;
    public static Controller controller;
    public static Model model;

    public static void main(String args[]) {

        UserInterface ui = new JavaFXUI();
        App.view = ui.GetView();
        App.controller = ui.GetController();
        App.model = new Model();
        App.model.init();

        while(model.isRunning()) {
            App.model.Update();
            App.controller.Notify();
        }
    }
}