package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.ufs.observatorio.model.RedeSocial;
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
}
