package com.example.petstore;


import com.example.petstore.pet.Pet;
import com.example.petstore.pet.PetRepository;
import com.example.petstore.pettype.PetType;
import com.example.petstore.pettype.PetTypeRepository;
import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Startup
@Singleton
public class InitializerOnStart {

    @Inject
    PetRepository petRepository;

    @Inject
    PetTypeRepository petTypeRepository;

    @PostConstruct
    @Transactional
    public void onStart() {

            PetType petType1 = new PetType("Dog");
            PetType petType2 = new PetType("Cat");
            PetType petType3 = new PetType("Bird");
            petTypeRepository.persist(petType1,petType2,petType3);

            Pet p1 = new Pet(petType1,"Tommy",5);
            Pet p2 = new Pet(petType1,"Sam",7);
            Pet p3 = new Pet(petType2,"Kitty",4);
            petRepository.persist(p1,p2,p3);

    }



}
