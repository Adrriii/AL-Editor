package editor.domain.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Canvas;
import editor.domain.Operation;


/**
* An operation aiming at loading a whiteboard from a file.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class LoadCanvas extends Operation {



    public LoadCanvas() {
    }

    @Override
    public void Do() {
        if (AppController.currentCanvasPath != null) { // Can load
            try {
                FileInputStream f = new FileInputStream(new File(AppController.currentCanvasPath));
                ObjectInputStream o = new ObjectInputStream(f);
                App.model.SetCanvas((Canvas) o.readObject());
                App.model.getCanvas().Reattach();
                App.view.getCanvasView().Update();
                System.out.println("Loaded Canvas");

                o.close();
                f.close();
            } catch (IOException e) {
                System.out.println("Could not open "+AppController.currentCanvasPath);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Invalid file "+AppController.currentCanvasPath);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Undo() {
        
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}