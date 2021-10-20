package com.example.petstore;

import com.example.petstore.pettype.PetType;
import com.example.petstore.pettype.PetTypeRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusMain
public class Main implements QuarkusApplication {
    @Inject
    PetTypeRepository petTypeRepository;

    @Transactional
    @Override
    public int run(String... args) throws Exception {
        PetType petType1 = new PetType("Dog");
        PetType petType2 = new PetType("Cat");
        PetType petType3 = new PetType("Fish");
        petTypeRepository.persist(petType1,petType2,petType3);
        return 0;
    }
}
