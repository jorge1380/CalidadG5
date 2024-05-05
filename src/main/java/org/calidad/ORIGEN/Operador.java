
package org.calidad.ORIGEN;

import org.calidad.Controladores.Pantalla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paula
 */

public class Operador extends Usuario implements Serializable{
    private List<Desafio> listadesafios;
    public void insertardesafio(Desafio desafio){
        if (this.listadesafios == null){
            this.listadesafios = new ArrayList<>();
        }
        this.listadesafios.add(desafio);
    }
    public void mostrardesafios(){
        int i = 0;
        for (Desafio d : this.listadesafios){
            Pantalla.imprimir(i + (". ") + d.getUserUno().getNombre() + (" vs ") + d.getUserDos().getNombre());
            i += 1;
        }
    }

    public void validardesafio(int i){
        Desafio desafio = this.listadesafios.get(i);
        Usuario a = desafio.getUserDos();
        Usuario b = desafio.getUserDos();
        a.setDesafio(desafio);
        b.setDesafio(desafio);
    }
}