package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.ufs.observatorio.dao.PaisDAO;
import br.com.ufs.observatorio.model.DadosPais;
import br.com.ufs.observatorio.model.Pais;

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
	private String ano = "ANO";
	private String propertyName1;
	private String propertyName2;
	private int hospitaisPublicos;
	private int hospitaisPrivados;
	Pais objPais = new Pais();

	PaisDAO paisDAO = new PaisDAO();

	@PostConstruct
	public void action() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pais");
		pais = valor;
		try {
			// Set objeto País
			objPais = paisDAO.consultarPaisByNome(pais);
			// Capturando a Data Atual
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String format = formatter.format(data);
			System.out.println(format);
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

	}

	public void preencherDadosPais() throws SQLException {

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
		pais = objeto.getPais();
	}

	// -----------------------------------Getters and
	// Setters------------------------
	
	

	public String getTeste() {
		return teste;
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

}
