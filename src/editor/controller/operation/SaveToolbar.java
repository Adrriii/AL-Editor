package editor.controller.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.controller.Operation;

/**
* An operation aiming at saving the toolbar to a file.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class SaveToolbar extends Operation {

    /**
     * Save the current toolbar to the current temporary file
     */
    public SaveToolbar() {
    }

    @Override
    public void Do() {
            try {
                FileOutputStream f = new FileOutputStream(new File(App.tools), false);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(App.model.getToolbar());
            
                o.close();
                f.close();
            } catch (IOException e) {
                System.out.println("Could not open "+App.tools);
                e.printStackTrace();
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