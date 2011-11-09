package com.rais.manager.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_user")
@Proxy(lazy = false)
public class User {

	private int id;
	private String name;
	private String cedula;
	private String password;
	private Student studentRef;
	private Teacher teacherRef;

	// --------------------------------------------------------------------------------

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	// --------------------------------------------------------------------------------

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// --------------------------------------------------------------------------------

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedula() {
		return cedula;
	}

	// --------------------------------------------------------------------------------

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// --------------------------------------------------------------------------------

	public void setStudentRef(Student studentRef) {
		this.studentRef = studentRef;
	}

	@OneToOne
	public Student getStudentRef() {
		return studentRef;
	}

	// --------------------------------------------------------------------------------

	public void setTeacherRef(Teacher teacherRef) {
		this.teacherRef = teacherRef;
	}

	@OneToOne
	public Teacher getTeacherRef() {
		return teacherRef;
	}

	// --------------------------------------------------------------------------------

}
