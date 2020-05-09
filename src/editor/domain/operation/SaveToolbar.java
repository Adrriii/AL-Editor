package editor.domain.operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;

public class SaveToolbar extends Operation {



    public SaveToolbar() {
    }

    @Override
    public void Do() {
            try {
                FileOutputStream f = new FileOutputStream(new File(".ale-tool"), false);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(App.model.getToolbar());
            
                o.close();
                f.close();
            } catch (IOException e) {
                System.out.println("Could not open .ale-tool");
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