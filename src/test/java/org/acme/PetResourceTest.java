package org.acme;

import com.example.petstore.pet.PetDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void petGetALlEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);


    }

	@Test
    public void petGetIdEndpoint() {
		int petId = 4;
		given()
				.pathParam("petId", petId)
				.when().get("/v1/pets/{petId}")
				.then()
				.statusCode(200)
				.body(
						"id",equalTo(petId),
						"petAge",equalTo(5),
						"petName",equalTo("Tommy"),
						"petType.id",equalTo(1),
						"petType.typeName",equalTo("Dog")

				);


    }

    @Test
    public void petPostEndpoint() {
		given()
				.contentType(ContentType.JSON).body(new PetDto(1L,"Tom",5))
				.when().post("/v1/pets")
				.then()
				.statusCode(200)
				.body(
						"petAge",equalTo(5),
						"petName",equalTo("Tom"),
						"petType.id",equalTo(1),
						"petType.typeName",equalTo("Dog")
				);
    }

    @Test
    public void petPutEndpoint() {
		int petId = 4;

		given()
				.contentType(ContentType.JSON).body(new PetDto(2L,"Updated",10))
				.pathParam("petId",petId)
				.when().put("/v1/pets/{petId}")
				.then()
				.statusCode(200)
				.body(
						"id",equalTo(petId),
						"petAge",equalTo(10),
						"petName",equalTo("Updated"),
						"petType.id",equalTo(2),
						"petType.typeName",equalTo("Cat")
				).log().all();
    }

    @Test
    public void petDeleteEndpoint() {
		int petId = 5;
		given()
				.pathParam("petId",petId)
				.when().delete("/v1/pets/{petId}")
				.then()
				.statusCode(200)
				.body(
						is("Deleted pet with id "+petId)
				).log().all();
    }

}