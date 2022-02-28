package com.example.catlearn.catlearn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.example.catlearn.catlearn.model.enuns.NivelUsuario;

@NamedQuery(name="Usuario.buscaTodos", query="select c from Usuario c")
@Entity
public class Usuario implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario; 
	@Column
	private String codigo;
	@Column
	private String usuario;
	@Column(length=100)
	private String nome ;
	@Column
	private String senha;
	@Transient
	private String novaSenha;
	@Transient
	private String confSenha;
	@Enumerated(EnumType.STRING)
    private NivelUsuario nivel;
	@Transient
	private boolean isAtivo;
	@Column(length=1)
	private String ativo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfSenha() {
		return confSenha;
	}
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public NivelUsuario getNivel() {
		return nivel;
	}
	public void setNivel(NivelUsuario nivel) {
		this.nivel = nivel;
	}
	public boolean isAtivo() {
		if("S".equals(ativo)){
			setAtivo(true);
		}
		return isAtivo;
	}
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
		ativo = isAtivo?"S":"N";
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public String toString() {
		return "[ " + usuario + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
