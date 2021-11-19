package org.acme;

import com.example.petstore.pet.PetDto;
import com.example.petstore.pettype.PetType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetTypeResourceTest {

	@Test
    public void petTypeGetALlEndpoint() {
        given()
          .when().get("/v1/pettypes")
          .then()
             .statusCode(200);
//             .body(hasItem(expected));


    }

	@Test
    public void petTypeGetIdEndpoint() {
		int petTypeId = 1;

		given()
				.pathParam("id", petTypeId)
				.when().get("/v1/pettypes/{id}")
				.then()
				.statusCode(200)
				.body(
						"id",equalTo(petTypeId),
						"typeName",equalTo("Dog")
				);
    }

    @Test
    public void petTypePostEndpoint() {
		given()
				.contentType(ContentType.JSON).body(new PetType("Fish"))
				.when().post("/v1/pettypes")
				.then()
				.statusCode(200)
				.body(
						"typeName",equalTo("Fish")
				).log().all();
    }

    @Test
    public void petTypePutEndpoint() {
		Long petTypeId = 1L;
		given()
				.contentType(ContentType.JSON).body(new PetType(petTypeId,"Updated"))
				.when().put("/v1/pettypes")
				.then()
				.statusCode(200)
				.body(
						"typeName",equalTo("Updated")
				).log().all();
    }

    @Test
    public void petTypeDeleteEndpoint() {
		int petTypeId = 3;
		given()
				.pathParam("id",petTypeId)
				.when().delete("/v1/pettypes/{id}")
				.then()
				.statusCode(200)
				.body(
						is("Deleted successfully")
				).log().all();
    }

}