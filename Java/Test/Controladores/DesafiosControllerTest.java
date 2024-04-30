package Controladores;

import ORIGEN.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DesafiosControllerTest {

    @Test
    void iniciarDesafio() {

        //Configuración de datos de ejemplo
        Arma arma1 = new Arma();
        Arma arma2 = new Arma();
        arma1.setModificadorAtc(0);
        arma2.setModificadorAtc(0);
        List<Arma> armas1 = new ArrayList<>();
        List<Arma> armas2 = new ArrayList<>();
        armas1.add(arma1);
        armas2.add(arma2);
        Armadura armadura1 = new Armadura();
        Armadura armadura2 = new Armadura();
        armadura1.setModificadorDef(0);
        armadura2.setModificadorDef(0);

        Vampiro vampiro1 = new Vampiro();

        vampiro1.setNombre("Paco");
        vampiro1.setEdad(500);
        vampiro1.setPuntosSangre(10);
        vampiro1.setCosteHabilidad(3);
        vampiro1.setHabilidad("Transformación");
        vampiro1.setAtqHab(7);
        vampiro1.setDefHab(5);
        vampiro1.setPoder(20);
        vampiro1.setOro(1000);
        vampiro1.setSalud(500);
        vampiro1.setArmasActivas((ArrayList<Arma>) armas1);
        vampiro1.setArmaduraActiva(armadura1);

        Vampiro vampiro2 = new Vampiro();

        vampiro2.setNombre("Pepe");
        vampiro2.setEdad(100);
        vampiro2.setPuntosSangre(10);
        vampiro2.setCosteHabilidad(3);
        vampiro2.setHabilidad("Transformación");
        vampiro2.setAtqHab(7);
        vampiro2.setDefHab(5);
        vampiro2.setPoder(20);
        vampiro2.setOro(1000);
        vampiro2.setSalud(10);
        vampiro2.setArmasActivas((ArrayList<Arma>) armas2);
        vampiro2.setArmaduraActiva(armadura2);

        Usuario usuario1 = new Usuario();
        usuario1.setPersonaje(vampiro1);
        Usuario usuario2 = new Usuario();
        usuario2.setPersonaje(vampiro2);

        Desafio desafio = new Desafio(usuario1,usuario2,0);

        DesafiosController dsfController = new DesafiosController();
        try {
            //Se inicia el desafio
            Desafio dsfResultado = dsfController.iniciarDesafio(desafio,null);

            //Se comprueba que el ganador sea el esperado
            assertEquals(1,dsfResultado.getGanador());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}