package Controladores;


import ORIGEN.Fortaleza;
import ORIGEN.Personaje;
import ORIGEN.Vampiro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FortalezaControllerTest {

    private final InputStream originalSystemIn = System.in;
    private FortalezaController fortalezaController;
    private Personaje personaje;

    @Before
    public void setUp() {
        fortalezaController = new FortalezaController();
        personaje = new Vampiro();
    }

    @After
    public void restoreSystemInput() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testCrearFortaleza() {
        String nombreFortaleza = "Fortaleza 1";
        int sensibilidadFortaleza = 10;

        System.setIn(new ByteArrayInputStream(nombreFortaleza.getBytes()));
        String nombre = Pantalla.pedircadena("Nombre de la fortaleza: ");
        System.setIn(new ByteArrayInputStream(String.valueOf(sensibilidadFortaleza).getBytes()));
        int sensibilidad = Pantalla.pedirenteros("Valor de la fortaleza: ");

        Fortaleza fortaleza = fortalezaController.crearFortaleza();

        assertNotNull(fortaleza);
        assertEquals(nombreFortaleza, fortaleza.getNombreFort());
        assertEquals(sensibilidadFortaleza, fortaleza.getSensibilidadFort());
    }

    @Test
    public void testEditarFortaleza() {
        Fortaleza fortaleza1 = new Fortaleza();
        fortaleza1.setNombreFort("Fortaleza 1");

        ArrayList<Fortaleza> fortalezas = new ArrayList<>();
        fortalezas.add(fortaleza1);
        personaje.setFortalezas(fortalezas);

        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Personaje personajeModificado = fortalezaController.editarFortaleza(personaje);

        assertNotNull(personajeModificado);
        assertEquals(fortaleza1, personajeModificado.getFortalezas().get(0));
    }
}
