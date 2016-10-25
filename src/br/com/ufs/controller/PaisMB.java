package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import br.com.ufs.observatorio.dao.PaisDAO;
import br.com.ufs.observatorio.model.DadosPais;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.util.JsonWrite;

@ManagedBean(name = "paisMB")
public class PaisMB {

	String teste = "39";
	private int qtSitesDisponiveis;
	private int qtHospitaisSemSite;
	private int qtHospitaisCatalogados;
	private int qtSitesForaDoAr;
	private int qtHospitaisComSite;
	private int qtSitesComServico;
	private int qtSitesComComentarios;
	private int qtSitesComCorpoClinico;
	private int qtSitesInformacaoInstitucional;
	private String pais = "Escolha um País";
	private String ano = "";
	private String propertyName1;
	private String propertyName2;
	private int hospitaisPublicos;
	private int hospitaisPrivados;
	private int hospitaisSemFinsLucrativos;
	private int hospitaisMisto;
	private int hospitaisUniversitarios;
	private int hospitaisNaoDefinidos;
	String listaJSON;
	JsonWrite jsonWrite = new JsonWrite();

	private List<Pais> listaPaises;
	private ArrayList<Pais> listaPaises2;


	Pais objPais = new Pais();

	PaisDAO paisDAO = new PaisDAO();
	
	public String alterarPais(){
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String descricao = req.getParameter("descricao");
		String capital = req.getParameter("capital");
		int id = Integer.parseInt(req.getParameter("codigo"));
		String populacao = req.getParameter("populacao");
		String idi = req.getParameter("idi");
		String idh = req.getParameter("idh");
		String pib = req.getParameter("pib");
		
		try {
			paisDAO.alterarPais(descricao, capital, id, populacao, idi, idh, pib);
			addMessage("Pais Alterado");
		} catch (SQLException e) {
			addMessageError("Não foi possível alterar o país");
			e.printStackTrace();
		}
		return "/AdminLTE-2.3.6/ConsultaPais?faces-redirect=true";

	}

	public void excluirPais(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		int id = Integer.parseInt(req.getParameter("codigo"));
		
		try {
			paisDAO.excluirPais(id);
			addMessage("Pais excluído");
		} catch (SQLException e) {
			addMessageError("Não foi possível excluir o país");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void action() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pais");
		pais = valor;
		try {

			listaPaises = paisDAO.consultarPais();
			listaPaises2 = paisDAO.consultarPais2();
			// Set objeto País
			objPais = paisDAO.consultarPaisByNome(pais);
			// Capturando a Data Atual
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String format = formatter.format(data);
			System.out.println(format);
			// Temporário
			format = "13-10-2016";
			// Capturando o objeto
			DadosPais objeto = paisDAO.consultarDadosPais(pais, format);
			qtHospitaisCatalogados = objeto.getQtHospitaisCatalogados();
			qtHospitaisComSite = objeto.getQtHospitaisComSite();
			qtSitesForaDoAr = objeto.getQtSitesForaDoAr();
			qtSitesComServico = objeto.getQtSitesComServico();
			qtSitesComComentarios = objeto.getQtSitesComComentarios();
			qtSitesComCorpoClinico = objeto.getQtSitesComCorpoClinico();
			qtSitesInformacaoInstitucional = objeto.getQtSitesInformacaoInstitucional();
			hospitaisPrivados = objeto.getHospitaisPrivados();
			hospitaisPublicos = objeto.getHospitaisPublicos();
			qtSitesDisponiveis = objeto.getQtSitesDisponiveis();
			qtHospitaisSemSite = objeto.getQtHospitaisSemSites();
			pais = objeto.getPais();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonPais(listaPaises2)));
		System.out.println(listaJSON);
	}

	public void preencherDadosPais() throws SQLException {
		objPais = paisDAO.consultarPaisByNome(pais);
		DadosPais objeto = paisDAO.consultarDadosPais(pais, ano);
		qtHospitaisCatalogados = objeto.getQtHospitaisCatalogados();
		qtHospitaisComSite = objeto.getQtHospitaisComSite();
		qtSitesForaDoAr = objeto.getQtSitesForaDoAr();
		qtSitesComServico = objeto.getQtSitesComServico();
		qtSitesComComentarios = objeto.getQtSitesComComentarios();
		qtSitesComCorpoClinico = objeto.getQtSitesComCorpoClinico();
		qtSitesInformacaoInstitucional = objeto.getQtSitesInformacaoInstitucional();
		hospitaisPrivados = objeto.getHospitaisPrivados();
		hospitaisPublicos = objeto.getHospitaisPublicos();
		hospitaisMisto = objeto.getHospitaisMisto();
		hospitaisNaoDefinidos = objeto.getHospitaisNaoDefinidos();
		hospitaisSemFinsLucrativos = objeto.getHospitaisSemFinsLucrativos();
		hospitaisUniversitarios = objeto.getHospitaisUniversitarios();
		pais = objeto.getPais();
	}

	public void cadastrarPais() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String descricao = req.getParameter("nome");
		String capital = req.getParameter("capital");
		String populacao = req.getParameter("populacao");
		String IDH = req.getParameter("IDH");
		String IDI = req.getParameter("IDI");
		String PIB = req.getParameter("PIB");
		Date ultimaAlteracao = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String format = formatter.format(ultimaAlteracao);

