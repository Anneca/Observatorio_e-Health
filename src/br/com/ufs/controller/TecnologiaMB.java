package br.com.ufs.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.ufs.observatorio.dao.PaisDAO;
import br.com.ufs.observatorio.dao.TecnologiaDAO;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.model.Tecnologia;

@ManagedBean(name = "tecnologiaMB")
public class TecnologiaMB {
	
	TecnologiaDAO tecnologiaDAO = new TecnologiaDAO();
	ArrayList<Tecnologia> lista = new ArrayList<Tecnologia>();
	String listaJSON = "[{value: 700,color: '#f56954',highlight: '#f56954',label: 'Chrome'}]";
	String nomePais;
	public String getListaJSON() {
		return listaJSON;
	}

	public void setListaJSON(String listaJSON) {
		this.listaJSON = listaJSON;
	}

	Pais pais = new Pais();
	PaisDAO paisDAO =new PaisDAO();

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
//			System.out.println(format);
			format = "13-10-2016";
			lista = tecnologiaDAO.consultarTecnologiasByPaisDataAtual(format, pais.getCodigo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Tecnologia> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Tecnologia> lista) {
		this.lista = lista;
	}

}
