package br.com.gv2c.painel.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.gv2c.ConnectDbFactory;
import br.com.gv2c.painel.vo.PainelVO;
import br.com.gv2c.painel.vo.ProcessoVO;
import br.com.gv2c.painel.vo.StatusVO;
import br.com.gv2c.util.FacesUtil;

@SessionScoped
@ManagedBean
public class PainelBean {

	SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");

	private ListagemBean listagemBean = new ListagemBean();
	
	private List<ProcessoVO> listaProcesso = new ArrayList<ProcessoVO>();
	
	private List<StatusVO> listaStatus = new ArrayList<StatusVO>();
	
	private Date data;

	private PainelVO painelSelecionado;
	
	private Integer idPainel;

	private Integer idStatus;

	private Integer idProcesso;

	private Integer idEtapa;

	private Integer sla;
	
	private StatusVO statusSelecionado;

	private ProcessoVO processoSelecionado;

	private String responsavel;
	
	private String descricao;
	
	private List<PainelVO> listaPainel = new ArrayList<PainelVO>();

	private PainelVO detalhePainel;

	private String modalMsg;

	@PostConstruct
	public void init() {
		this.clearFields();
	}			
	
	public void clearFields() {
		this.initCombos();
		
		Calendar aCalendar = Calendar.getInstance();
		this.data = aCalendar.getTime();
	}
	
	private void initCombos() {
		listaProcesso = listagemBean.listarContas();
		listaStatus = listagemBean.listarStatus();
//		painelSelecionado = new PainelVO();
	}
	
