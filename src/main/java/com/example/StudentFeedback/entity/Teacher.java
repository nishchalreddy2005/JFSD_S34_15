package com.example.StudentFeedback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher_table")
public class Teacher 
{
	@Id // primary key = unique + not null
	@Column(name = "teacher_id", length=20)
	private int id;
	@Column(name = "teacher_name", nullable = false, length=100)
	private String name;
	@Column(name = "teacher_email", nullable = false, unique = true, length=100)
	private String email;
	@Column(name = "teacher_password", nullable = false, length=50)
	private String password;
	@Column(name = "teacher_department", nullable = false, length=10)
	private String department;
	@Column(name = "teacher_qualification", nullable = false, length=10)
	private String qualification;
	@Column(name = "teacher_gender", nullable = false, length=10)
	private String gender;
	@Column(name = "teacher_contact", nullable = false, unique = true, length=20)
	private String contactno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	
}
