package com.example.StudentFeedback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_table")
public class Student 
{
	@Id // primary key = unique + not null
	@Column(name = "student_id", length=20)
	private String id;
	@Column(name = "student_name", nullable = false, length=100)
	private String name;
	@Column(name = "student_email", nullable = false, unique = true, length=100)
	private String email;
	@Column(name = "student_password", nullable = false, length=50)
	private String password;
	@Column(name = "student_department", nullable = false, length=10)
	private String department;
	@Column(name = "student_gender", nullable = false, length=10)
	private String gender;
	@Column(name = "student_dateofbirth", nullable = false, length=20)
	private String dateofbirth;
	@Column(name = "student_contact", nullable = false, unique = true, length=20)
	private String contactno;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	
}
