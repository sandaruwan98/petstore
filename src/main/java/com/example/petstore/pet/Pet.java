package com.example.petstore.pet;

import com.example.petstore.pettype.PetType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Schema(name = "Pet")
public class Pet {
	@Id @GeneratedValue private Long id;

	private String petName;
	private Integer petAge;

	@ManyToOne
	private PetType petType;

	public Pet() {
	}

	public Pet(Long id, PetType petType, String petName, Integer petAge) {
		this.id = id;
		this.petType = petType;
		this.petName = petName;
		this.petAge = petAge;
	}

	public Pet(PetType petType, String petName, Integer petAge) {

		this.petType = petType;
		this.petName = petName;
		this.petAge = petAge;
	}

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public PetType getPetType() {
		return petType;
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

	@Override
	public String toString() {
		return "Pet{" +
				"petType='" + petType + '\'' +
				", petName='" + petName + '\'' +
				", petAge=" + petAge +
				", id=" + id +
				'}';
	}
}
