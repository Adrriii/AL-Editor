package editor.domain.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Canvas;
import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;
import editor.domain.Toolbar;

public class LoadToolbar extends Operation {



    public LoadToolbar() {
    }

    @Override
    public void Do() {
        if (AppController.currentToolbarPath != null) { // Can load
            try {
                FileInputStream f = new FileInputStream(new File(AppController.currentToolbarPath));
                ObjectInputStream o = new ObjectInputStream(f);
                App.model.SetToolbar((Toolbar) o.readObject());
                
                o.close();
                f.close();
            } catch (IOException e) {
                System.out.println("Could not open "+AppController.currentToolbarPath);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Invalid file "+AppController.currentToolbarPath);
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