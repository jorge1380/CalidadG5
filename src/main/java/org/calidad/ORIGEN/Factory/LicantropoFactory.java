package org.calidad.ORIGEN.Factory;

import org.calidad.Controladores.Pantalla;
import org.calidad.ORIGEN.Armadura;
import org.calidad.ORIGEN.Licantropo;
import org.calidad.ORIGEN.Arma;

import java.util.ArrayList;


public class LicantropoFactory extends PersonajeFactory{

    ArrayList<Arma> armasac = new ArrayList<>();
    ArrayList<Arma> armas = new ArrayList<>();

    ArrayList<Armadura> armaduras=  new ArrayList<Armadura>();

    @Override
    public Licantropo crearPersonaje() {
        Licantropo licantropo = new Licantropo();
        licantropo.setNombre(Pantalla.pedircadena("Elige nombre"));
        licantropo.setHabilidad(Pantalla.pedircadena("Elige nombre de don"));
        licantropo.setAtqHab(Pantalla.pedirenteros("Valor ataque"));
        licantropo.setDefHab(Pantalla.pedirenteros("Valor defensa"));
        licantropo.setCosteHabilidad(Pantalla.pedirenteros("Valor coste"));
        licantropo.setPoder(Pantalla.pedirenteros("Poder"));
        licantropo.setOro(500);
        licantropo.setSalud(5);
        licantropo.setRabia(0);
        Pantalla.imprimir("personaje creado");

        armas.add(armaDefault());//añadimos el arma default a la lista de armas disponible del personaje
        licantropo.setArmas(armas);
        armasac.add(armaDefault());//añadimos el arma default a la lista de armas activas que lleva el personaje
        licantropo.setArmasActivas(armasac);
        armaduras.add(armaduraDefault());
        licantropo.setArmadura(armaduras);//añadimos la armadura default a la lista de armaduras diponibles del personaje
        licantropo.setArmaduraActiva(armaduraDefault());//se selecciona la armadura default como la armadura activa del personaje
        return licantropo;
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
