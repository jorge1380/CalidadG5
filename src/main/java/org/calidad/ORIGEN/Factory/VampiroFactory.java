package org.calidad.ORIGEN.Factory;

import java.util.ArrayList;
import java.util.List;

import org.calidad.Controladores.UsuarioController;
import org.calidad.Controladores.Pantalla;
import org.calidad.ORIGEN.Armadura;
import org.calidad.ORIGEN.Vampiro;
import org.calidad.ORIGEN.Arma;

public class VampiroFactory extends PersonajeFactory {
    ArrayList<Arma> armasac = new ArrayList<>();
    ArrayList<Arma> armas = new ArrayList<>();

    ArrayList<Armadura> armaduras=  new ArrayList<Armadura>();

    public Vampiro crearPersonaje() {
        Vampiro vampiro = new Vampiro();
        vampiro.setNombre(Pantalla.pedircadena("Elige nombre"));
        vampiro.setHabilidad(Pantalla.pedircadena("Elige nombre habilidad"));
        vampiro.setAtqHab(Pantalla.pedirenteros("Valor ataque"));
        vampiro.setDefHab(Pantalla.pedirenteros("Valor defensa"));
        vampiro.setCosteHabilidad(Pantalla.pedirenteros("Valor coste"));
        vampiro.setPoder(Pantalla.pedirenteros("Poder"));
        vampiro.setOro(500);
        vampiro.setSalud(5);
        vampiro.setEdad(Pantalla.pedirenteros("Edad vampiro"));
        vampiro.setPuntosSangre(Pantalla.pedirenteros("Puntos de sangre"));
        Pantalla.imprimir("Personaje creado con éxito.");
        armas.add(armaDefault());//añadimos el arma default a la lista de armas disponible del personaje
        vampiro.setArmas(armas);
        armasac.add(armaDefault());//añadimos el arma default a la lista de armas activas que lleva el personaje
        vampiro.setArmasActivas(armasac);
        armaduras.add(armaduraDefault());
        vampiro.setArmadura(armaduras);//añadimos la armadura default a la lista de armaduras diponibles del personaje
        vampiro.setArmaduraActiva(armaduraDefault());//se selecciona la armadura default como la armadura activa del personaje
        return vampiro;
    }

    public Arma armaDefault(){//generamos un arma default para todos los personajes
        Arma arma = new Arma();
        arma.setNombre("Palo");
        arma.setEmpuñadura(1);
        arma.setModificadorAtc(1);
        arma.setModificadorDef(0);
        return arma;
    }

    public Armadura armaduraDefault(){//generamos una armadura default para todos los personajes
        Armadura nueva = new Armadura();
        nueva.setNombre("Cota de malla");
        nueva.setModificadorAtc(1);
        nueva.setModificadorDef(1);
        return nueva;
    }
}
