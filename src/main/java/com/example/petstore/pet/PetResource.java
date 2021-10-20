package com.example.petstore.pet;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

	@Inject
	PetRepository petRepository;

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> allpets = petRepository.listAll();
		return Response.ok(allpets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		PetDto pet = new PetDto();

		pet.setPetAge(3);
		pet.setPetName("Buula");
		pet.setPetType("Dog");

		return Response.ok(pet).build();
		
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Update pet for id",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404",description = "No Pet found for the id.") })
	@PUT
	@Path("{petId}")
	public Response updatePet(@PathParam("petId") int petId,Pet pet) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet newPet = new Pet();

		newPet.setPetAge(pet.getPetAge());
		newPet.setPetName(pet.getPetName());
		newPet.setPetType(pet.getPetType());

		return Response.ok(newPet).build();

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Add new pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to create a pet") })


	@POST
	@Transactional
	public Response addPet(Pet pet) {
		petRepository.persist(pet);
		return Response.ok(pet).build();

	}
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Delete pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to delete pet") })

	@DELETE
	@Path("{petId}")
	public Response deletePet(@PathParam("petId") int petId) {


		String res =  "Deleted pet with id "+petId;
		return Response.ok(res).build();

	}


}
