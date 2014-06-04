package com.caronapp.model;

import java.util.Date;

public class Carona {
	
	private long id;
	private String nome;
	private String motoristaFacebookId;
	private String origem;
	private String destino;
	private Date data;
	
	public Carona(){}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMotoristaFacebookId(String motoristaFacebookId) {
		this.motoristaFacebookId = motoristaFacebookId;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Carona(long id, String motoristaFacebookId,String nome, 
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

	@Override
	public String toString() {
		return "Carona [id=" + id + ", nome=" + nome + ", motoristaFacebookId="
				+ motoristaFacebookId + ", origem=" + origem + ", destino="
				+ destino + ", data=" + data + "]";
	}

	
}
