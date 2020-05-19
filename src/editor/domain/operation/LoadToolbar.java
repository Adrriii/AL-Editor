package editor.domain.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import editor.application.App;
import editor.domain.Operation;
import editor.domain.Toolbar;


/**
* An operation aiming at loading a toolbar from a file.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class LoadToolbar extends Operation {

    public LoadToolbar() {
    }

    @Override
    public void Do() {

        try {
            FileInputStream f = new FileInputStream(new File(App.tools));
            ObjectInputStream o = new ObjectInputStream(f);
            App.model.SetToolbar((Toolbar) o.readObject());
            App.model.getToolbar().Reattach();
            o.close();
            f.close();
        } catch (IOException e) {
            // File probably does not exist, creating default
            App.model.SetToolbar(new Toolbar());
            App.model.getToolbar().LoadDefaultTools();
            (new SaveToolbar()).Do();
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid file "+App.tools);
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