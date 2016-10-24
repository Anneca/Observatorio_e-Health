package br.com.ufs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import br.com.ufs.observatorio.dao.CidadeDAO;
import br.com.ufs.observatorio.model.Cidade;
import br.com.ufs.observatorio.util.JsonWrite;

@ManagedBean(name = "cidadeMB")
public class CidadeMB {
	private List<Cidade> listaCidades;
	private ArrayList<Cidade> listaCidades2;
	CidadeDAO cidadeDAO = new CidadeDAO();
	String listaJSON;
	JsonWrite jsonWrite = new JsonWrite();


	@PostConstruct
	public void action() {
		try {
			listaCidades = cidadeDAO.consultarCidades();
			listaCidades2 = cidadeDAO.consultarCidades();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonCidade(listaCidades2)));

	}

	public void cadastrarCidade() {
		try {

			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			String nome = req.getParameter("nome");
			String capital = req.getParameter("capital");
			cidadeDAO.cadastrarCidade(nome, capital);
	        addMessage("Cidade Cadastrada");
			} catch (SQLException e) {
				addMessageError("Não foi possível cadastrar a cidade");
			e.printStackTrace();
		}
	}
	
	public void addMessageError(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


	// ------------Getters and Setters---------

	
	
	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public String getListaJSON() {
		return listaJSON;
	}

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

}
