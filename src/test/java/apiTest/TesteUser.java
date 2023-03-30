// nome do pacote
package apiTest;

// Biblicotecas


import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteUser {   // inicio da classe
    // Atributos
    static String ct = "application/json"; // content type
    static String uriUser = "https://petstore.swagger.io/v2/user/";

    // Funções e Métodos
    // Funções de Apoio

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    // Funções e Métodos
    @Test @Order(1)
    public void testarIncluirUser() throws IOException {
        // Carregar os dados do nosso json
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");

        String userId = "1372566920";

        // realizar o teste
        given()                                         //Dado que
                .contentType(ct)                        // o tipo do conteúdo
                .log().all()                            // mostre tudo
                .body(jsonBody)                         // corpo da requisição
                .when()                                         // Quando
                .post(uriUser)                          // Endpoint / Onde
                .then()                                                                 // Então
                .log().all()                                                    // mostre tudo na volta
                .statusCode(200)                                             // Comunic. ida e volta ok
                .body("code", is(200))                                // tag code é 200
                .body("type", is("unknown"))                           // tag type é "unknown"
                .body("message", is(userId))                               // message ´o userId
        ;
    }// fim do post

    @Test @Order(2)
    public void testarConsultarUser() {
        String username = "Mari";

        // resultados esperados
        int userId = 1372566920;   // código do usuário
        String email = "mari@teste.com";
        String senha = "1234";
        String telefone = "11959998860";

        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriUser + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is(email))
                .body("password", is(senha))
                .body("phone", is(telefone))
        ;
    }

    @Test @Order(3)
    public void testarAlterarUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

        String userId = "1372566920";
        String username = "Mari";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .put(uriUser + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;

    }

    @Test @Order(4)
    public void testarExcluirUser() {
        String username = "Mari";

        given()
                .contentType(ct)
                .log().all()

                .when()
                .delete(uriUser + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username))
        ;
    }

    @Test @Order(5)
    public void testarLogin() {
        String username = "Mari";
        String password = "abcdee";

        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriUser + "login?username=" + username + "&password=" + password)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
                .extract();

        // Extração do token da resposta
        String token = resp.jsonPath().getString("message").substring(23);
        System.out.println("Conteudo do Token: " + token);
    }

    @ParameterizedTest @Order(6)
    @CsvFileSource(resources = "/csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void testarIncluirUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) { // incicio Incluir CSV
        // Carregar os dados do nosso json
        /*
        StringBuilder jsonBody = new StringBuilder("{");
            jsonBody.append("'id': " + id + ",");
            jsonBody.append("'username': "  + username + ",");
            jsonBody.append("'firstName': " + firstName + ",");
            jsonBody.append("'lastName': " + lastName + "," );
            jsonBody.append("'email': "  + email + "," );
            jsonBody.append("'password': " + password + ",");
            jsonBody.append("'phone': " + phone + ",");
            jsonBody.append("'userStatus': " + userStatus);
            jsonBody.append("}");
            */
        User user = new User(); // instancia a classe User

        user.id = id;
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.phone = phone;
        user.userStatus = userStatus;

        Gson gson = new Gson(); // instancia a classe Gson
        String jsonBody = gson.toJson(user);

        // realizar o teste
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .post(uriUser)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(id))
        ;
    }
}