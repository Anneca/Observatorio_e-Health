package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import br.com.ufs.observatorio.dao.HospitalDAO;
import br.com.ufs.observatorio.model.Hospital;
import br.com.ufs.observatorio.model.Tecnologia;
import br.com.ufs.observatorio.util.JsonWrite;

@ManagedBean(name = "hospitalMB")
public class HospitalMB {

	HospitalDAO hospitalDAO = new HospitalDAO();
	int pais;
	int cidade;
	boolean possuiSite;
	ArrayList<Hospital> lista = new ArrayList<Hospital>();
	String listaJSON;
	JsonWrite jsonWrite = new JsonWrite();

	@PostConstruct
	public void init() {
		try {
			lista = hospitalDAO.consultarHospitais();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonHospital(lista)));
	}

	public void alterarHospital(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String descricao = req.getParameter("nome");
		String possui_site = req.getParameter("site");
		String url = req.getParameter("url");
		int id = Integer.parseInt(req.getParameter("codigo"));
		
		try {
			hospitalDAO.alterarHospital(descricao, possui_site,url, id);
			addMessage("Hospital Alterado");
		} catch (SQLException e) {
			addMessageError("Não foi possível alterar o Hospital");
			e.printStackTrace();
		}
	}
	
	public void excluirHospital(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		int id = Integer.parseInt(req.getParameter("codigo"));
		
		try {
			hospitalDAO.excluirHospital(id);
			addMessage("Hospital excluído");
		} catch (SQLException e) {
			addMessageError("Não foi possível excluir o hospital");
			e.printStackTrace();
		}	}

	
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

	public ArrayList<Hospital> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Hospital> lista) {
		this.lista = lista;
	}

	public String getListaJSON() {
		return listaJSON;
	}

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
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
