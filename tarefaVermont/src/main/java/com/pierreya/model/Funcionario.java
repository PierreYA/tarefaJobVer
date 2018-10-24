package com.pierreya.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;;

@Entity
public class Funcionario {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cargo=" + cargo + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	@NotBlank
	private String nome;
	
	private Boolean sincronizado = false;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Cargo cargo;
	
	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cargo
	 */
	public Cargo getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the sincronizado
	 */
	public Boolean getSincronizado() {
		return sincronizado;
	}

	/**
	 * @param sincronizado the sincronizado to set
	 */
	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}
	
}
