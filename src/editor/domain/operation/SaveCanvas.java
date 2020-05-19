package editor.domain.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Operation;

/**
* An operation aiming at saving the whiteboard to a file.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class SaveCanvas extends Operation {

    public SaveCanvas() {
    }

    @Override
    public void Do() {
        if (AppController.currentCanvasPath != null) { // Can save
            try {
                FileOutputStream f = new FileOutputStream(new File(AppController.currentCanvasPath), false);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(App.model.getCanvas());
            
                o.close();
                f.close();
            } catch (IOException e) {
                System.out.println("Could not open "+AppController.currentCanvasPath);
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