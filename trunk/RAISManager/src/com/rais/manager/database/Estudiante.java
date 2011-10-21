package com.rais.manager.database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_estudiante")
@Proxy(lazy = false)
public class Estudiante {

	private int id;
	private String alias;
	private String nombre;
	private String cedula;
	private String mail;
	private String password;
	private List<EvalEstudiante> listaEvalEstudiante;
	private List<Encuesta> listaEncuestas;
	private List<GrupoEstudiante> listaGrupoEstudiante;

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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	// --------------------------------------------------------------------------------

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	// --------------------------------------------------------------------------------

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedula() {
		return cedula;
	}

	// --------------------------------------------------------------------------------

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// --------------------------------------------------------------------------------

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// --------------------------------------------------------------------------------

	public void setListaEvalEstudiante(List<EvalEstudiante> listaEvalEstudiante) {
		this.listaEvalEstudiante = listaEvalEstudiante;
	}

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "estudianteRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<EvalEstudiante> getListaEvalEstudiante() {
		return listaEvalEstudiante;
	}

	// --------------------------------------------------------------------------------

	public void setListaEncuestas(List<Encuesta> listaEncuestas) {
		this.listaEncuestas = listaEncuestas;
	}

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "estudianteRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<Encuesta> getListaEncuestas() {
		return listaEncuestas;
	}

	// --------------------------------------------------------------------------------

	public void setListaGrupoEstudiante(List<GrupoEstudiante> listaGrupoEstudiante) {
		this.listaGrupoEstudiante = listaGrupoEstudiante;
	}

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "estudianteRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<GrupoEstudiante> getListaGrupoEstudiante() {
		return listaGrupoEstudiante;
	}

	// --------------------------------------------------------------------------------

}
