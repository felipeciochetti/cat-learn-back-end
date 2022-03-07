package com.example.catlearn.catlearn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="pessoa")
@Data
public class Pessoa  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPessoa;
	@Column(length=50)
	private  String codigo ;
	@Column(length=100)
	private String nome ;
	@Column
	@Temporal(TemporalType.DATE)	
	private Date dtCadastro;
	@Column
	@Temporal(TemporalType.DATE)	
	private Date dtNascimento;
	@Column(length=20)
	private String cpf ;
	@Column(length=20)
	private String rg ;
	@Column(length=20)
	private String cnpj ;
	@Column(length=50)
	private String email;
	@Column(length=20)
	private String celular;
	@Column(length=20)
	private String telefone;
	@Column(length=100)
	private String cidade;
	@Column(length=30)
	private String referencia;
	
	@OneToMany(mappedBy = "course")
    Set<CustomerCourse> courses;

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Set<CustomerCourse> getCourses() {
		return courses;
	}

	public void setCourses(Set<CustomerCourse> courses) {
		this.courses = courses;
	}

		
	
	

}