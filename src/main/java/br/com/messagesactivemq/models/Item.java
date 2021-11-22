package br.com.messagesactivemq.models;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private int quantidade;
	
	
	public Item(Long id, String name, int quantidade) {
		this.id = id;
		this.name = name;
		this.quantidade = quantidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
