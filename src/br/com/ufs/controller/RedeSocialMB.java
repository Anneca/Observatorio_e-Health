package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import br.com.ufs.observatorio.dao.PaisDAO;
import br.com.ufs.observatorio.dao.RedeSocialDAO;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.model.RedeSocial;
import br.com.ufs.observatorio.util.JsonWrite;

@ManagedBean(name = "redeSocialMB")
public class RedeSocialMB {

	RedeSocialDAO redeSocialDAO = new RedeSocialDAO();
	ArrayList<RedeSocial> lista = new ArrayList<RedeSocial>();
	String nomePais;
	Pais pais = new Pais();
	PaisDAO paisDAO = new PaisDAO();
	String listaJSON;
	JsonWrite jsonWrite = new JsonWrite();
	String nomePaisRanking;
	String listaJsonRedesSociais;
	Date data;

	@PostConstruct
	public void init() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pais");
		nomePais = valor;
		try {

			pais = paisDAO.consultarPaisByNome(nomePais);
			// Capturando a Data Atual
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String format = formatter.format(data);
			// format = "13-10-2016";
			lista = redeSocialDAO.consultarRedesSociaisByPaisDataAtual(format, pais.getCodigo());
			listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonRedeSocial(lista)));
			listaJsonRedesSociais = "[{'colocacao':'','descricao':'','quantidade':''}]";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void preencherListaRedesSociais() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String data = req.getParameter("calendario");
		// data = "13-10-2016";

		try {
			pais = paisDAO.consultarPaisByNome(nomePaisRanking);
			if (!data.equals("")) {
				lista = redeSocialDAO.consultarRedeSocialByPaisData(data, pais.getCodigo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaJsonRedesSociais = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonRedesSociais(lista)));

	}

	// -----------------------------------Getters And
	// Setters-----------------------------

	public ArrayList<RedeSocial> getLista() {
		return lista;
	}

	public String getNomePaisRanking() {
		return nomePaisRanking;
	}

	public String getListaJsonRedesSociais() {
		return listaJsonRedesSociais;
	}

	public void setListaJsonRedesSociais(String listaJsonRedesSociais) {
		this.listaJsonRedesSociais = listaJsonRedesSociais;
	}

	public void setNomePaisRanking(String nomePaisRanking) {
		this.nomePaisRanking = nomePaisRanking;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getListaJSON() {
		return listaJSON;
	}

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
	}

	public void setLista(ArrayList<RedeSocial> lista) {
		this.lista = lista;
	}

}
