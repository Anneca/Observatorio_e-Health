package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ufs.observatorio.model.Cidade;
import br.com.ufs.observatorio.util.Conexao;

public class CidadeDAO {

	Conexao con = new Conexao();

	public ArrayList<Cidade> consultarCidades() throws SQLException {

		ArrayList<Cidade> lista = new ArrayList<Cidade>();

		String sql = "select * from cidade";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			Cidade obj = new Cidade();
			obj.setCodigo(resultado.getInt("id_cidade"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setCapital(resultado.getString("cv_capital"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}
	
	public boolean cadastrarCidade(String nome, String capital) throws SQLException{
		boolean sucesso;
		sucesso = false;
		
		String sql = "Insert into cidade (cv_descricao, cv_capital) values ('"+ nome + "','" + capital + "')" ;
		
		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);
		return sucesso;
	}

}
