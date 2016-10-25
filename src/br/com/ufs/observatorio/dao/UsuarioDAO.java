package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ufs.observatorio.model.Cidade;
import br.com.ufs.observatorio.model.Usuario;
import br.com.ufs.observatorio.util.Conexao;

public class UsuarioDAO {
	Conexao con = new Conexao();
	
	public Usuario consultarUsuario(String email, String senha) throws SQLException {


		Usuario obj = new Usuario();
		
		String sql = "select * from usuario where cv_email='" + email + "' and cv_senha='" + senha + "'";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			obj.setCodigo(resultado.getInt("id_usuario"));
			obj.setNome(resultado.getString("cv_nome"));
			obj.setEmail(resultado.getString("cv_email"));
			obj.setSenha(resultado.getString("cv_senha"));

		}

		comando.close();
		con.conexao.close();
		return obj;

	}
	
	public Usuario retornarUsuarioValido(String email, String senha) throws SQLException {


		Usuario obj = new Usuario();
		
		String sql = "select * from usuario where cv_email='" + email + "' and cv_senha='" + senha + "'";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			obj.setCodigo(resultado.getInt("id_usuario"));
			obj.setNome(resultado.getString("cv_nome"));
			obj.setEmail(resultado.getString("cv_email"));
			obj.setSenha(resultado.getString("cv_senha"));

		}

		comando.close();
		con.conexao.close();
		return obj;

	}
	public boolean login(String email, String senha) throws SQLException {


		boolean sucesso = false;
		
		String sql = "select * from usuario where cv_email='" + email + "' and cv_senha='" + senha + "'";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			Usuario obj = new Usuario();
			obj.setCodigo(resultado.getInt("id_usuario"));
			obj.setNome(resultado.getString("cv_nome"));
			obj.setEmail(resultado.getString("cv_email"));
			obj.setSenha(resultado.getString("cv_senha"));
			sucesso = true;

		}

		comando.close();
		con.conexao.close();
		return sucesso;

	}

	
	public boolean cadastrarUsuario(String nome, String email, String senha) throws SQLException{
		boolean sucesso;
		sucesso = false;
		
		String sql = "Insert into usuario (cv_nome, cv_email, cv_senha) values ('"+ nome + "','" + email + "','" + senha + "')" ;
		
		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);
		return sucesso;
	}
	

}
