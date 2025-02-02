package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import java.time.Instant;

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

    public void ValidarBody(int id, int petId, int quantity, String status) {
        int responseId = SerenityRest.lastResponse().getBody().path("id");
        int responsePetId = SerenityRest.lastResponse().getBody().path("petId");
        int responseQuantity = SerenityRest.lastResponse().getBody().path("quantity");
        String responseStatus = SerenityRest.lastResponse().getBody().path("status");
        Assert.assertEquals(responseId, id);
        Assert.assertEquals(responsePetId, petId);
        Assert.assertEquals(responseQuantity, quantity);
        Assert.assertEquals(responseStatus, status);
    }

}
