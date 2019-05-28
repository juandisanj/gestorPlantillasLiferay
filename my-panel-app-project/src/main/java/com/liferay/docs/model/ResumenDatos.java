package com.liferay.docs.model;

public class ResumenDatos {
	
	private long idDato;
	private String nombreDato;
	private String scriptDatos;
	private String claseDato;	
	
	public ResumenDatos() {
	}
	
	public ResumenDatos(long idDato, String nombreDato, String scriptDatos, String claseDato) {
		super();
		this.idDato = idDato;
		this.nombreDato = nombreDato;
		this.scriptDatos = scriptDatos;
		this.claseDato = claseDato;
	}
	
	public long getIdDato() {
		return idDato;
	}
	public void setIdDato(long idDato) {
		this.idDato = idDato;
	}
	public String getNombreDato() {
		return nombreDato;
	}
	public void setNombreDato(String nombreDato) {
		this.nombreDato = nombreDato;
	}
	public String getScriptDatos() {
		return scriptDatos;
	}
	public void setScriptDatos(String scriptDatos) {
		this.scriptDatos = scriptDatos;
	}
	public String getClaseDato() {
		return claseDato;
	}
	public void setClaseDato(String claseDato) {
		this.claseDato = claseDato;
	}
	
	

}
