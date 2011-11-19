package com.rais.manager.database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_teacher")
@Proxy(lazy = false)
public class Teacher {

	private int id;
	private User userRef;
	private List<GroupTeacher> groupTeacherList;

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

	public void setUserRef(User userRef) {
		this.userRef = userRef;
	}

	@OneToOne
	public User getUserRef() {
		return userRef;
	}

	// --------------------------------------------------------------------------------

	public void setGroupTeacherList(List<GroupTeacher> groupTeacherList) {
		this.groupTeacherList = groupTeacherList;
	}

	@OneToMany(mappedBy = "teacherRef", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<GroupTeacher> getGroupTeacherList() {
		return groupTeacherList;
	}

	// --------------------------------------------------------------------------------

}
