package org.calidad.ORIGEN;

import java.io.Serializable;

public class Humano extends Esbirro implements Serializable {
    private String lealtad;

    public Humano(String nombre, int salud) {
        super(nombre, salud);
    }
}
