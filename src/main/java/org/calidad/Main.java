package org.calidad;

import org.calidad.Controladores.Appcontroller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Appcontroller appcontroller= new Appcontroller();
        appcontroller.start();
    }
}