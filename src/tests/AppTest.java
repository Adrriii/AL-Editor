package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import editor.application.App;

public class AppTest {
    
    
    @Test
    public void rectangleProperties() {
        String[] args = new String[1];
        args[0] = "tests";

        App.main(args);

        assertTrue("App starting in test", true);
    }
}