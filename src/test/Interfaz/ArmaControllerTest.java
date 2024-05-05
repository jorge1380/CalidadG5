package Interfaz;
import org.calidad.ORIGEN.Arma;
import org.calidad.Controladores.ArmaController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class ArmaControllerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testCrearArma() {
        // Simulamos entrada del usuario
        String input = "Espada\n1\n5\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutamos el método a probar
        ArmaController armaController = new ArmaController();
        Arma armaCreada = armaController.crearArma();

        // Verificamos salida por consola
        String expectedOutput = "Escribe el nombre del arma\n" +
                "Pulsa 1 para arma a 1 mano. Pulsa 2 para arma a 2 manos\n" +
                "valor Ataque\nvalor Defensa\n";
        assertEquals(normalize(expectedOutput), normalize(outputStream.toString()));

        // Verificamos valores del objeto creado
        assertEquals("Espada", armaCreada.getNombre());
        assertEquals(1, armaCreada.getEmpuñadura());
        assertEquals(5, armaCreada.getModificadorAtc());
        assertEquals(3, armaCreada.getModificadorDef());
    }

    // Función para normalizar los separadores de línea
    private String normalize(String text) {
        return text.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}

