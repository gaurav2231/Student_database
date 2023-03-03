package com.student.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "firstname is required")
	@Column(name = "first_name")
	private String firstname;

	@NotBlank(message = "surname is required")
	@Column(name = "surname")
	private String surname;

	@NotBlank(message = "gender is required")
	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "phone_no")
	@NotBlank(message = "phone is required")
	private String phone;

	@Column(name = "aadhar_no")
	@NotBlank(message = "aadharno is required")
	private String aadharno;

	@Column(name = "image")
	private String image;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	@JsonManagedReference
	private List<Address> address;

	public Student() {
		super();
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAadharno() {
		return aadharno;
	}

	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}

	public Student(int id, @NotBlank(message = "firstname is required") String firstname,
			@NotBlank(message = "surname is required") String surname,
			@NotBlank(message = "gender is required") String gender, Date dob,
			@NotBlank(message = "phone is required") String phone,
			@NotBlank(message = "aadharno is required") String aadharno, String image, String img,
			List<Address> address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.aadharno = aadharno;
		this.image = image;
		this.address = address;
	}

}
