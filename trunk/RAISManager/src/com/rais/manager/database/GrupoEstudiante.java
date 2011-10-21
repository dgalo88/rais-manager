package com.rais.manager.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_grupoestudiante")
@Proxy(lazy = false)
public class GrupoEstudiante {

	private int id;
	private Estudiante estudianteRef;
	private Grupo grupoRef;

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

	public void setEstudianteRef(Estudiante estudianteRef) {
		this.estudianteRef = estudianteRef;
	}

	@ManyToOne
	public Estudiante getEstudianteRef() {
		return estudianteRef;
	}

	// --------------------------------------------------------------------------------

	public void setGrupoRef(Grupo grupoRef) {
		this.grupoRef = grupoRef;
	}

	@ManyToOne
	public Grupo getGrupoRef() {
		return grupoRef;
	}

	// --------------------------------------------------------------------------------

}
