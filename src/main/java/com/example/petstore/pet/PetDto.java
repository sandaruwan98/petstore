package com.example.petstore.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema
public class PetDto {

    private Long id;

    @Schema(required = true, description = "Pet type id")
    private Long petTypeId;

    @Schema(required = true, description = "Pet name")
    private String petName;

    private Integer petAge;

    public PetDto() {
    }

    public PetDto(Long petTypeId, String petName, Integer petAge) {
        this.id = 0L;
        this.petTypeId = petTypeId;
        this.petName = petName;
        this.petAge = petAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Long petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }
}
