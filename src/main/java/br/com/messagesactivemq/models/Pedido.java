package br.com.messagesactivemq.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String doc;
	
	private List<Item> itens = new ArrayList<>();
	

	public Pedido(Long id, String doc, List<Item> itens) {
		this.id = id;
		this.doc = doc;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
