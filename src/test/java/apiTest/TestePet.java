package apiTest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class TestePet {
    static String ct = "application/json"; // content type
    static String uriPET = "https://petstore.swagger.io/v2/pet/";

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));

    }
    @Test
    public void testarIncluirPet() throws IOException {

        String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");

        int petId = 6920;

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriPET)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(petId))
                .body("name", is("Mag"))
                .body("tags.name[0]", is("vermifugado"))
                .body("status", is("ativo"))
        ;

    }
    @Test
    public void testarConsultarPet(){
        int petId = 6920;


        given()
                .contentType(ct)
                .log().all()

        .when()
                .get(uriPET + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(6920))
                .body("name", is("Mag"))
                .body("tags.name[0]", is("vermifugado"))
                .body("status", is(("ativo")))
        ;
    }

    @Test
    public void testarAlterarPet() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/pet2.json");
        int petId = 6920;

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriPET)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(6920))
                .body("name", is("Mag"))
                .body("tags.name[0]", is("castrado"))
                .body("status", is(("desativado")))
        ;
    }

    @Test
    public void testarExcluirPet(){

        int petId = 6920;

        given()
                .contentType(ct)
                .log().all()

        .when()
                .delete(uriPET + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is("6920"))
        ;

    }
}
