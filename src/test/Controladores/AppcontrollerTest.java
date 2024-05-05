package Controladores;

import org.calidad.ORIGEN.Operador;
import org.calidad.ORIGEN.Usuario;
import org.calidad.Controladores.Appcontroller;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
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

    @Test
    void guardarDatos(){
        Appcontroller appController = new Appcontroller();
        try {
            appController.guardarDatos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Usuario> usuarios = appController.getUsuarios();
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            File file = new File("listaUsuarios.dat");
            if (!file.exists()){
                file.createNewFile();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaUsuarios.dat"));
            Object aux = ois.readObject();
            while (aux!=null) {
                if (aux instanceof Operador)
                    lista.add((Operador) aux);
                else if (aux instanceof Usuario)
                    lista.add((Usuario) aux);
                aux = ois.readObject();
            }
            ois.close();
        }
        catch (EOFException e1)
        {
            //Fin del fichero.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (lista.size() == usuarios.size()){
            for (int i = 0; i < lista.size(); i++) {
                assertEquals(lista.get(i).getNombre(),usuarios.get(i).getNombre());
                assertEquals(lista.get(i).getContrasena(),usuarios.get(i).getContrasena());
                assertEquals(lista.get(i).getPersonaje(),usuarios.get(i).getPersonaje());
                assertEquals(lista.get(i).getNickname(),usuarios.get(i).getNickname());
                assertEquals(lista.get(i).isBaneado(),usuarios.get(i).isBaneado());
                assertEquals(lista.get(i).getDesafio(),usuarios.get(i).getDesafio());
            }
        }else{
            fail("Las listas no son iguales");
        }

    }


}