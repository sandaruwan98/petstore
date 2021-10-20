package com.example.petstore.pettype;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetTypeRepository  implements PanacheRepository<PetType> {
}
