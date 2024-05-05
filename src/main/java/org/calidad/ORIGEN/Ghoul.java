package org.calidad.ORIGEN;

import java.io.Serializable;

public class Ghoul extends Esbirro implements Serializable {
    private Integer valordependencia;
    private String dependencia;

    public Ghoul(String nombre, int salud, int valorDependencia, String esbirroDependencia) {
        super(nombre, salud);
        this.valordependencia = valorDependencia;
        this.dependencia = esbirroDependencia;
    }
}
