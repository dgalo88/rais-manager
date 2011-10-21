package com.rais.manager.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_evalestudiante")
@Proxy(lazy = false)
public class EvalEstudiante {

	private int id;
	private Encuesta encuestaRef;
	private Estudiante estudianteRef;

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

	public void setEncuestaRef(Encuesta encuestaRef) {
		this.encuestaRef = encuestaRef;
	}

	@ManyToOne
	public Encuesta getEncuestaRef() {
		return encuestaRef;
	}

	// --------------------------------------------------------------------------------

	public void setEstudianteRef(Estudiante estudianteRef) {
		this.estudianteRef = estudianteRef;
	}

	@ManyToOne
	public Estudiante getEstudianteRef() {
		return estudianteRef;
	}

	// --------------------------------------------------------------------------------

}
