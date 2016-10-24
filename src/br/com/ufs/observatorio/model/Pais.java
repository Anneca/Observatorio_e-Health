package br.com.ufs.observatorio.model;

import java.util.Date;

public class Pais {

	int codigo;
	String descricao;
	String capital;
	String populacao;
	String IDH;
	String IDI;
	String PIB;
	String ultimaAlteracao;

	public String getIDH() {
		return IDH;
	}

	public void setIDH(String iDH) {
		IDH = iDH;
	}

	public String getIDI() {
		return IDI;
	}

	public void setIDI(String iDI) {
		IDI = iDI;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
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

}
