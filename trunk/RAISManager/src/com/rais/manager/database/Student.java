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
@Table(name = "t_student")
@Proxy(lazy = false)
public class Student {

	private int id;
	private User userRef;
	private List<Poll> pollList;
	private List<PollStudent> pollStudentList;
	private List<GroupStudent> groupStudentList;

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

	public void setPollList(List<Poll> pollList) {
		this.pollList = pollList;
	}

	@OneToMany(mappedBy = "studentRef", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Poll> getPollList() {
		return pollList;
	}

	// --------------------------------------------------------------------------------

	public void setPollStudentList(List<PollStudent> pollStudentList) {
		this.pollStudentList = pollStudentList;
	}

	@OneToMany(mappedBy = "studentRef", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<PollStudent> getPollStudentList() {
		return pollStudentList;
	}

	// --------------------------------------------------------------------------------

	public void setGroupStudentList(List<GroupStudent> groupStudentList) {
		this.groupStudentList = groupStudentList;
	}

	@OneToMany(mappedBy = "studentRef", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<GroupStudent> getGroupStudentList() {
		return groupStudentList;
	}

	// --------------------------------------------------------------------------------

}
