package br.com.ufs.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.ufs.observatorio.dao.RedeSocialDAO;
import br.com.ufs.observatorio.model.RedeSocial;

@ManagedBean(name = "redeSocialMB")
public class RedeSocialMB {
	
	RedeSocialDAO redeSocialDAO = new RedeSocialDAO();
	ArrayList<RedeSocial> lista = new ArrayList<RedeSocial>();

	@PostConstruct
	public void init() {
		System.out.println("Entrou aqui");
		try {
			lista = redeSocialDAO.consultarRedesSociais();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//-----------------------------------Getters And Setters-----------------------------
	public ArrayList<RedeSocial> getLista() {
		return lista;
	}

	public void setLista(ArrayList<RedeSocial> lista) {
		this.lista = lista;
	}
	
	
}
