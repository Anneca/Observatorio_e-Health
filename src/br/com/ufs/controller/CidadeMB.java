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
	private String nome;
	private String capital;
	int codigo;

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

	public void alterarCidade() {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String descricao = req.getParameter("descricao");
		String capital = req.getParameter("capital");
		int id = Integer.parseInt(req.getParameter("codigo"));
		
		try {
			cidadeDAO.alterarCidade(descricao, capital, id);
			addMessage("Cidade Alterada");
		} catch (SQLException e) {
			addMessageError("Não foi possível cadastrar a cidade");
			e.printStackTrace();
		}
	}

	public void excluirCidade() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		int id = Integer.parseInt(req.getParameter("codigo"));
		
		try {
			cidadeDAO.excluirCidade(id);
			addMessage("Cidade excluída");
		} catch (SQLException e) {
			addMessageError("Não foi possível excluir a cidade");
			e.printStackTrace();
		}
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// ------------Getters and Setters---------

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
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
