package org.calidad.ORIGEN;

import java.io.Serializable;

public abstract class Equipo implements Serializable {
    private String tipo;
    private String nombre;
    private int ataque;
    private int defensa;
    private int modificadorAtc;//
    private int modificadorDef;
    //añadir booleano para determinar si un equipo esta activo o no y un metodo

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getModificadorAtc() {
        return modificadorAtc;
    }

    public void setModificadorAtc(int modificadorAtc) {
        this.modificadorAtc = modificadorAtc;
    }

    public int getModificadorDef() {
        return modificadorDef;
    }

    public void setModificadorDef(int modificadorDef) {
        this.modificadorDef = modificadorDef;
    }

    public abstract Equipo modificar();
}