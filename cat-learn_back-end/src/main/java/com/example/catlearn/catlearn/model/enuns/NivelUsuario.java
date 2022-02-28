/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.catlearn.catlearn.model.enuns;

/**
 *
 */
public enum NivelUsuario {

	MASTER("Master","M"),ADMIN("Administrador","A"),ASSOCIADO("Associado","S"),USUARIO("Usúario","N"),DIRETORIA("Diretoria","D"),STAFF("Staff","S");
    
    private String label;
    private String valor;

    private  NivelUsuario(String label,String valor) {
        this.label = label;
        this.valor = valor;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
    
}
