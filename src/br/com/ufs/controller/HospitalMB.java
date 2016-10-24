package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.ufs.observatorio.dao.HospitalDAO;

@ManagedBean(name = "hospitalMB")
public class HospitalMB {

	HospitalDAO hospitalDAO = new HospitalDAO();
	int pais;
	int cidade;
	boolean possuiSite;

	public void cadastrarHospital() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String nome = req.getParameter("nome");
		String tipoOrganizacao = req.getParameter("demo-category");
		String site = "http://www.linkquebrado.com/";
		String temSite = "Não";
		if (possuiSite) {
			site = req.getParameter("url");
			temSite = "Sim";
		}

		try {
			hospitalDAO.cadastrarHospital(nome, site, cidade, pais, tipoOrganizacao, temSite);
			addMessage("Hospital Cadastrado");
		} catch (SQLException e) {
			addMessageError("Não foi possível cadastrar o hospital");
			e.printStackTrace();
		}
	}

	public boolean getPossuiSite() {
		return possuiSite;
	}

	public void setPossuiSite(boolean possuiSite) {
		this.possuiSite = possuiSite;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public void addMessageError(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}

}
