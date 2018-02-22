package br.com.gv2c.painel.vo;

import java.io.Serializable;

public class StatusVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5433773930583492263L;
	private Integer id;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
