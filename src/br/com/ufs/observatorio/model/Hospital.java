package br.com.ufs.observatorio.model;

public class Hospital {
	private int codigo;
	private String nome;
	private String site;
	Cidade cidade;
	Pais pais;
	private String tipoOrganizacao;
	private String dataAlteracao;
	private String possuiSite;
	
	
	//-----------------Gettes And Setters-------------
	
	
	public String getDataAlteracao() {
		return dataAlteracao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getPossuiSite() {
		return possuiSite;
	}
	public void setPossuiSite(String possuiSite) {
		this.possuiSite = possuiSite;
	}
	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getTipoOrganizacao() {
		return tipoOrganizacao;
	}
	public void setTipoOrganizacao(String tipoOrganizacao) {
		this.tipoOrganizacao = tipoOrganizacao;
	}
	
	

}
