package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer_table")
@SequenceGenerator(name = "generator1", sequenceName = "gen1", initialValue = 1000)
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator1")
	@Column(name="customer_id")
	private long customerId;
	
	@Column(name="first_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="firstName must contain atleast 3 characters")
	private String firstName;
	
	@Column(name="last_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="lastName must contain atleast 3 characters")
	private String lastName;
	
	@Column(name="phone_number")
	@NotEmpty
	@Size(min=10 ,max=10, message="phoneNumber must contain  10 digits")
	private String phoneNumber;
	
	@Column(name="address",length=100)
	@NotEmpty
	@Size(min=3 , message="Address must contain atleast 3 characters")
    private String address;
	
	@Column(name="zip_code")
	@NotEmpty
	@Size(min=6 ,max=6, message="zipCode must contain 6 digits")
	private String zipCode;
	
	@Column(name="email_id",unique=true,length=30)
	@NotEmpty
	@Email(message="Email  is not valid!")
	public String emailID;
	
	@Column(name="password",length=20)
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	public String password;

	
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", zipCode=" + zipCode + ", emailID="
				+ emailID + ", password=" + password + "]";
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

	
}

