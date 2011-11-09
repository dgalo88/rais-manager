package com.rais.manager.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_groupteacher")
@Proxy(lazy = false)
public class GroupTeacher {

	private int id;
	private Teacher teacherRef;
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

	public void setTeacherRef(Teacher teacherRef) {
		this.teacherRef = teacherRef;
	}

	@ManyToOne
	public Teacher getTeacherRef() {
		return teacherRef;
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
