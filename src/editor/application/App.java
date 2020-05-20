package editor.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import editor.controller.AppController;
import editor.model.Model;
import editor.userinterface.*;
import editor.userinterface.javafximpl.*;
import editor.userinterface.testimpl.*;

/**
* This is the entry class of the application.
* The main method can be called with {"tests"} to initialize only.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class App {

    public static View view;
    public static Controller controller;
    public static AppController appController;
    public static ControlFactory controlFactory;
    public static Model model;
    public static boolean ready;

    public static boolean win_env = true;
    public static String tools = ".ale-tool";

    public static int callcount = 0;

    /**
    * This is the entry method of the application.
    * 
    * @param args Insert the string "tests" to perform basic init only.
    */
    public static void main(String args[]) {

        ready = false;

        UserInterface ui;

        if(args.length > 0 && args[0] == "tests") {
            // App started in test mode
            
            App.tools = ".ale-tool-test"; 
            // Don't use the same tools for testing

            App.controlFactory = new TestControlFactory();
            ui = new TestUI();
        } else {
            App.controlFactory = new JavaFXControlFactory();
            ui = new JavaFXUI();
        }

        App.view = ui.GetView();
        App.controller = ui.GetController();
        App.model = new Model();

        App.model.init();

        App.appController = new AppController();

        ready = true;

        // Don't start the app loop when testing
        if(args.length == 0 || args[0] != "tests") { 
            while (model.isRunning()) {
                App.model.Update();

                try {
                    Thread.sleep(16); // 60 fps, need to process delta time to make it better
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
    * Returns a correctly formatted absolute path depending on the currently detected platform.
    * 
    * @param relativePath A path relative to the current application's execution directory.
    */
    public static String getFilePath(String relativePath) {
        if (App.win_env) {
            return System.getProperty("user.dir") + "\\" + relativePath.replace("/", "\\");
        } else {
            return System.getProperty("user.dir") + "/" + relativePath.replace("\\", "/");
        }
    }

    /**
    * Returns a FileInputStream (or null) from a path relative to the current application's execution directory.
    * 
    * @param relativePath A path relative to the current application's execution directory.
    */
    public static FileInputStream load(String path) throws FileNotFoundException {
        try {
            return new FileInputStream(App.getFilePath(path));
        } catch (FileNotFoundException e) {
            if(App.win_env) {
                App.win_env = false;
                return App.load(path);
            }
            throw e;
        }
    }
}