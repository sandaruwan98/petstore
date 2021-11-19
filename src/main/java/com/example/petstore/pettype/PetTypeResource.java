package com.example.petstore.pettype;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/pettypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PetTypeResource {
    @Inject
    PetTypeRepository petTypeRepository;

    @GET
    public Response getPetTypes(){

        List<PetType> petTypes = petTypeRepository.listAll();
        return Response.ok(petTypes).build();
    }

    @GET
    @Path("{id}")
    public Response getPetTypeById(@PathParam("id") Long id){
        PetType petType = petTypeRepository.findById(id);
        return Response.ok(petType).build();
    }
    @POST
    @Transactional
    public Response AddPetType(PetType petType){

        petTypeRepository.persist(petType);
        return Response.ok(petType).build();
    }
    @PUT
    @Transactional
    public Response UpdatePetType( PetType updateData){
        petTypeRepository.update("typeName = ?2 where id = ?1",updateData.getId(),updateData.getTypeName());
        return Response.ok(updateData).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response DeletePetType(@PathParam("id") Long id){
        if( petTypeRepository.deleteById(id))
            return Response.ok("Deleted successfully").build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }




}
