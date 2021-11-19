package com.example.petstore.pet;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.pettype.PetType;
import com.example.petstore.pettype.PetTypeRepository;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

	@Inject
	PetRepository petRepository;

	@Inject
	PetTypeRepository petTypeRepository;

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
	public Response getPet(@PathParam("petId") Long petId) {
		Pet pet = petRepository.findById(petId);
		if (pet == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pet).build();
	}

	@GET
	@Path("search/name/{petName}")
	public Response findPetByName(@PathParam("petName") String petName) {
		Pet pet = petRepository.findByName(petName);
		if (pet == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pet).build();
	}

	@GET
	@Path("search/age/{petAge}")
	public Response findPetByAge(@PathParam("petAge") Integer petAge) {
		List<Pet> petList = petRepository.findPetsByAge(petAge);
		return Response.ok(petList).build();
	}

	@GET
	@Path("search/petType/name/{petTypeName}")
	public Response findPetByType(@PathParam("petTypeName") String petTypeName) {
		List<Pet> petList = petRepository.findByPetType(petTypeName);
		return Response.ok(petList).build();
	}

	@GET
	@Path("search/petType/id/{petTypeId}")
	public Response findPetByTypeId(@PathParam("petTypeId") Long petTypeId) {
		List<Pet> petList = petRepository.findByPetType(petTypeId);
		return Response.ok(petList).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Update pet for id",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404",description = "No Pet found for the id.") })
	@PUT
	@Path("{petId}")
	@Transactional
	public Response updatePet(@PathParam("petId") Long petId,PetDto petDto) {

		Pet currentPet = petRepository.findById(petId);
		if (currentPet == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		currentPet.setPetAge(petDto.getPetAge());
		currentPet.setPetName(petDto.getPetName());
		PetType petType = petTypeRepository.findById(petDto.getPetTypeId());
		if (petType != null)
			currentPet.setPetType(petType);
		petRepository.persist(currentPet);
		return Response.ok(currentPet).build();

	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Add new pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to create a pet") })
	@POST
	@Transactional
	public Response addPet(PetDto petDto) {
		PetType petType = petTypeRepository.findById(petDto.getPetTypeId());
		if (petType == null)
			return Response.status(Status.BAD_REQUEST).build();
		Pet newPet = new Pet();
		newPet.setPetName(petDto.getPetName());
		newPet.setPetAge(petDto.getPetAge());
		newPet.setPetType(petType);

		petRepository.persist(newPet);
		return Response.ok(newPet).build();

	}
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Delete pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to delete pet") })

	@DELETE
	@Transactional
	@Path("{petId}")
	public Response deletePet(@PathParam("petId") Long petId) {
		 if(!petRepository.deleteById(petId))
			 return Response.status(Status.INTERNAL_SERVER_ERROR).build();

		String res =  "Deleted pet with id "+petId;
		return Response.ok(res).build();

	}


}