	public List<PainelVO> listarItensPainel() {
		listaPainel.clear();
		
		List<PainelVO> lista = new ArrayList<PainelVO>(); 
		
		Connection con = ConnectDbFactory.createConnection();

		Calendar calExtracaoFim = Calendar.getInstance();
		
		//
		Calendar calExtracaoIni = Calendar.getInstance();
		calExtracaoIni.set(Calendar.DAY_OF_MONTH, 0);
		calExtracaoIni.set(Calendar.HOUR_OF_DAY, 0);
		calExtracaoIni.set(Calendar.MINUTE, 0);
		calExtracaoIni.set(Calendar.SECOND, 0);
		calExtracaoIni.set(Calendar.MILLISECOND, 0);
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select pn.id, pn.dt_painel " + 
					",ps.cd_processo, ps.nm_processo " + 
					",pn.st_processo " + 
					",case when pn.st_processo = 1 then 'OK' else 'NOK' end as status " + 
					",pn.cd_etapa, pn.ds_usuario, pn.ds_mensagem, pn.sla " + 
					"from painel.painel pn " + 
					"inner join painel.processo ps on ps.cd_processo = pn.cd_processo order by pn.id desc");
			while (rs.next()) {
				calExtracaoFim.setTime(rs.getDate(2));
				PainelVO cx = new PainelVO();
				cx.setId(rs.getInt(1));
				cx.setData(rs.getDate(2));
				cx.setCdProcesso(rs.getInt(3));
				cx.setNmProcesso(rs.getString(4));
				cx.setStProcesso(rs.getInt(5));
				cx.setStatus(rs.getString(6));
				cx.setCdEtapa(rs.getInt(7));
				cx.setUsuario(rs.getString(8));
				cx.setDsMensagem(rs.getString(9));
				cx.setSla(rs.getInt(10));
				lista.add(cx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		listaPainel.addAll(lista);
		return lista;
	}

	
	public List<PainelVO> listarPainelDistinct() {
		listaPainel.clear();
		
		List<PainelVO> lista = new ArrayList<PainelVO>(); 
		
		Connection con = ConnectDbFactory.createConnection();

		Calendar calExtracaoFim = Calendar.getInstance();
		
		//
		Calendar calExtracaoIni = Calendar.getInstance();
		calExtracaoIni.set(Calendar.DAY_OF_MONTH, 0);
		calExtracaoIni.set(Calendar.HOUR_OF_DAY, 0);
		calExtracaoIni.set(Calendar.MINUTE, 0);
		calExtracaoIni.set(Calendar.SECOND, 0);
		calExtracaoIni.set(Calendar.MILLISECOND, 0);
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select distinct ps.nm_processo " + 
					"from painel.painel pn " + 
					"inner join painel.processo ps on ps.cd_processo = pn.cd_processo order by ps.nm_processo");
			while (rs.next()) {
//				calExtracaoFim.setTime(rs.getDate(2));
				PainelVO cx = new PainelVO();
				cx.setNmProcesso(rs.getString(1));
				lista.add(cx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		listaPainel.addAll(lista);
		return lista;
	}
	
	
	public PainelVO listarUltima(String processo) {
		System.out.println("*************listarUltima:" + processo);
		listaPainel.clear();
		
		Connection con = ConnectDbFactory.createConnection();

		Calendar calExtracaoFim = Calendar.getInstance();
		
		//
		Calendar calExtracaoIni = Calendar.getInstance();
		calExtracaoIni.set(Calendar.DAY_OF_MONTH, 0);
		calExtracaoIni.set(Calendar.HOUR_OF_DAY, 0);
		calExtracaoIni.set(Calendar.MINUTE, 0);
		calExtracaoIni.set(Calendar.SECOND, 0);
		calExtracaoIni.set(Calendar.MILLISECOND, 0);
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select pn.id, pn.dt_painel " + 
					",ps.cd_processo, ps.nm_processo " + 
					",pn.st_processo " + 
					",case when pn.st_processo = 1 then 'OK' else 'NOK' end as status " + 
					",pn.cd_etapa, pn.ds_usuario, pn.ds_mensagem, pn.sla " + 
					"from painel.painel pn " + 
					"inner join painel.processo ps on ps.cd_processo = pn.cd_processo " +
//					"where pn.cd_processo =  " +processo +
					"where ps.nm_processo =  '" +processo + "'"+
					"order by pn.id desc");
			if (rs.next()) {
				calExtracaoFim.setTime(rs.getDate(2));
				PainelVO cx = new PainelVO();
				cx.setId(rs.getInt(1));
				cx.setData(rs.getDate(2));
				cx.setCdProcesso(rs.getInt(3));
				cx.setNmProcesso(rs.getString(4));
				cx.setStProcesso(rs.getInt(5));
				cx.setStatus(rs.getString(6));
				cx.setCdEtapa(rs.getInt(7));
				cx.setUsuario(rs.getString(8));
				cx.setDsMensagem(rs.getString(9));
				cx.setSla(rs.getInt(10));
				
				return cx;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public int lancarPainel(Date data, int cdProcesso, int stProcesso, int cdEtapa, String responsavel, String descricao) throws SQLException {
		
		String dataFormatada = "now()";
		if(data != null) {
			dataFormatada = "'"+formatData.format(data)+"'";
		}

		Connection con = ConnectDbFactory.createConnection();
		Statement stmt = con.createStatement();
//		String sql = "INSERT INTO painel.painel (dt_painel, cd_processo, st_processo, cd_etapa, dt_atualizacao, ds_usuario, ds_mensagem, sla) " + 
//				" values("+dataFormatada+", "+cdProcesso+"," + stProcesso + "," +cdEtapa+", now(), '"+responsavel+"', '"+descricao+"' , " + sla +")";

		String sql = "INSERT INTO painel.painel (dt_painel, cd_processo, st_processo, cd_etapa, dt_atualizacao, ds_usuario, ds_mensagem, sla) " + 
				" values(now(), "+cdProcesso+"," + stProcesso + "," +cdEtapa+", now(), '"+responsavel+"', '"+descricao+"' , " + sla +")";

		System.out.println(sql);
		return stmt.executeUpdate(sql);

	}

	public int atualizarConta(Integer idPainel, Date data, Integer cdProcesso, Integer cdStatus, Integer cdEtapa, String responsavel, String descricao) throws SQLException {
		
		String dataFormatada = formatData.format(data);

		Connection con = ConnectDbFactory.createConnection();

		Statement stmt = con.createStatement();
		String sql = "update painel set data='"+dataFormatada+"', cd_processo="+cdProcesso+", st_processo="+cdStatus+", cd_etapa="+cdEtapa+", responsavel='"+responsavel+"', descricao='"+descricao+"' where id="+ idPainel;
		return stmt.executeUpdate(sql);

	}

	public int deletarPainel(Integer idPainel) throws SQLException {
		System.out.println("DELETAR id:" +idPainel);
		Connection con = ConnectDbFactory.createConnection();
		Statement stmt = con.createStatement();
		String sql = "delete from painel.painel where id="+ idPainel;
		return stmt.executeUpdate(sql);
		
	}	

	public void modalOBS(String texto){
		
//		editarLinha();
		
//		  Map<String,String> params = FacesContext.getExternalContext().getRequestParameterMap();
//		  String action = params.get("action");
		
		System.out.println("modalOBS:"+ texto);
		modalMsg = texto;
		
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.update("formModal:dlgOBS");
		context.execute("PF('dlgOBS').show();");
	}
	
	public String modalDetalhe(String texto){
		System.out.println("modalDetalhe:"+ texto);
		
		detalhePainel = listarUltima(texto);
		
//		editarLinha();
		
//		  Map<String,String> params = FacesContext.getExternalContext().getRequestParameterMap();
//		  String action = params.get("action");
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String datat = params.get("processoDetalhe");
		System.out.println("modalDetalhe***:" + datat);
	      
		modalMsg = texto;
		
//		RequestContext context = RequestContext.getCurrentInstance(); 
//		context.update("formModal:dlgOBS");
//		context.execute("PF('pm:listagem?transition=flip').show();");
        return "pm:listagem?transition=none";

	}

	
	public void salvar() {
		System.out.println(this.idPainel + "," + data + ","+ this.idProcesso + "," + this.idStatus + "," + this.sla + "," + this.idEtapa + "," + this.responsavel + "," + this.descricao);

		//Validacoes
		if(this.idProcesso== null) {
			FacesUtil.exibirMensagemInfo("Selecione um processo");
			return;
		}
		if(this.idStatus == null) {
			FacesUtil.exibirMensagemInfo("Informe o status");
			return;
		}
		if(this.idEtapa == null) {
			FacesUtil.exibirMensagemInfo("Informe a etapa");
			return;
		}

		if(painelSelecionado != null){
			this.idPainel=painelSelecionado.getId();
		}
//		if(formaPgtoSelecionado != null){
//			this.idFormaPgto=formaPgtoSelecionado.getIdFormaPgto();
//		}
		String dataFormatada = "";
		if(data != null) {
			dataFormatada = formatData.format(data);
		} else {
			dataFormatada = formatData.format(new Date());
		}
		try {
			if(this.idPainel == null) {
				lancarPainel(data, idProcesso, idProcesso, idEtapa, responsavel, descricao);
			} else {
				atualizarConta(idPainel, data, idProcesso, idStatus, idEtapa, responsavel, descricao);
			}
			FacesUtil.exibirMensagemInfo("Informacao registrada");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.exibeMensagemErro("Informacao nao registrada " + e.getLocalizedMessage(), e.getMessage());
	        return;
		}
		
		limparFormulario();
	}

	public void deletar() {
		System.out.println("DELETAR:" + painelSelecionado.getId() + ","+ painelSelecionado.getNmProcesso());
		
		if(painelSelecionado.getId() != null) {
			try {
				deletarPainel(painelSelecionado.getId());
				FacesUtil.exibirMensagemInfo("Registro excluido");
			} catch (SQLException e) {
				e.printStackTrace();
				FacesUtil.exibeMensagemErro("Registro nao excluido" + e.getLocalizedMessage());
		        return;
			}
		}
		limparFormulario();
	}

	public String caixa() {
		limparFormulario();
		return "caixa";
	}		
	public void limparFormulario() {
		this.idPainel=null;
//		this.data=null;
		Calendar aCalendar = Calendar.getInstance();
		this.data = aCalendar.getTime();
		this.idProcesso=null;
		this.idEtapa=null;
		this.idStatus=null;
		this.responsavel=null;
		this.descricao=null;
		this.sla=null;
		painelSelecionado=null;
		statusSelecionado=null;
	}
	
	public void editarLinha() {
		System.out.println("Editar " + painelSelecionado.getId() + ","+ painelSelecionado.getDsMensagem());
		
		this.idPainel=painelSelecionado.getId();
		this.data=painelSelecionado.getData();
		this.idProcesso=painelSelecionado.getCdProcesso();
		this.idEtapa=painelSelecionado.getCdEtapa();
		this.responsavel=painelSelecionado.getUsuario();
		this.descricao=painelSelecionado.getDsMensagem();
		
		processoSelecionado = listagemBean.findProcessoById(idProcesso);
		statusSelecionado = listagemBean.findStatusById(idStatus);
		
	}
	
    public List<ProcessoVO> completeProcesso(String query) {
        List<ProcessoVO> filteredContas = new ArrayList<ProcessoVO>();
         
        for (int i = 0; i < listaProcesso.size(); i++) {
            ProcessoVO vo = listaProcesso.get(i);
            if(vo.getNome().toLowerCase().contains(query.toLowerCase())) {
                filteredContas.add(vo);
            }
        }
         
        return filteredContas;
    }	

	public String mobStatus() {
		return "mstatus";
	}

	public String mobListagem() {
		return "mlistagem";
	}	

	public SimpleDateFormat getFormatData() {
		return formatData;
	}

	public void setFormatData(SimpleDateFormat formatData) {
		this.formatData = formatData;
	}

	public ListagemBean getListagemBean() {
		return listagemBean;
	}

	public void setListagemBean(ListagemBean listagemBean) {
		this.listagemBean = listagemBean;
	}

	public List<ProcessoVO> getListaProcesso() {
		return listaProcesso;
	}

	public void setListaProcesso(List<ProcessoVO> listaProcesso) {
		this.listaProcesso = listaProcesso;
	}

	public List<StatusVO> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<StatusVO> listaStatus) {
		this.listaStatus = listaStatus;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public PainelVO getPainelSelecionado() {
		return painelSelecionado;
	}

	public void setPainelSelecionado(PainelVO painelSelecionado) {
		this.painelSelecionado = painelSelecionado;
	}

	public Integer getIdPainel() {
		return idPainel;
	}

	public void setIdPainel(Integer idPainel) {
		this.idPainel = idPainel;
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Integer getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	public StatusVO getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(StatusVO statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public ProcessoVO getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(ProcessoVO processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<PainelVO> getListaPainel() {
		return listaPainel;
	}

	public void setListaPainel(List<PainelVO> listaPainel) {
		this.listaPainel = listaPainel;
	}

	public String getModalMsg() {
		return modalMsg;
	}

	public void setModalMsg(String modalMsg) {
		this.modalMsg = modalMsg;
	}

	public PainelVO getDetalhePainel() {
		return detalhePainel;
	}

	public void setDetalhePainel(PainelVO detalhePainel) {
		this.detalhePainel = detalhePainel;
	}

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	
}
