package de.hbrs.ia.model;

import de.hbrs.ia.model.managepersonal.ManagePersonalUI;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {


    public static void main(String[] args) throws IllegalAccessException {
        Logger l = Logger.getLogger("org.mongodb.driver");
        l.setLevel(Level.WARNING);
        ManagePersonalUI ui = new ManagePersonalUI();
        ui.dialog();






    }
}

