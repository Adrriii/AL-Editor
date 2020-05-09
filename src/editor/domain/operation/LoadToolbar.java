package editor.domain.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import editor.application.App;
import editor.domain.Operation;
import editor.domain.Toolbar;

public class LoadToolbar extends Operation {



    public LoadToolbar() {
    }

    @Override
    public void Do() {

        try {
            FileInputStream f = new FileInputStream(new File(".ale-tool"));
            ObjectInputStream o = new ObjectInputStream(f);
            App.model.SetToolbar((Toolbar) o.readObject());
            System.out.println("Loaded Toolbar");
            System.out.println(App.model.getToolbar().toString());
            o.close();
            f.close();
        } catch (IOException e) {
            // File probably does not exist, creating default
            App.model.SetToolbar(new Toolbar());
            App.model.getToolbar().LoadDefaultTools();
            (new SaveToolbar()).Do();
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid file .ale-tool");
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