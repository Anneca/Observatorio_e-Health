package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
	PaisDAO paisDAO =new PaisDAO();
	String listaJSON;
	JsonWrite jsonWrite = new JsonWrite();

	@PostConstruct
	public void init() {
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap() .get("pais");
		nomePais = valor;
		try {
			
			pais = paisDAO.consultarPaisByNome(nomePais);
			// Capturando a Data Atual
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String format = formatter.format(data);
			format = "13-10-2016";
			lista = redeSocialDAO.consultarRedesSociaisByPaisDataAtual(format, pais.getCodigo());
			listaJSON = JSONArray.toJSONString((jsonWrite.gerarArquivoJsonRedeSocial(lista)));
			System.out.println(listaJSON);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//-----------------------------------Getters And Setters-----------------------------
	
	
	public ArrayList<RedeSocial> getLista() {
		return lista;
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
