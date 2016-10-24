package br.com.ufs.observatorio.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.ufs.observatorio.util.Conexao;

public class UsuarioPerfilDAO {
	
	Conexao con = new Conexao();
	
	public boolean cadastrarUsuarioPerfil(int usuario, int perfil) throws SQLException{
		boolean sucesso;
		sucesso = false;
		
		String sql = "Insert into usuario_perfil (id_usuario, id_perfil) values ("+ usuario + "," + perfil + ")" ;
		
		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);
		return sucesso;
	}


}
