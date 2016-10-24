package br.com.ufs.controller;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.ufs.observatorio.dao.UsuarioDAO;
import br.com.ufs.observatorio.dao.UsuarioPerfilDAO;
import br.com.ufs.observatorio.model.Usuario;

@ManagedBean(name = "usuarioMB")
public class UsuarioMB {
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	UsuarioPerfilDAO usuarioPerfilDAO = new UsuarioPerfilDAO();
	String nome;
	String email;
	String senha;
	String codigo;

	public void cadastrarUsuario() {

		if (codigo.equals("COOR123")) {
			System.out.println("Anne");
			try {
				usuarioDAO.cadastrarUsuario(nome, email, senha);
				Usuario obj = usuarioDAO.consultarUsuario(email, senha);
				// Perfil coordenador 1
				usuarioPerfilDAO.cadastrarUsuarioPerfil(obj.getCodigo(), 1);
				addMessage("Usuário Cadastrado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				addMessageError("Não foi possível realizar o seu cadastro");
			}

		} else {
			if (codigo.equals("PESQ123")) {
				try {
					usuarioDAO.cadastrarUsuario(nome, email, senha);
					Usuario obj = usuarioDAO.consultarUsuario(email, senha);
					// Perfil pesquisador 2
					usuarioPerfilDAO.cadastrarUsuarioPerfil(obj.getCodigo(), 2);
					addMessage("Usuário Cadastrado");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					addMessageError("Não foi possível realizar o seu cadastro");
				}
			} else {
				addMessageError("Código inserido não é válido");
			}
		}
	}

	public void login() {

		System.out.println("Anne");
		try {
			if (usuarioDAO.login(email, senha)) {
				addMessage("Você está logado");
			} else {
				addMessageError("Não foi possível realizar o login");

			}

		} catch (SQLException e) {
			addMessageError("Não foi possível realizar o login");
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}