package com.nttdata.steps;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import java.time.Instant;
import java.util.Objects;

public class ExamenStep {

    private static String URL = null;
    private static String URL_EXAMEN = "https://petstore.swagger.io/v2/store/order";

    public void getURL(String url)
    {
        URL = url;
    }

    @Step("Creación de una ORDEN")
    public void crearOrden(int id, int petId , int quantity, String status){
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": \""+id+"\",\n" +
                        "  \"petId\": \""+petId+"\",\n" +
                        "  \"quantity\": \""+quantity+"\",\n" +
                        "  \"shipDate\": \""+ Instant.now().toString()+"\",\n" +
                        "  \"status\": \""+status+"\",\n" +
                        "  \"complete\": \""+true+"\"\n" +
                        "}")
                .log().all()
                .post(URL_EXAMEN)
                .then()
                .log().all();
    }

    public void consultarOrden(int id){

        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(URL+"/store/order/"+id)
                .then()
                .log().all();
    }

    public void validateStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, SerenityRest.lastResponse().getStatusCode());
    }

   /* public void ValidarBody(int id, int petId, int quantity, String status) {
        int responseId = SerenityRest.lastResponse().getBody().path("id");
        int responsePetId = SerenityRest.lastResponse().getBody().path("petId");
        int responseQuantity = SerenityRest.lastResponse().getBody().path("quantity");
        String responseStatus = SerenityRest.lastResponse().getBody().path("status");
        Assert.assertEquals(responseId, id);
        Assert.assertEquals(responsePetId, petId);
        Assert.assertEquals(responseQuantity, quantity);
        Assert.assertEquals(responseStatus, status);
    }*/

    public void ValidarBody(int id, int petId, int quantity, String status) {
        ValidatableResponse response = SerenityRest.lastResponse().then();
        String responseBody = SerenityRest.lastResponse().getBody().asString();

        int responseId = response.extract().path("id");
        int responsePetId = response.extract().path("petId");
        int responseQuantity = response.extract().path("quantity");
        String responseStatus = response.extract().path("status");
        Boolean responseComplete = response.extract().path("complete") != null ? response.extract().path("complete") : false;
        String responseShipDate = Objects.toString(response.extract().path("shipDate"), "No disponible");

        System.out.println("BODY del response es:" + responseBody);
        System.out.println("Validando la información del response:");
        System.out.println("Id esperado: " + id + " | Id recibido: " + responseId);
        System.out.println("PetId esperado: " + petId + " | PetId recibido: " + responsePetId);
        System.out.println("Quantity esperado: " + quantity + " | Quantity recibido: " + responseQuantity);
        System.out.println("ShipDate recibido: " + responseShipDate);
        System.out.println("Status esperado: " + status + " | Status recibido: " + responseStatus);
        System.out.println("Complete recibido: " + responseComplete);

        Assert.assertEquals("El Id no coincide", id, responseId);
        Assert.assertEquals("El petId no coincide", petId, responsePetId);
        Assert.assertEquals("El quantity no coincide", quantity, responseQuantity);
        Assert.assertEquals("El status no coincide", status, responseStatus);
    }

}
