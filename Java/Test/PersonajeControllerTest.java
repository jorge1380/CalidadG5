package Interfaz;
import Controladores.PersonajeController;
import ORIGEN.Personaje;
import ORIGEN.Usuario;
import ORIGEN.Vampiro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

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
    public void testRegistrarPersonaje() {
        PersonajeController personajeController = new PersonajeController();
        Usuario usuario = new Usuario();
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(inputStream);

        usuario = personajeController.registrarPersonaje(usuario);

        assertEquals("Seleccione una opción \n 1. Vampiro \n 2. Licántropo \n 3. Cazador \n 4. Cancelar \n ", outputStream.toString());
        assertEquals(Vampiro.class, usuario.getPersonaje().getClass());
        assertEquals(500, usuario.getPersonaje().getOro());
    }


    @Test
    public void testModificarPersonaje() {
        PersonajeController personajeController = new PersonajeController();
        Vampiro vampiro = new Vampiro(); // Crear un Vampiro ficticio para la prueba
        vampiro.setNombre("Dracula"); // Asignar un nombre al Vampiro ficticio
        InputStream inputStream = new ByteArrayInputStream("1 \n 1 \n Espada de Fuego \n 2 \n Fortaleza de Velocidad \n 7 \n".getBytes());
        System.setIn(inputStream);

        Personaje personajeModificado = personajeController.modificarPersonaje(vampiro);

        assertEquals("  1.Editar Arma o Armadura\n  2.Editar Fotalezas o Debilidades\n  3.Añadir Arma o Armadura\n  4.Añadir Fortaleza o Debilidad Arma\n  5.Añadir Esbirro\n  6.Modificar Estadisticas\n  7.Cancelar\n", outputStream.toString());
        assertEquals("Espada de Fuego", personajeModificado.getArmas().get(0).getNombre()); // Verificar que se haya agregado el arma correctamente
        assertEquals("Fortaleza de Velocidad", personajeModificado.getFortalezas().get(0).getNombreFort()); // Verificar que se haya agregado la fortaleza correctamente
    }
}