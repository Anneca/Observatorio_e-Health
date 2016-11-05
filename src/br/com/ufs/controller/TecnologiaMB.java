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
import br.com.ufs.observatorio.dao.TecnologiaDAO;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.model.Tecnologia;
import br.com.ufs.observatorio.util.JsonWrite;

@ManagedBean(name = "tecnologiaMB")
public class TecnologiaMB {

	TecnologiaDAO tecnologiaDAO = new TecnologiaDAO();
	ArrayList<Tecnologia> lista = new ArrayList<Tecnologia>();
	String listaJSON;
	String nomePais;
	Pais pais = new Pais();
	PaisDAO paisDAO = new PaisDAO();
	JsonWrite jsonWrite = new JsonWrite();
	String nomePaisRanking;
	String listaJsonTecnologias;
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
			// System.out.println(format);
			format = "13-10-2016";
			lista = tecnologiaDAO.consultarTecnologiasByPaisDataAtual(format, pais.getCodigo());
			listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJson(lista)));
			listaJsonTecnologias = "[{'colocacao':'','descricao':'','quantidade':''}]";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void preencherListaTecnologias(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String data = req.getParameter("calendario");
		data = "13-10-2016";

		try {
			pais = paisDAO.consultarPaisByNome(nomePaisRanking);
			lista = tecnologiaDAO.consultarTecnologiasByPaisData(data, pais.getCodigo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaJsonTecnologias = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonTecnologias(lista)));
		

	}
	
	
	// --------------------Getters And Setters------------
	
	
	public String getListaJSON() {
		return listaJSON;
	}

	public String getListaJsonTecnologias() {
		return listaJsonTecnologias;
	}

	public void setListaJsonTecnologias(String listaJsonTecnologias) {
		this.listaJsonTecnologias = listaJsonTecnologias;
	}

	public String getNomePaisRanking() {
		return nomePaisRanking;
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

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
	}

	public ArrayList<Tecnologia> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Tecnologia> lista) {
		this.lista = lista;
	}

}
