package Controladores;

import java.util.List;

import ORIGEN.Armadura;
import ORIGEN.Personaje;

public class ArmaduraController {
    
     public Armadura crearArmadura(){//operacion exclusiva del operador que permite crear nuevas armaduras para los personajes de los jugadores
        Armadura armadura = new Armadura();
        armadura.setNombre(Pantalla.pedircadena("Escribe el nombre del arma"));
        while (armadura.getNombre().equals(""))
            armadura.setNombre(Pantalla.pedircadena("No puede estar vacío: "));
        armadura.setModificadorAtc(Pantalla.pedirenteros("valor Ataque"));
        armadura.setModificadorDef(Pantalla.pedirenteros("valor Defensa"));
        return armadura;

    }

    public Personaje editarArmadura(Personaje pj){//operación exclusiva del operador que permite modificar una armadura.
        List<Armadura> lista = pj.getArmadura();
        if (lista==null){
            Pantalla.imprimir("El personaje no tiene armaduras");
            Pantalla.imprimir("Volviendo...");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Pantalla.imprimir("El personaje no tiene armaduras");
            Pantalla.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Armadura actual = lista.get(i);
            if (actual==null)
                continue;
            Pantalla.imprimir(i+". "+actual.getNombre());
        }
        Pantalla.imprimir(t+". Cancelar operación.");
        int n = -1;
        while (n <0 || n > t){
            n = Pantalla.pedirenteros("Elije una armadura");
        }
        if (n==t){
            return pj;
        }
        if (pj.getArmadura().get(n)!=null)
            pj.getArmadura().get(n).modificar();// una vez seleccionada la armadura lla al metodo mof¡dificar de la clase armadura que actualiza la nueva informacion
        pj.setArmaduraActiva(null);
        return pj;
    }

    public Personaje cambiarArmadura(Personaje personaje){ //permite a los usuarios cambiar la armadura equipada
         Armadura armaduraActiva = personaje.getArmaduraActiva();
         Pantalla.imprimir("Armadura activa Actual: " + personaje.getArmaduraActiva().getNombre());

         Pantalla.imprimir("Lista de armaduras disponibles");//se muestran las armaduras disponibles.
         for ( Armadura a : personaje.getArmadura()){
             Pantalla.imprimir(a.getNombre());
         }
         String nueva = Pantalla.pedircadena("Selecciona la nueva armadura");
         for (Armadura a: personaje.getArmadura()){
             if (a.getNombre().equals(nueva))
                 personaje.setArmaduraActiva(a);
         }
        return personaje;

    }
}
