package com.example.petstore.pet;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {
    List<Pet> findPetsByAge(Integer age){
        return list("petAge",age);
    }

    Pet findByName(String name){
        return find("petName",name).firstResult();
    }

    List<Pet> findByPetType(String petTypeName){
        return list("petType.typeName",petTypeName);
    }

    List<Pet> findByPetType(Long petTypeId){
        return list("petType.id",petTypeId);
    }

}
