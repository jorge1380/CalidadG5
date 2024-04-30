package Controladores;

import ORIGEN.Desafio;
import ORIGEN.Personaje;
import ORIGEN.Usuario;
import ORIGEN.Vampiro;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    @Test
    void desafiar() {

        Appcontroller appController = new Appcontroller();
        DesafiosController dsfControllers = new DesafiosController();
        List<Usuario> usuariosLista = appController.getUsuarios();

        //Se crean los datos de ejemplos
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuario2.setNickname("Roberto");
        usuario2.setPersonaje(new Vampiro());
        Vampiro vamp = new Vampiro();
        vamp.setOro(200);
        usuario1.setPersonaje(vamp);
        usuariosLista.add(usuario2);

        //Se redirige la entrada estándar
        ByteArrayInputStream in = new ByteArrayInputStream("Roberto\r\n1".getBytes());
        System.setIn(in);

        UsuarioController usrController = new UsuarioController();
        try {
            usrController.desafiar(usuario1,usuariosLista);

            //Se comprueba que el desafío envíado haya sido guardado
            assertEquals(dsfControllers.getListaDesafio().get(dsfControllers.getListaDesafio().size()-1).getUserDos()
                    .getNickname(),"Roberto");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}