		try {
			paisDAO.cadastrarPais(descricao, capital, populacao, IDH, IDI, PIB, format);
			addMessage("País Cadastrado");
		} catch (SQLException e) {
			addMessageError("Não foi possível cadastrar o país");
			e.printStackTrace();
		}
	}

	public void addMessageError(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// -----------------------------------Getters and
	// Setters------------------------
	
	
	public String getTeste() {
		return teste;
	}

	public ArrayList<Pais> getListaPaises2() {
		return listaPaises2;
	}

	public void setListaPaises2(ArrayList<Pais> listaPaises2) {
		this.listaPaises2 = listaPaises2;
	}

	public String getListaJSON() {
		return listaJSON;
	}

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public int getQtSitesDisponiveis() {
		return qtSitesDisponiveis;
	}

	public void setQtSitesDisponiveis(int qtSitesDisponiveis) {
		this.qtSitesDisponiveis = qtSitesDisponiveis;
	}

	public int getQtHospitaisSemSite() {
		return qtHospitaisSemSite;
	}

	public void setQtHospitaisSemSite(int qtHospitaisSemSite) {
		this.qtHospitaisSemSite = qtHospitaisSemSite;
	}

	public Pais getObjPais() {
		return objPais;
	}

	public void setObjPais(Pais objPais) {
		this.objPais = objPais;
	}

	public int getHospitaisPublicos() {
		return hospitaisPublicos;
	}

	public void setHospitaisPublicos(int hospitaisPublicos) {
		this.hospitaisPublicos = hospitaisPublicos;
	}

	public int getHospitaisPrivados() {
		return hospitaisPrivados;
	}

	public void setHospitaisPrivados(int hospitaisPrivados) {
		this.hospitaisPrivados = hospitaisPrivados;
	}

	public String getPropertyName1() {
		return propertyName1;
	}

	public void setPropertyName1(String propertyName1) {
		this.propertyName1 = propertyName1;
	}

	public String getPropertyName2() {
		return propertyName2;
	}

	public void setPropertyName2(String propertyName2) {
		this.propertyName2 = propertyName2;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public int getQtHospitaisCatalogados() {
		return qtHospitaisCatalogados;
	}

	public void setQtHospitaisCatalogados(int qtHospitaisCatalogados) {
		this.qtHospitaisCatalogados = qtHospitaisCatalogados;
	}

	public int getQtSitesForaDoAr() {
		return qtSitesForaDoAr;
	}

	public void setQtSitesForaDoAr(int qtSitesForaDoAr) {
		this.qtSitesForaDoAr = qtSitesForaDoAr;
	}

	public int getQtHospitaisComSite() {
		return qtHospitaisComSite;
	}

	public void setQtHospitaisComSite(int qtHospitaisComSite) {
		this.qtHospitaisComSite = qtHospitaisComSite;
	}

	public int getQtSitesComServico() {
		return qtSitesComServico;
	}

	public void setQtSitesComServico(int qtSitesComServico) {
		this.qtSitesComServico = qtSitesComServico;
	}

	public int getQtSitesComComentarios() {
		return qtSitesComComentarios;
	}

	public void setQtSitesComComentarios(int qtSitesComComentarios) {
		this.qtSitesComComentarios = qtSitesComComentarios;
	}

	public int getQtSitesComCorpoClinico() {
		return qtSitesComCorpoClinico;
	}

	public void setQtSitesComCorpoClinico(int qtSitesComCorpoClinico) {
		this.qtSitesComCorpoClinico = qtSitesComCorpoClinico;
	}

	public int getQtSitesInformacaoInstitucional() {
		return qtSitesInformacaoInstitucional;
	}

	public void setQtSitesInformacaoInstitucional(int qtSitesInformacaoInstitucional) {
		this.qtSitesInformacaoInstitucional = qtSitesInformacaoInstitucional;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPais() {
		return pais;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public int getHospitaisSemFinsLucrativos() {
		return hospitaisSemFinsLucrativos;
	}

	public void setHospitaisSemFinsLucrativos(int hospitaisSemFinsLucrativos) {
		this.hospitaisSemFinsLucrativos = hospitaisSemFinsLucrativos;
	}

	public int getHospitaisMisto() {
		return hospitaisMisto;
	}

	public void setHospitaisMisto(int hospitaisMisto) {
		this.hospitaisMisto = hospitaisMisto;
	}

	public int getHospitaisUniversitarios() {
		return hospitaisUniversitarios;
	}

	public void setHospitaisUniversitarios(int hospitaisUniversitarios) {
		this.hospitaisUniversitarios = hospitaisUniversitarios;
	}

	public int getHospitaisNaoDefinidos() {
		return hospitaisNaoDefinidos;
	}

	public void setHospitaisNaoDefinidos(int hospitaisNaoDefinidos) {
		this.hospitaisNaoDefinidos = hospitaisNaoDefinidos;
	}

	public PaisDAO getPaisDAO() {
		return paisDAO;
	}

	public void setPaisDAO(PaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

}
