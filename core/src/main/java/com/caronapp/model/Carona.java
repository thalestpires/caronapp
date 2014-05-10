package com.caronapp.model;

import java.util.Date;

public class Carona {
	
	private long id;
	private String nome;
	private String motoristaFacebookId;
	private String origem;
	private String destino;
	private Date data;
	
	public Carona(long id, String nome, String motoristaFacebookId,
			String origem, String destino, Date data) {
		super();
		this.id = id;
		this.nome = nome;
		this.motoristaFacebookId = motoristaFacebookId;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
	}
	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getMotoristaFacebookId() {
		return motoristaFacebookId;
	}
	public String getOrigem() {
		return origem;
	}
	public String getDestino() {
		return destino;
	}
	public Date getData() {
		return data;
	}
	
	
	
	
	
	

}