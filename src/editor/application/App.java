package editor.application;

import editor.domain.AppController;
import editor.model.Model;
import editor.userinterface.*;
import editor.userinterface.javafximpl.*;

public class App {

    public static View view;
    public static Controller controller;
    public static AppController appController;
    public static Model model;
    public static boolean ready;

    public static void main(String args[]) {

        ready = false;

        UserInterface ui = new JavaFXUI();
        App.view = ui.GetView();
        App.controller = ui.GetController();
        App.model = new Model();

        App.model.init();

        App.appController = new AppController();
        
        ready = true;

        while (model.isRunning()) {
            App.model.Update();

            try {
                Thread.sleep(16); // 60 fps, need to process delta time to make it better
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFilePath(String relativePath) {
        return System.getProperty("user.dir") + "\\" + relativePath.replace("/", "\\");
    }
}