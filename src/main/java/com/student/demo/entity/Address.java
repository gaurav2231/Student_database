package com.student.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "flat_no")
//	@NotBlank(message = "flatno is required")
	private String flatno;
	
//	@NotBlank(message = "street is required")
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
//	@NotBlank(message = "city is required")
	private String city;
	
	@Column(name = "state")
//	@NotBlank(message = "state is required")
	private String state;
	
	@Column(name = "country")
//	@NotBlank(message = "country is required")
	private String country;
	
	@Column(name = "pin_code")
//	@NotBlank(message = "pincode is required")
	private String pincode;

	@ManyToOne()
	@JsonBackReference
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlatno() {
		return flatno;
	}
	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Address(int id, @NotBlank(message = "flatno is required") String flatno,
			@NotBlank(message = "street is required") String street,
			@NotBlank(message = "city is required") String city, @NotBlank(message = "state is required") String state,
			@NotBlank(message = "country is required") String country,
			@NotBlank(message = "pincode is required") String pincode, Student student) {
		super();
		this.id = id;
		this.flatno = flatno;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.student = student;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Address() {
		super();
	}
	
	

}
