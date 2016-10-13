package br.com.ufs.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.ufs.observatorio.dao.TecnologiaDAO;
import br.com.ufs.observatorio.model.Tecnologia;

@ManagedBean(name = "tecnologiaMB")
public class TecnologiaMB {
	
	TecnologiaDAO tecnologiaDAO = new TecnologiaDAO();
	ArrayList<Tecnologia> lista = new ArrayList<Tecnologia>();

	@PostConstruct
	public void init() {
		System.out.println("Entrou aqui");
		try {
			lista = tecnologiaDAO.consultarTecnologias();
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
