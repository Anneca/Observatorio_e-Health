package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ufs.observatorio.model.DadosPais;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.util.Conexao;

public class PaisDAO {
	Conexao con = new Conexao();
	int idPais;
	String nomePais;

	public ArrayList<Pais> consultarPais() throws SQLException {

		ArrayList<Pais> lista = new ArrayList<Pais>();

		String sql = "select * from pais";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			Pais obj = new Pais();
			obj.setCodigo(resultado.getInt("id_pais"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}

	public DadosPais consultarDadosPais(String pais, String data) throws SQLException {

		int id = capturarIdPaisByNome(pais);
		int quantidadeHospitaisCatalogados = retornarQtHospitaisCatalogados(id);
		int quantidadeHospitaisComSites = retornarQtHospitaisCatalogados(id);
		int quantidadeSitesInfomacoesInstitucionais = retornarQtSitesComInformacoesInstitucionais(id);
		int quantidadeSitesComServicos = retornarQtSitesComServico(id);
		int quantidadeSitesComCorpoClinico = retornarQtSitesComCorpoClinico(id);
		int quantidadeSitesForaDoAr = retornarQtSitesForaDoAr(id);
		int quantidadeSitesComComentarios = retornarQtSitesComComentarios(id);
		int hospitaisPublicos = retornarQtHospitaisPublicos(id);
		int hospitaisPrivados = retornarQtHospitaisPrivados(id);

		// Preenchendo o objeto

		DadosPais obj = new DadosPais();

		obj.setPais(pais);
		obj.setQtHospitaisCatalogados(quantidadeHospitaisCatalogados);
		obj.setQtHospitaisComSite(quantidadeHospitaisComSites);
		obj.setQtSitesComComentarios(quantidadeSitesComComentarios);
		obj.setQtSitesComCorpoClinico(quantidadeSitesComCorpoClinico);
		obj.setQtSitesInformacaoInstitucional(quantidadeSitesInfomacoesInstitucionais);
		obj.setQtSitesComServico(quantidadeSitesComServicos);
		obj.setQtSitesForaDoAr(quantidadeSitesForaDoAr);
		obj.setPais(pais);
		obj.setHospitaisPrivados(hospitaisPrivados);
		obj.setHospitaisPublicos(hospitaisPublicos);

		return obj;

	}

	public int capturarIdPaisByNome(String pais) throws SQLException {

		String sql = "Select * from Pais where cv_descricao = '" + pais + "';";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			idPais = resultado.getInt("id_pais");
			nomePais = resultado.getString("cv_descricao");
		}

		comando.close();
		con.conexao.close();
		return idPais;

	}

	// HOSPITAIS CATALOGADOS
	public int retornarQtHospitaisCatalogados(int id) throws SQLException {
		String sql = "Select count(id_pais) as qt_HospitaisCatalogados from Hospital where id_pais=" + id;
		int hospitaisCatalogados = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisCatalogados = resultado.getInt("qt_HospitaisCatalogados");
		}

		comando.close();
		con.conexao.close();
		return hospitaisCatalogados;

	}

	// SITES COM SERVICOS
	public int retornarQtSitesComServico(int id) throws SQLException {
		String sql = " select COUNT(cv_servicos) as qt_sitesComServicos from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_servicos = 'Sim'";

		int sitesComServico = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesComServico = resultado.getInt("qt_sitesComServicos");
		}

		comando.close();
		con.conexao.close();
		return sitesComServico;

	}

	// SITES COM INFORMACOES INSTITUCIONAIS
	public int retornarQtSitesComInformacoesInstitucionais(int id) throws SQLException {
		String sql = " select COUNT(cv_informacoes_institucionais) as qt_sitesInformacaoInstitucional from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_informacoes_institucionais = 'Sim'";

		int sitesComServico = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesComServico = resultado.getInt("qt_sitesInformacaoInstitucional");
		}

		comando.close();
		con.conexao.close();
		return sitesComServico;

	}

	// SITES COM CORPO CLINICO
	public int retornarQtSitesComCorpoClinico(int id) throws SQLException {
		String sql = " select COUNT(cv_corpo_clinico) as qt_sitesComCorpoClinico from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_corpo_clinico = 'Sim'";

		int sitesComCorpoClinico = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesComCorpoClinico = resultado.getInt("qt_sitesComCorpoClinico");
		}

		comando.close();
		con.conexao.close();
		return sitesComCorpoClinico;

	}

	// SITES COM EMAIL PARA COMENTARIOS
	public int retornarQtSitesComComentarios(int id) throws SQLException {
		String sql = " select COUNT(cv_comentarios) as qt_sitesComComentarios from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_comentarios = 'Sim'";

		int sitesComComentarios = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesComComentarios = resultado.getInt("qt_sitesComComentarios");
		}

		comando.close();
		con.conexao.close();
		return sitesComComentarios;

	}

	// SITES FORA DO AR
	public int retornarQtSitesForaDoAr(int id) throws SQLException {
		String sql = " select COUNT(cv_observacao) as qt_sitesForaDoAr from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_observacao = 'Site fora do AR'";

		int sitesForaDoAr = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesForaDoAr = resultado.getInt("qt_sitesForaDoAr");
		}

		comando.close();
		con.conexao.close();
		return sitesForaDoAr;

	}

	// HOSPITAIS PRIVADOS
	public int retornarQtHospitaisPrivados(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisPrivados from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
				+ " AND cv_tipo_organizacao = 'Privado'";
		
		

		int hospitaisPrivados = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisPrivados = resultado.getInt("hospitaisPrivados");
		}

		comando.close();
		con.conexao.close();
		return hospitaisPrivados;

	}
	
	// HOSPITAIS PUBLICOS
		public int retornarQtHospitaisPublicos(int id) throws SQLException {
			String sql = " select COUNT(cv_tipo_organizacao) as hospitaisPublicos from formulario_hospital fm "
					+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
					+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital " + " where id_Pais =" + id
					+ " AND cv_tipo_organizacao = 'Publico'";

			int hospitaisPublicos = 0;

			con.setConnection();
			Statement comando = con.conexao.createStatement();
			ResultSet resultado = comando.executeQuery(sql);

			while (resultado.next()) {
				hospitaisPublicos = resultado.getInt("hospitaisPublicos");
			}

			comando.close();
			con.conexao.close();
			return hospitaisPublicos;

		}

}
