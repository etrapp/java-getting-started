package br.com.gv2c.painel.vo;

import java.io.Serializable;
import java.util.Date;

public class PainelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094024804701546711L;
	private Integer id;
	private Date data;
	private Integer cdProcesso;
	private String nmProcesso;
	private Integer stProcesso;
	private String status;
	private Date dataAtualizacao;
	private String dsMensagem;
	private Integer cdEtapa;
	private String usuario;
	private Integer sla;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCdProcesso() {
		return cdProcesso;
	}
	public void setCdProcesso(Integer cdProcesso) {
		this.cdProcesso = cdProcesso;
	}
	public String getNmProcesso() {
		return nmProcesso;
	}
	public void setNmProcesso(String nmProcesso) {
		this.nmProcesso = nmProcesso;
	}
	public Integer getStProcesso() {
		return stProcesso;
	}
	public void setStProcesso(Integer stProcesso) {
		this.stProcesso = stProcesso;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getDsMensagem() {
		return dsMensagem;
	}
	public void setDsMensagem(String dsMensagem) {
		this.dsMensagem = dsMensagem;
	}
	public Integer getCdEtapa() {
		return cdEtapa;
	}
	public void setCdEtapa(Integer cdEtapa) {
		this.cdEtapa = cdEtapa;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getSla() {
		return sla;
	}
	public void setSla(Integer sla) {
		this.sla = sla;
	}


}
