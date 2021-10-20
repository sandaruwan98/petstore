package com.example.petstore.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Schema(name = "Pet")
public class Pet {
	@Id @GeneratedValue private Long id;

	private String petType;
	private String petName;
	private Integer petAge;

	public Pet() {
	}

	public Pet(Long id, String petType, String petName, Integer petAge) {
		this.id = id;
		this.petType = petType;
		this.petName = petName;
		this.petAge = petAge;
	}

	public Pet(String petType, String petName, Integer petAge) {

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

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
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
