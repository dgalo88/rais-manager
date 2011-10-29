package com.rais.manager.database;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_poll")
@Proxy(lazy = false)
public class Poll {

	private int id;
	private Date receivedDate;
	private Date sentDate;
	private List<EvalStudent> evalStudentList;
	private Group groupRef;
	private Student studentRef;

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

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getSentDate() {
		return sentDate;
	}

	// --------------------------------------------------------------------------------

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	// --------------------------------------------------------------------------------

	public void setEvalStudentList(List<EvalStudent> evalStudentList) {
		this.evalStudentList = evalStudentList;
	}

	@SuppressWarnings("deprecation")
	@OneToMany (mappedBy = "pollRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<EvalStudent> getEvalStudentList() {
		return evalStudentList;
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

	public void setStudentRef(Student studentRef) {
		this.studentRef = studentRef;
	}

	@ManyToOne
	public Student getStudentRef() {
		return studentRef;
	}

	// --------------------------------------------------------------------------------

}
