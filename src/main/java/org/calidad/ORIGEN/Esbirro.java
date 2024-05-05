package org.calidad.ORIGEN;

import java.io.Serializable;
import java.util.List;

public abstract class Esbirro implements Serializable {
    private String nombre;
    private int salud;

    public Esbirro(String nombre, int salud) {
        this.nombre = nombre;
        this.salud = salud;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int calcularSalud(){
        return getSalud();
    }
}
