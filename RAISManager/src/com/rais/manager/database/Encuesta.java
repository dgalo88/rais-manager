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
@Table(name = "t_encuesta")
@Proxy(lazy = false)
public class Encuesta {

	private int id;
	private Date fechaRecibida;
	private Date fechaEnviada;
	private List<EvalEstudiante> listaEvalEstudiante;
	private Grupo grupoRef;
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

	public void setFechaEnviada(Date fechaEnviada) {
		this.fechaEnviada = fechaEnviada;
	}

	public Date getFechaEnviada() {
		return fechaEnviada;
	}

	// --------------------------------------------------------------------------------

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	// --------------------------------------------------------------------------------

	public void setListaEvalEstudiante(List<EvalEstudiante> listaEvalEstudiante) {
		this.listaEvalEstudiante = listaEvalEstudiante;
	}

	@SuppressWarnings("deprecation")
	@OneToMany (mappedBy = "encuestaRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<EvalEstudiante> getListaEvalEstudiante() {
		return listaEvalEstudiante;
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

	public void setEstudianteRef(Estudiante estudianteRef) {
		this.estudianteRef = estudianteRef;
	}

	@ManyToOne
	public Estudiante getEstudianteRef() {
		return estudianteRef;
	}

	// --------------------------------------------------------------------------------

}
