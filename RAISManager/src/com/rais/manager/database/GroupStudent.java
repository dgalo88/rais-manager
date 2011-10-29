package com.rais.manager.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_groupstudent")
@Proxy(lazy = false)
public class GroupStudent {

	private int id;
	private Student studentRef;
	private Group groupRef;

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

	public void setStudentRef(Student studentRef) {
		this.studentRef = studentRef;
	}

	@ManyToOne
	public Student getStudentRef() {
		return studentRef;
	}

	// --------------------------------------------------------------------------------

	public void setGroupRef(Group groupRef) {
		this.groupRef = groupRef;
	}

	@ManyToOne
	public Group getGroupRef() {
		return groupRef;
	}

	// --------------------------------------------------------------------------------

}
