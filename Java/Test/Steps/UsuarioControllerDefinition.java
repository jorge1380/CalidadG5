package Steps;

import Controladores.Appcontroller;
import Controladores.DesafiosController;
import Controladores.UsuarioController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class UsuarioControllerDefinition {

    private Appcontroller appController;
    private UsuarioController usrController;
    private DesafiosController dsfControllers;

    @Given("^que inicio la aplicación$")
    public void inicioLaAplicacion() {
        appController = new Appcontroller();
        usrController = new UsuarioController();
        dsfControllers = new DesafiosController();
    }

    @Given("^hay usuarios registrados en la aplicación$")
    public void hayUsuariosRegistrados() {
        // Lógica para asegurar que haya usuarios registrados (si es necesario)
    }

    @When("^desafío a un usuario con el siguiente personaje:$")
    public void desafioAUsuarioConPersonaje(DataTable datosUsuario) {
        List<Map<String, String>> listaUsuarios = datosUsuario.asMaps(String.class, String.class);
        for (Map<String, String> usuario : listaUsuarios) {
            // Lógica para desafiar al usuario con el personaje especificado
            ByteArrayInputStream in = new ByteArrayInputStream(
                (usuario.get("Usuario") + "\r\n" +
                 usuario.get("Personaje") + "\r\n").getBytes());
            System.setIn(in);
            usrController.desafiar(usuario1,usuariosLista);
        }
    }

    @Then("^debería enviar un desafío al usuario correctamente$")
    public void desafioEnviadoCorrectamente() {
        // Lógica para verificar que el desafío fue enviado correctamente
        assertEquals(dsfControllers.getListaDesafio().get(dsfControllers.getListaDesafio().size()-1).getUserDos()
                    .getNickname(),"Roberto");
    }
}
