package org.calidad.ORIGEN.Factory;

import org.calidad.Controladores.Pantalla;
import org.calidad.ORIGEN.Armadura;
import org.calidad.ORIGEN.Cazador;
import  org.calidad.ORIGEN.Arma;

import java.util.ArrayList;


public class CazadorFactory extends PersonajeFactory{

    ArrayList<Arma> armasac = new ArrayList<>();
    ArrayList<Arma> armas = new ArrayList<>();

    ArrayList<Armadura> armaduras=  new ArrayList<Armadura>();

    @Override
    public Cazador crearPersonaje() {
        Cazador cazador = new Cazador();
        cazador.setNombre(Pantalla.pedircadena("Elige nombre"));
        cazador.setHabilidad(Pantalla.pedircadena("Elige nombre habilidad"));
        cazador.setAtqHab(Pantalla.pedirenteros("Valor ataque"));
        cazador.setDefHab(Pantalla.pedirenteros("Valor defensa"));
        cazador.setCosteHabilidad(Pantalla.pedirenteros("Valor coste"));
        cazador.setPoder(Pantalla.pedirenteros("Poder"));
        cazador.setOro(500);
        cazador.setSalud(5);
        cazador.setVoluntad(3);
        Pantalla.imprimir("personaje creado");
        armas.add(armaDefault());//añadimos el arma default a la lista de armas disponible del personaje
        cazador.setArmas(armas);
        armasac.add(armaDefault());//añadimos el arma default a la lista de armas activas que lleva el personaje
        cazador.setArmasActivas(armasac);
        armaduras.add(armaduraDefault());
        cazador.setArmadura(armaduras);//añadimos la armadura default a la lista de armaduras diponibles del personaje
        cazador.setArmaduraActiva(armaduraDefault());//se selecciona la armadura default como la armadura activa del personaje
        return cazador;
    }

    public Arma armaDefault(){//generamos un arma default para todos los personajes
        Arma arma = new Arma();
        arma.setNombre("palo");
        arma.setEmpuñadura(1);
        arma.setModificadorAtc(1);
        arma.setModificadorDef(0);
        return arma;
    }

    public Armadura armaduraDefault(){//generamos una armadura default para todos los personajes
        Armadura nueva = new Armadura();
        nueva.setNombre("cota de malla");
        nueva.setModificadorAtc(1);
        nueva.setModificadorDef(1);
        return nueva;
    }
}
