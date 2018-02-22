package br.com.gv2c.painel.bean;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gv2c.ConnectDbFactory;
import br.com.gv2c.painel.vo.StatusVO;
import br.com.gv2c.painel.vo.ProcessoVO;

@SessionScoped
@ManagedBean
public class ListagemBean {
	
	private String grade = "zerado";
	
	List<ProcessoVO> categoriaConta;
	
    @PostConstruct
    public void init() {
    	System.out.println("INIT.....");
    	categoriaConta = listarContas();
    	grade = "iniciado";
    }

	public List<ProcessoVO> listarContas() {
    	System.out.println("LISTAR CONTAS.....");
		
		List<ProcessoVO> lista = new ArrayList<ProcessoVO>();
		
		Connection con = ConnectDbFactory.createConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from painel.processo");
			while (rs.next()) {
				System.out.println(rs.getString(2));
				ProcessoVO vo = new ProcessoVO();
				vo.setId(rs.getLong(1));
				vo.setNome(rs.getString(2));
				vo.setData(rs.getDate(3));
				lista.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<ProcessoVO> listarProcessos() {
    	System.out.println("LISTAR CONTAS.....");
		
		List<ProcessoVO> lista = new ArrayList<ProcessoVO>();
		
		Connection con = ConnectDbFactory.createConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from painel.processo");
			while (rs.next()) {
				System.out.println(rs.getString(2));
				ProcessoVO vo = new ProcessoVO();
				vo.setId(rs.getLong(1));
				vo.setNome(rs.getString(2));
				vo.setData(rs.getDate(3));
				lista.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}


	public List<StatusVO> listarStatus() {
    	System.out.println("LISTAR STATUS.....");
		
		List<StatusVO> lista = new ArrayList<StatusVO>();
		StatusVO vo = new StatusVO();
		vo.setId(1);
		vo.setStatus("OK");
		lista.add(vo);

		vo = new StatusVO();
		vo.setId(2);
		vo.setStatus("NOK");
		lista.add(vo);

		return lista;
	}


	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public ProcessoVO findProcessoById(Integer idProcesso) {
		ProcessoVO vo = new ProcessoVO();
		Connection con = ConnectDbFactory.createConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from painel.processo where cd_processo = " + idProcesso);
			while (rs.next()) {
				vo.setId(rs.getLong(1));
				vo.setNome(rs.getString(2));
				vo.setData(rs.getDate(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public StatusVO findStatusById(Integer idStatus) {
		StatusVO vo = new StatusVO();
		
		if(idStatus == 1) {
			vo.setId(1);
			vo.setStatus("OK");
		} else {
			vo = new StatusVO();
			vo.setId(2);
			vo.setStatus("NOK");
		}
		
		return vo;
	}
	
	
}
