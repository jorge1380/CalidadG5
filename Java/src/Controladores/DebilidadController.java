package Controladores;

import ORIGEN.Debilidad;
import ORIGEN.Personaje;
import java.util.List;


public class DebilidadController {
    
    public Debilidad crearDebilidad(){//Se crean, escogen valores y asignan las debilidades de los personajes
        Debilidad debilidad = new Debilidad();
        debilidad.setActivo(false);
        debilidad.setNombreFort(Pantalla.pedircadena("Nombre de la debilidad: "));
        while (debilidad.getNombreFort().equals(""))
            debilidad.setNombreFort(Pantalla.pedircadena("No puede estar vacío: "));
        debilidad.setSensibilidadFort(Pantalla.pedirenteros("Valor de la debilidad: "));
        return debilidad;
    }

    public Personaje editarDebilidad(Personaje personaje) { //permite editar cualquier debilidad que tenga el personaje
        List<Debilidad> lista = personaje.getDebilidades();
        if (lista == null){ //no existe la lista de debilidades
            Pantalla.imprimir("El personaje no tiene debilidades");
            Pantalla.imprimir("Volviendo...");
            return personaje;
        }
        int t = lista.size();
        if (t <= 0){ //el personaje no tiene ninguna debilidad creada ya que la lista de debilidades esta vacia
            Pantalla.imprimir("El personaje no tiene debilidades");
            Pantalla.imprimir("Volviendo...");
            return personaje;
        }
        //el personaje tiene una o mas debilidades creadas
        for (int i = 0; i < t; i++) {//se recorre la lista y se muestran por pantalla
            Debilidad actual = lista.get(i);
            if (actual == null)
                continue;
            Pantalla.imprimir(i + ". " + actual.getNombreDeb());
        }
        Pantalla.imprimir(t + ". Cancelar operación.");
        int n = -1;
        while (n < 0 || n > t){
            n = Pantalla.pedirenteros("Elije una debilidad");
        }
        if (n == t){//comprueba si en la lista solo hay una debilidad
            return personaje;
        }
        if (personaje.getDebilidades().get(n) != null)//se vuelve a comprobar que la debilidad escogida por el usuario exista y entonces se modifica
            personaje.getDebilidades().get(n).modificar();
        return personaje;

    }


}