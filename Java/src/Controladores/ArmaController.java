package Controladores;

import java.util.ArrayList;
import java.util.List;

import ORIGEN.Arma;
import ORIGEN.Personaje;

public class ArmaController {
    public Arma crearArma() {
        Arma arma = new Arma();
        arma.setNombre(Pantalla.pedircadena("Escribe el nombre del arma"));
        while (arma.getNombre().equals("")) {
            arma.setNombre(Pantalla.pedircadena("No puede estar vacío: "));
        }
        arma.setEmpuñadura(Pantalla.pedirenteros("Pulsa 1 para arma a 1 mano. Pulsa 2 para arma a 2 manos"));
        arma.setModificadorAtc(Pantalla.pedirenteros("valor Ataque"));
        arma.setModificadorDef(Pantalla.pedirenteros("valor Defensa"));
        return arma;
    }

   public Personaje editarArma( Personaje pj){//operación exclusiva del operador que permite modificar las armas que los usuarios pueden utilizar
        List<Arma> lista = pj.getArmas();
        if (lista == null){
            Pantalla.imprimir("EL personaje no tiene Armas");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Pantalla.imprimir("El personaje no tiene armas");
            Pantalla.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Arma actual = lista.get(i);
            Pantalla.imprimir(i+".   "+actual.getNombre());
        }
        Pantalla.imprimir(t+". Cancelar operación.");
        int n = -1;
        while (n <0 || n > t){
            n = Pantalla.pedirenteros("Elije un arma");
        }
        if (n == t){
            return pj;
        }
        if (pj.getArmas().get(n)!=null)
            pj.getArmas().get(n).modificar();//cuando selecciona un arma llama al método modificar de la clase arma que se ocupa de pedir los nuevos datos y asignarlos al arma
        pj.setArmasActivas(null);
        return pj;
    }

     public Personaje cambiarArma(Personaje personaje){//permite a los usuarios cambiar el arma equipada.
        List<Arma> armasAc = personaje.getArmasActivas();
        Pantalla.imprimir("Arma equipada:  ");
        for(Arma a : armasAc){
            Pantalla.imprimir(a.getNombre());
        }

        Pantalla.imprimir(" ");
        Pantalla.imprimir("Armas dispopnibles");

        if (armasAc == null){//en caso de que no tenga ningún arma equipada
            armasAc = new ArrayList<>();
            List<Arma> disponibles = personaje.getArmas();
            for (Arma a: disponibles){
                Pantalla.imprimir(a.getNombre());// se muestra la lista de armas disponibles
            }
            String nueva = Pantalla.pedircadena("Seleciona el nuevo arma");
            for(Arma a : disponibles){
                if (a.getNombre().equals(nueva))
                    armasAc.add(a);
            }personaje.setArmasActivas((ArrayList<Arma>) armasAc);//se equipa el arma previamente seleccionada
        }else{//en caso de que tenga algún arma equipada
            List<Arma> disponibles = personaje.getArmas();
            for (Arma a : disponibles){
                Pantalla.imprimir(a.getNombre());}
            String nueva = Pantalla.pedircadena("Seleciona el nuevo arma");
            if (armasAc.size() == 1) {//comprueba el que el personaje solo tenga 1 arma equipada
                if (armasAc.get(0).getEmpuñadura() == 1) {//comprueba que el arma equipada sea de 1 mano para popder añadir otra arma
                    for (Arma b : disponibles) {
                        if (b.getNombre().equals(nueva) && b.getEmpuñadura() == 1)
                            armasAc.add(b);
                        else {//si el nuevo arma es de dos maos elimina la anterior y la reemplaza
                            Pantalla.imprimir("Se sustituira el arma vieja por la nueva");
                            armasAc.remove(0);
                            armasAc.add(b);
                        }
                    }
                }
            }
            else if (armasAc.size() == 2){//en caso de que tenga dos armas equipadas.
                Pantalla.imprimir("Se cambiara la primera arma equipada por la nueva si la opcion es válida");
                if (armasAc.get(0).getEmpuñadura() == 1) {
                    for (Arma b : disponibles) {
                        if (b.getNombre().equals(nueva) && b.getEmpuñadura() == 1) {// si tiene dos armas ya equipadas y elige equiparse una nueva de empuladura 1 mano, se sustituye el primer arma equipada al personaje
                            armasAc.remove(0);
                            armasAc.add(b);
                        }
                        else {
                            Pantalla.imprimir("Se sustituira el arma vieja por la nueva");//si el arma es de dos manos elimina las anteriores y las sustituye por la nueva
                            armasAc.remove(0);
                            armasAc.remove(1);
                            armasAc.add(b);
                        }
                    }
                }
            }
        }personaje.setArmasActivas((ArrayList<Arma>) armasAc);
        return personaje;
    }
}
