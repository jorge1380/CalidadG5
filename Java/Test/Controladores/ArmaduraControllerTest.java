package Controladores;

import ORIGEN.Armadura;
import ORIGEN.Personaje;
import ORIGEN.Vampiro;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmaduraControllerTest {

    private ArmaduraController armaduraController;
    private Personaje personaje;

    @Before
    public void setUp() {
        armaduraController = new ArmaduraController();
    }
    @Test
    public void testCrearArmadura() {
        // Simulamos entrada del usuario
        String input = "Armadura 1\n10\n20\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutamos el método a probar
        Armadura armadura = armaduraController.crearArmadura();

        // Verificamos valores del objeto creado
        assertEquals("Armadura 1", armadura.getNombre());
        assertEquals(10, armadura.getModificadorAtc());
        assertEquals(20, armadura.getModificadorDef());
    }
    @Test
    public void testCrearArmaduraInterfaz() {
        // Simulamos entrada del usuario
        String input = "Armadura 1\n10\n20\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Capturamos salida de la consola
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Ejecutamos el método a probar
        ArmaduraController armaduraController = new ArmaduraController();
        armaduraController.crearArmadura();

        // Verificamos salida por consola
        String expectedOutput = "Escribe el nombre del arma\n" +
                "valor Ataque\nvalor Defensa\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testIntegracionArmaduraController() {
        InputStream originalIn = System.in;
        try {
            // Simulamos entrada del usuario para crearArmadura()
            String inputCrear = "Armadura 1\n10\n20\n";
            InputStream inputStreamCrear = new ByteArrayInputStream(inputCrear.getBytes());
            System.setIn(inputStreamCrear);

            // Ejecutamos crearArmadura()
            ArmaduraController armaduraController = new ArmaduraController();
            Armadura armaduraCreada = armaduraController.crearArmadura();

            // Verificamos que la armadura se creó correctamente
            assertEquals("Armadura 1", armaduraCreada.getNombre());
            assertEquals(10, armaduraCreada.getModificadorAtc());
            assertEquals(20, armaduraCreada.getModificadorDef());

            // Creamos un personaje y le asignamos la armadura creada
            Personaje personaje = new Vampiro();
            personaje.getArmadura().add(armaduraCreada);

            // Restablecemos System.in a su flujo original
            System.setIn(originalIn);

            // Simulamos entrada del usuario para editarArmadura()
            String inputEditar = "0\nArmadura 2\n15\n25\n";
            InputStream inputStreamEditar = new ByteArrayInputStream(inputEditar.getBytes());
            System.setIn(inputStreamEditar);

            // Ejecutamos editarArmadura()
            armaduraController.editarArmadura(personaje);

            // Verificamos que la armadura se editó correctamente
            Armadura armaduraEditada = personaje.getArmadura().get(0);
            assertEquals("Armadura 2", armaduraEditada.getNombre());
            assertEquals(15, armaduraEditada.getModificadorAtc());
            assertEquals(25, armaduraEditada.getModificadorDef());

            // Restablecemos System.in a su flujo original
            System.setIn(originalIn);

            // Simulamos entrada del usuario para cambiarArmadura()
            String inputCambiar = "Armadura 2\n";
            InputStream inputStreamCambiar = new ByteArrayInputStream(inputCambiar.getBytes());
            System.setIn(inputStreamCambiar);

            // Ejecutamos cambiarArmadura()
            armaduraController.cambiarArmadura(personaje);

            // Verificamos que la armadura activa se cambió correctamente
            assertEquals("Armadura 2", personaje.getArmaduraActiva().getNombre());
        } finally {
            // Restablecemos System.in a su flujo original
            System.setIn(originalIn);
        }
    }




}





