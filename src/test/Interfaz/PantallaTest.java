package Interfaz;
import org.calidad.Controladores.Pantalla;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
public class PantallaTest {

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
    public void testImprimir() {
        Pantalla.imprimir("Hola mundo");
        String expected = "Hola mundo\n";
        assertEquals(normalize(expected), normalize(outputStream.toString()));
    }

    @Test
    public void testPedirEnteros() {
        String input = "42\n"; // Añadimos el carácter de nueva línea
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        int resultado = Pantalla.pedirenteros("Ingrese un número:");
        assertEquals(42, resultado);
    }

    @Test
    public void testPedirCadena() {
        String input = "StringNombre\n"; // Añadimos el carácter de nueva línea
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String resultado = Pantalla.pedircadena("Ingrese su nombre:");
        assertEquals("StringNombre", resultado);
    }

    // Función para normalizar los separadores de línea
    private String normalize(String text) {
        return text.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}

