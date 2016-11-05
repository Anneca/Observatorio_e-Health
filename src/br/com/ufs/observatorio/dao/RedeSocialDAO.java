package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.ufs.observatorio.model.RedeSocial;
import br.com.ufs.observatorio.model.Tecnologia;
import br.com.ufs.observatorio.util.Conexao;

public class RedeSocialDAO {
	Conexao con = new Conexao();

	public ArrayList<RedeSocial> consultarRedesSociais() throws SQLException {

		ArrayList<RedeSocial> lista = new ArrayList<RedeSocial>();

		String sql = " select row_number() over (order by count(cv_descricao) desc) as colocacao, cv_descricao, count(cv_descricao) as quantidade from formulario_redes_sociais fr "
				+ " INNER JOIN formulario f ON f.id_formulario = fr.id_formulario "
				+ " INNER JOIN rede_social r ON r.id_rede_social = fr.id_rede_social "
				+ " group by cv_descricao order by quantidade desc";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			RedeSocial obj = new RedeSocial();
			obj.setColocacao(resultado.getInt("colocacao"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setQuantidade(resultado.getInt("quantidade"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}
	/*
	 * Retorna uma lista com as 5 redes sociais mais utilizadas pelos hospitais de um país na data presente
	 */
	public ArrayList<RedeSocial> consultarRedesSociaisByPaisDataAtual(String dataAtual, int id) throws SQLException {
		ArrayList<RedeSocial> lista = new ArrayList<RedeSocial>();

		String sql = " select row_number() over (order by count(cv_descricao) desc), cv_descricao, count(cv_descricao) as quantidade from formulario_redes_sociais fr " +
					 " INNER JOIN formulario f ON f.id_formulario = fr.id_formulario " +
					 " INNER JOIN rede_social r ON r.id_rede_social = fr.id_rede_social " +
					 " INNER JOIN formulario_hospital fh ON fh.id_formulario = fr.id_formulario " +
					 " INNER JOIN hospital h on h.id_hospital = fh.id_hospital " +
					 " where h.id_pais = " + id + " and " +
					 " f.dt_formulario LIKE '%" + dataAtual + "%' " +
					 " group by cv_descricao order by quantidade desc " +
					 " LIMIT 5 ";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			RedeSocial obj = new RedeSocial();
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setQuantidade(resultado.getInt("quantidade"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;
	}
	
	// Método utilizado na tela de Ranking Redes Sociais
	public ArrayList<RedeSocial> consultarRedeSocialByPaisData(String data, int id) throws SQLException {

		ArrayList<RedeSocial> lista = new ArrayList<RedeSocial>();

		String sql = " select row_number() over (order by count(cv_descricao) desc), cv_descricao, count(cv_descricao) as quantidade from formulario_redes_sociais fr " +
					 " INNER JOIN formulario f ON f.id_formulario = fr.id_formulario " + 
					 " INNER JOIN rede_social r ON r.id_rede_social = fr.id_rede_social " + 
					 " INNER JOIN formulario_hospital fh ON fh.id_formulario = fr.id_formulario " +
					 " INNER JOIN hospital h on h.id_hospital = fh.id_hospital " +
					 " where h.id_pais = " + id + " and " +
					 " f.dt_formulario LIKE '%" + data + "%' " +
					 " group by cv_descricao order by quantidade desc ";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			RedeSocial obj = new RedeSocial();
			obj.setColocacao(resultado.getInt("row_number"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setQuantidade(resultado.getInt("quantidade"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}


}
