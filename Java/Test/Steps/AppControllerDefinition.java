package Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class AppControllerDefinition {

    private Appcontroller appController;

    @Given("^que inicio la aplicación$")
    public void inicioLaAplicacion() {
        appController = new Appcontroller();
    }

    @When("^registro un nuevo usuario con los siguientes datos:$")
    public void registroNuevoUsuario(DataTable datosUsuario) {
        List<Map<String, String>> listaUsuarios = datosUsuario.asMaps(String.class, String.class);
        for (Map<String, String> usuario : listaUsuarios) {
            ByteArrayInputStream in = new ByteArrayInputStream(
                (usuario.get("Nombre") + "\r\n" +
                 usuario.get("Nickname") + "\r\n" +
                 usuario.get("Contraseña") + "\r\n").getBytes());
            System.setIn(in);
            appController.registrarse();
        }
    }

    @Then("^el usuario debería estar registrado correctamente$")
    public void usuarioRegistradoCorrectamente() {
        // Aquí puedes añadir las aserciones necesarias para verificar que el usuario se ha registrado correctamente
    }

    @When("^intento iniciar sesión con un usuario no existente con los siguientes datos:$")
    public void iniciarSesionUsuarioNoExistente(DataTable datosInicioSesion) {
        List<Map<String, String>> listaDatos = datosInicioSesion.asMaps(String.class, String.class);
        for (Map<String, String> datos : listaDatos) {
            ByteArrayInputStream in = new ByteArrayInputStream(
                (datos.get("Usuario") + "\r\n" +
                 datos.get("Contraseña") + "\r\n").getBytes());
            System.setIn(in);
            appController.iniciarSesion();
        }
    }

    @Then("^debería recibir un mensaje de que el usuario no existe$")
    public void recibirMensajeUsuarioNoExiste() {
        // Aquí puedes añadir las aserciones necesarias para verificar el mensaje recibido
    }

    @When("^guardo los datos de los usuarios$")
    public void guardarDatosUsuarios() {
        try {
            appController.guardarDatos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("^debería poder cargar los mismos datos y que sean iguales$")
    public void cargarDatosIguales() {
        // Aquí puedes añadir las aserciones necesarias para verificar que los datos cargados son iguales a los guardados
    }
}

