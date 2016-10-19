package br.com.ufs.observatorio.model;

public class DadosPais {

	private int qtHospitaisSemSites;
	private int qtSitesDisponiveis;
	private int qtHospitaisCatalogados;
	private int qtSitesForaDoAr;
	private int qtHospitaisComSite;
	private int qtSitesComServico;
	private int qtSitesComComentarios;
	private int qtSitesComCorpoClinico;
	private int qtSitesInformacaoInstitucional;
	private String Pais;
	private String data;
	private int hospitaisPrivados;
	private int hospitaisPublicos;

	// ----------------------------------------Getters and
	// Setters--------------------------------
	
	
	
	public int getQtHospitaisCatalogados() {
		return qtHospitaisCatalogados;
	}

	public int getQtHospitaisSemSites() {
		return qtHospitaisSemSites;
	}

	public void setQtHospitaisSemSites(int qtHospitaisSemSites) {
		this.qtHospitaisSemSites = qtHospitaisSemSites;
	}

	public int getQtSitesDisponiveis() {
		return qtSitesDisponiveis;
	}

	public void setQtSitesDisponiveis(int qtSitesDisponiveis) {
		this.qtSitesDisponiveis = qtSitesDisponiveis;
	}

	public int getHospitaisPrivados() {
		return hospitaisPrivados;
	}

	public void setHospitaisPrivados(int hospitaisPrivados) {
		this.hospitaisPrivados = hospitaisPrivados;
	}

	public int getHospitaisPublicos() {
		return hospitaisPublicos;
	}

	public void setHospitaisPublicos(int hospitaisPublicos) {
		this.hospitaisPublicos = hospitaisPublicos;
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

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
