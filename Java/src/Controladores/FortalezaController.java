package Controladores;

import java.util.List;
import ORIGEN.Fortaleza;
import ORIGEN.Personaje;

public class FortalezaController {
        public Fortaleza crearFortaleza(){//Se crean, escogen valores y asignan las fortalezas de los personajes
        Fortaleza fortaleza = new Fortaleza();
        fortaleza.setActivo(false);
        fortaleza.setNombreFort(Pantalla.pedircadena("Nombre de la fortaleza: "));
        while (fortaleza.getNombreFort().equals(""))
            fortaleza.setNombreFort(Pantalla.pedircadena("No puede estar vacío: "));
        fortaleza.setSensibilidadFort(Pantalla.pedirenteros("Valor de la fortaleza: "));
        return fortaleza;
    }
     
    public Personaje editarFortaleza(Personaje personaje) { //permite editar cualquier fortaleza que tenga el personaje
        List<Fortaleza> lista = personaje.getFortalezas();
        if (lista == null){ //no existe la lista de fortalezas
            Pantalla.imprimir("El personaje no tiene fortalezas");
            Pantalla.imprimir("Volviendo...");
            return personaje;
        }
        int t = lista.size();
        if (t <= 0){ //el personaje no tiene ninguna fortaleza creada ya que la lista de fortalezas esta vacia
            Pantalla.imprimir("El personaje no tiene fortalezas");
            Pantalla.imprimir("Volviendo...");
            return personaje;
        }
        //el personaje tiene una o mas fortalezas creadas
        for (int i = 0; i < t; i++) {//se recorre la lista y se muestran por pantalla
            Fortaleza actual = lista.get(i);
            Pantalla.imprimir(i + ". "+actual.getNombreFort());
        }
        Pantalla.imprimir(t + ". Cancelar operación.");
        int n = -1;
        while (n < 0 || n > t){
            n = Pantalla.pedirenteros("Elije una fortaleza");
        }
        if (n == t){//comprueba si en la lista solo hay una fortaleza
            return personaje;
        }
        if (personaje.getFortalezas().get(n) != null)//se vuelve a comprobar que la fortaleza escogida por el usuario exista y entonces se modifica
            personaje.getFortalezas().get(n).modificar();
        return personaje;
    }
}
    

