package org.calidad.ORIGEN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Demonio extends Esbirro implements Serializable {
    private String pacto;
    private ArrayList<Esbirro> esbirros;

    public Demonio(String nombre, int salud, List<Esbirro> subLista) {
        super(nombre, salud);
    }
}
