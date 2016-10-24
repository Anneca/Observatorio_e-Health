package br.com.ufs.observatorio.model;

public class Cidade {
	private int codigo;
	private String descricao;
	private String capital;
	private String ultimaAlteracao;

	
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

}
