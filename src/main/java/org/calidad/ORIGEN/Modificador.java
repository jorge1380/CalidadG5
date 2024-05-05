package org.calidad.ORIGEN;

import org.calidad.Controladores.Pantalla;

import java.io.Serializable;

public class Modificador implements Serializable {
    private String nombreDeb;//poneNombreDeb
    private int sensibilidadDeb;
    private String nombreFort;
    private int sensibilidadFort;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    private boolean activo;

    public String getNombreFort() {
        return nombreFort;
    }

    public void setNombreFort(String nombreFort) {
        this.nombreFort = nombreFort;
    }

    public int getSensibilidadDeb() {
        return sensibilidadDeb;
    }

    public void setSensibilidadDeb(int sensibilidadDeb) {
        this.sensibilidadDeb = sensibilidadDeb;
    }

    public String getNombreDeb() {
        return nombreDeb;
    }

    public void setNombreDeb(String nombreDeb) {
        this.nombreDeb = nombreDeb;
    }

    public int getSensibilidadFort() {
        return sensibilidadFort;
    }

    public void setSensibilidadFort(int sensibilidadFort) {
        this.sensibilidadFort = sensibilidadFort;
    }

    public Modificador modificar(){
        Pantalla.imprimir("Nombre: "+ this.getNombreDeb());
        this.setNombreDeb(nombreDeb);
        Pantalla.imprimir("Nombre: "+ this.getNombreFort());
        this.setNombreFort(nombreFort);
        Pantalla.imprimir("Valor: "+ this.getSensibilidadDeb());
        this.setSensibilidadDeb(sensibilidadDeb);
        Pantalla.imprimir("Valor: "+ this.getSensibilidadFort());
        this.setSensibilidadFort(sensibilidadFort);
        return this;
    }
}
