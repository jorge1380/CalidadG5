package Controladores;

import ORIGEN.Usuario;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppcontrollerTest {

    @Test
    void registrarse() {
        Appcontroller appController = new Appcontroller();
        try {
            //Se redirige la entrada estándar
            ByteArrayInputStream in = new ByteArrayInputStream("Antonio\r\nAntonioNick\r\nAntonioPass\r\n".getBytes());
            System.setIn(in);

            //Se llama a la función de registrarse
            appController.registrarse();

            //Se guardan y cargan los datos
            appController.guardarDatos();
            appController.cargarDatos();
            List<Usuario> usuarios = appController.getUsuarios();

            //Se comprueba que los datos del último usuario guardado coincidan con los introducidos
            Usuario usuario = usuarios.get(usuarios.size()-1);
            assertEquals(usuario.getNombre(),"Antonio"); //Se comprueba el nombre
            assertEquals(usuario.getNickname(),"AntonioNick"); //Se comprueba el nombre de usuario
            assertEquals(usuario.getContrasena(),"AntonioPass"); //Se comprueba la contraseña

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void iniciarSesion() {
        Appcontroller appController = new Appcontroller();

        //Se redirige la entrada estándar
        ByteArrayInputStream in = new ByteArrayInputStream("Mario\r\n1234\r\n".getBytes());
        System.setIn(in);

        //Se redirige la salida estándar
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {

            //Se llama al método para iniciar sesión
            appController.iniciarSesion();

            //Se verifica que la salida esperada coincide con la obtenida
            String out = "Usuario\r\nContraseña\r\nNo existe el usuario...\r\nRegresando al menu\r\n";
            String outRecibida = output.toString();
            assertEquals(out,outRecibida);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}