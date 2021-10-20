package com.example.petstore.pettype;

import com.example.petstore.pet.Pet;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Schema(name = "PetType")
@Entity
public class PetType {

	@Schema(required = true, description = "Pet id")
	@Id
	@GeneratedValue
	private Long id;

	@Schema(required = true, description = "Pet type name")
	private String typeName;

//	@ManyToMany
//	private List<Pet> pets;

	public PetType() {
	}

	public PetType(String typeName) {
		this.typeName = typeName;
	}

	public PetType(Long id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "PetType{" +
				"Id=" + id +
				", typeName='" + typeName + '\'' +
				'}';
	}
}
