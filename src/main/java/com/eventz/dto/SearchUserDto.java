package com.eventz.dto;

public class SearchUserDto {
	
	private String label;
	private String name;
	private String surname;
	private String image;


	public SearchUserDto(String label, String name, String surname, String image) {
		this.label = label;
		this.name = name;
		this.surname = surname;
		this.image = image;
	}

	public SearchUserDto() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
