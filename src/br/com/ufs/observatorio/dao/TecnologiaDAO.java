package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ufs.observatorio.model.Tecnologia;
import br.com.ufs.observatorio.util.Conexao;

public class TecnologiaDAO {
	Conexao con = new Conexao();

	public ArrayList<Tecnologia> consultarTecnologias() throws SQLException {

		ArrayList<Tecnologia> lista = new ArrayList<Tecnologia>();

		String sql = " select row_number() over (order by count(cv_descricao) desc) as colocacao, cv_descricao, count(cv_descricao) as quantidade from formulario_tecnologia ft "
				+ " INNER JOIN formulario f ON f.id_formulario = ft.id_formulario "
				+ " INNER JOIN tecnologia t ON t.id_tecnologia = ft.id_tecnologia "
				+ " group by cv_descricao order by quantidade desc";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			Tecnologia obj = new Tecnologia();
			obj.setColocacao(resultado.getInt("colocacao"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setQuantidade(resultado.getInt("quantidade"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}
}
