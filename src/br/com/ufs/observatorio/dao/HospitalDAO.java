package br.com.ufs.observatorio.dao;

import java.sql.SQLException;
import java.sql.Statement;

import br.com.ufs.observatorio.util.Conexao;

public class HospitalDAO {

	Conexao con = new Conexao();

	public boolean cadastrarHospital(String nome, String site, int cidade, int pais, String tipoOrganizacao, String temSite)
			throws SQLException {
		boolean sucesso = false;

		String sql = "Insert into hospital (cv_nome, cv_site, id_cidade, id_pais, cv_tipo_organizacao, cv_tem_site) values ('"
				+ nome + "','" + site + "','" + cidade + "','" + pais + "','" + tipoOrganizacao +"','" + temSite + "')";

		System.out.println(sql);

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);

		return sucesso;
	}

}
