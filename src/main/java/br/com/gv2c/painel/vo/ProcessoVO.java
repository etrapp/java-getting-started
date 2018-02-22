package br.com.gv2c.painel.vo;

import java.sql.Date;

public class ProcessoVO {

	private Long id;
	private String nome;
	private Date data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
