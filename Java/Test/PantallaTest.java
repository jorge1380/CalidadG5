package Interfaz;
import Controladores.Pantalla;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class PantallaTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testImprimir() {
        Pantalla.imprimir("Hola mundo");
        assertEquals("Hola mundo \n", outputStream.toString());
    }

    @Test
    public void testPedirEnteros() {
        String input = "42 \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        int resultado = Pantalla.pedirenteros("Ingrese un número:");
        assertEquals(42, resultado);
    }

    @Test
    public void testPedirCadena() {
        String input = "StringNombre \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String resultado = Pantalla.pedircadena("Ingrese su nombre:");
        assertEquals("StringNombre", resultado);
    }
}
