package org.calidad.ORIGEN;

import org.calidad.Controladores.Pantalla;

import java.io.Serializable;
import java.util.Scanner;

public class Arma extends Equipo implements Serializable {
    private int empuñadura; //1 una mano 2 dos manos
    //@override modificar(){}

    public void setEmpuñadura(int manos){
        this.empuñadura = manos;
    }

    public int getEmpuñadura(){
        return this.empuñadura;
    }

    @Override
    public Arma modificar(){
        Pantalla.imprimir(("Vas a modificar esta arma" + this.getNombre()));
        this.setNombre(Pantalla.pedircadena("establece Nombre"));
        this.setModificadorAtc(Pantalla.pedirenteros("establece ataque"));
        this.setModificadorDef(Pantalla.pedirenteros("establece defensa"));
        this.setEmpuñadura(Pantalla.pedirenteros("establece si es a 1 mano o a 2 manos"));
    return this;
    }
}
