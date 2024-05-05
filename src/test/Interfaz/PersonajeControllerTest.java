package Interfaz;
import org.calidad.Controladores.PersonajeController;
import org.calidad.ORIGEN.Personaje;
import org.calidad.ORIGEN.Usuario;
import org.calidad.ORIGEN.Vampiro;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
public class PersonajeControllerTest {

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
    public void testModificarPersonaje() {
        PersonajeController personajeController = new PersonajeController();
        Vampiro vampiro = new Vampiro(); // Crear un Vampiro ficticio para la prueba
        vampiro.setNombre("Dracula"); // Asignar un nombre al Vampiro ficticio
        InputStream inputStream = new ByteArrayInputStream("1 \n 1 \n Espada de Fuego \n 2 \n Fortaleza de Velocidad \n 7 \n".getBytes());
        System.setIn(inputStream);

        Personaje personajeModificado = personajeController.modificarPersonaje(vampiro);

        assertEquals(normalize("  1.Editar Arma o Armadura\n  2.Editar Fotalezas o Debilidades\n  3.Añadir Arma o Armadura\n  4.Añadir Fortaleza o Debilidad Arma\n  5.Añadir Esbirro\n  " +
                "6.Modificar Estadisticas\n  7.Cancelar\nElige Opcion\n  1.Editar Arma\n  2.Editar Armadura\n  Otro.Cancelar\nElige Opcion\nEL personaje no tiene Armas\n "), normalize(outputStream.toString()));
        assertEquals("Espada de Fuego", personajeModificado.getArmas().get(0).getNombre()); // Verificar que se haya agregado el arma correctamente
        assertEquals("Fortaleza de Velocidad", personajeModificado.getFortalezas().get(0).getNombreFort()); // Verificar que se haya agregado la fortaleza correctamente
    }

    // Función para normalizar los separadores de línea
    private String normalize(String text) {
        return text.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}
