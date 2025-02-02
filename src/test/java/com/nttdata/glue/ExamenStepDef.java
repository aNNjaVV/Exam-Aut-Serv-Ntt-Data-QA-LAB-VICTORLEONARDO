package com.nttdata.glue;

import com.nttdata.steps.ExamenStep;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;

public class ExamenStepDef {

    @Steps
    ExamenStep examenStep;

    @Given("El usuario tiene acceso al API de STORE {string}")
    public void usuarioTieneAcceso(String URL) {
        examenStep.getURL(URL);
    }

    @When("Creo un nuevo pedido con la siguiente informacion: {int}, petId {int}, quantity {int}, status {string}")
    public void crearOrden(int id, int petId, int quantity, String status) {
        examenStep.crearOrden(id, petId, quantity, status);
    }

    @Then("La respuesta debe tener un status code {int}")
    public void validarStatusCode(int statusCode) {
        examenStep.validateStatusCode(statusCode);
    }

    @And("Valida body del response: id {int}, petId {int}, quantity {int}, status {string}")
    public void validarBody(int id, int petId, int quantity, String status)  {
        examenStep.ValidarBody(id, petId, quantity, status);
    }

    @When("Consulto un pedido por orderId {int}")
    public void consultarOrden(int id) {
        examenStep.consultarOrden(id);
    }

}
