package Steps;

import Controladores.DesafiosController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class DesafiosControllerDefinition {

    private DesafiosController desafiosController;

    @Given("^que inicio la aplicación$")
    public void inicioLaAplicacion() {
        desafiosController = new DesafiosController();
    }

    @When("^inicio un desafío entre los siguientes usuarios:$")
    public void inicioDesafio(DataTable datosUsuarios) {
        // Lógica para iniciar un desafío con los datos proporcionados en la tabla
    }

    @Then("^debería obtener el resultado esperado del desafío$")
    public void obtenerResultadoEsperado() {
        // Lógica para verificar el resultado esperado del desafío
    }

    @When("^guardo un desafío y lo cargo nuevamente$")
    public void guardarYcargarDesafio() {
        // Lógica para guardar un desafío y cargarlo nuevamente
    }

    @Then("^debería obtener los mismos desafíos guardados$")
    public void obtenerMismosDesafiosGuardados() {
        // Lógica para verificar que los desafíos guardados son los mismos que los cargados
    }
}
