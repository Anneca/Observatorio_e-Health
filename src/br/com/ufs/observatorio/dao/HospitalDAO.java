package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.ufs.observatorio.model.Hospital;
import br.com.ufs.observatorio.model.Tecnologia;
import br.com.ufs.observatorio.util.Conexao;

public class HospitalDAO {

	Conexao con = new Conexao();
	CidadeDAO cidadeDAO = new CidadeDAO();
	PaisDAO paisDAO = new PaisDAO();
	
	
	public void alterarHospital(String descricao, String possui_site, String url, int id) throws SQLException {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String format = formatter.format(data);

		String sql = "UPDATE hospital SET cv_nome ='" + descricao + "'," + " cv_tem_site='" + possui_site
				+ "', dt_cadastro='" + format + "',cv_site='"+ url + "' where id_hospital=" + id;
		System.out.println(sql);

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		comando.execute(sql);
		comando.close();
		con.conexao.close();

	}
	
	public void excluirHospital(int id) throws SQLException {
		String sql = "DELETE FROM hospital WHERE id_hospital =" + id;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		comando.execute(sql);
		comando.close();
		con.conexao.close();

	}



	public boolean cadastrarHospital(String nome, String site, int cidade, int pais, String tipoOrganizacao, String temSite)
			throws SQLException {
		boolean sucesso = false;
		
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String format = formatter.format(data);
		
		String sql = "Insert into hospital (cv_nome, cv_site, id_cidade, id_pais, cv_tipo_organizacao, cv_tem_site, dt_cadastro) values ('"
				+ nome + "','" + site + "','" + cidade + "','" + pais + "','" + tipoOrganizacao +"','" + temSite + "','" + format + "')";

		System.out.println(sql);

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);

		return sucesso;
	}
	
	public ArrayList<Hospital> consultarHospitais() throws SQLException {

		ArrayList<Hospital> lista = new ArrayList<Hospital>();

		String sql = "select * from hospital";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			Hospital hospital = new Hospital();
			hospital.setCodigo(resultado.getInt("id_hospital"));
			hospital.setNome(resultado.getString("cv_nome"));
			hospital.setPossuiSite(resultado.getString("cv_tem_site"));
			hospital.setSite(resultado.getString("cv_site"));
			hospital.setTipoOrganizacao(resultado.getString("cv_tipo_organizacao"));
			hospital.setDataAlteracao(resultado.getString("dt_cadastro"));
			hospital.setCidade(cidadeDAO.consultarCidadeByID(resultado.getInt("id_cidade")));
			hospital.setPais(paisDAO.consultarPaisByID(resultado.getInt("id_pais")));
			lista.add(hospital);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}

}
