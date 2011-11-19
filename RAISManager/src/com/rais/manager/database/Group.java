package com.rais.manager.database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_group")
@Proxy(lazy = false)
public class Group {

	private String name;
	private byte[] logo;
	private int id;
	private List<Poll> pollList;
	private List<GroupStudent> groupStudentList;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// --------------------------------------------------------------------------------

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public byte[] getLogo() {
		return logo;
	}

	// --------------------------------------------------------------------------------

	public void setPollList(List<Poll> pollList) {
		this.pollList = pollList;
	}

	@OneToMany(mappedBy = "groupRef", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Poll> getPollList() {
		return pollList;
	}

	// --------------------------------------------------------------------------------

	public void setGroupStudentList(List<GroupStudent> groupStudentList) {
		this.groupStudentList = groupStudentList;
	}

	@OneToMany(mappedBy = "groupRef", orphanRemoval = false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<GroupStudent> getGroupStudentList() {
		return groupStudentList;
	}

	// --------------------------------------------------------------------------------

	public void setGroupTeacherList(List<GroupTeacher> groupTeacherList) {
		this.groupTeacherList = groupTeacherList;
	}

	@OneToMany(mappedBy = "groupRef", orphanRemoval = false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<GroupTeacher> getGroupTeacherList() {
		return groupTeacherList;
	}

	// --------------------------------------------------------------------------------

}
