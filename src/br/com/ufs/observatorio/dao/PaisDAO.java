package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import br.com.ufs.observatorio.model.DadosPais;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.util.Conexao;

public class PaisDAO {
	Conexao con = new Conexao();
	int idPais;
	String nomePais;

	public boolean cadastrarPais(String descricao, String capital, String populacao, String IDH, String IDI, String PIB,
			String ultimaAlteracao) throws SQLException {
		boolean sucesso = false;

		String sql = "Insert into pais (cv_descricao, cv_capital, cv_populacao, cv_idh, cv_idi, cv_pib, dt_ultima_alteracao) values ('"
				+ descricao + "','" + capital + "','" + populacao + "','" + IDH + "','" + IDI + "','" + PIB + "','"
				+ ultimaAlteracao + "')";

		System.out.println(sql);

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);

		return sucesso;
	}

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

	public Pais consultarPaisByNome(String nome) throws SQLException {

		Pais obj = new Pais();

		String sql = "select * from pais where cv_descricao='" + nome + "'";
		// System.out.println(sql);

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			obj.setCodigo(resultado.getInt("id_pais"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setCapital(resultado.getString("cv_capital"));
			obj.setIDH(resultado.getString("cv_idh"));
			obj.setIDI(resultado.getString("cv_idi"));
			obj.setPIB(resultado.getString("cv_pib"));
			obj.setUltimaAlteracao(resultado.getString("dt_ultima_alteracao"));
			obj.setPopulacao(resultado.getString("cv_populacao"));

		}

		comando.close();
		con.conexao.close();
		return obj;

	}

	public DadosPais consultarDadosPais(String pais, String data) throws SQLException {

		int id = capturarIdPaisByNome(pais);
		int quantidadeHospitaisCatalogados = retornarQtHospitaisCatalogados(id);
		int quantidadeHospitaisComSites = retornarQtHospitaisComSites(id, data);
		int quantidadeSitesInfomacoesInstitucionais = retornarQtSitesComInformacoesInstitucionais(id);
		int quantidadeSitesComServicos = retornarQtSitesComServico(id);
		int quantidadeSitesComCorpoClinico = retornarQtSitesComCorpoClinico(id);
		int quantidadeSitesForaDoAr = retornarQtSitesForaDoAr(id, data);
		int quantidadeSitesComComentarios = retornarQtSitesComComentarios(id);
		int hospitaisPublicos = retornarQtHospitaisPublicos(id);
		int hospitaisPrivados = retornarQtHospitaisPrivados(id);
		int hospitaisMisto = retornarQtHospitaisMisto(id);
		int hospitaisNaoDefinidos = retornarQtHospitaisNaoDefinido(id);
		int hospitaisUniversitarios = retornarQtHospitaisUniversitarios(id);
		int hospitaisSemFinsLucrativos = retornarQtHospitaisSemFinsLucrativos(id);
		int hospitaisSemSites = retornarQtHospitaisSemSites(id, data);
		int quantidadeSitesDisponiveis = retornarQtSitesDisponiveis(id, data);

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
		obj.setHospitaisMisto(hospitaisMisto);
		obj.setHospitaisUniversitarios(hospitaisUniversitarios);
		obj.setHospitaisNaoDefinidos(hospitaisNaoDefinidos);
		obj.setHospitaisSemFinsLucrativos(hospitaisSemFinsLucrativos);
		obj.setQtHospitaisSemSites(hospitaisSemSites);
		obj.setQtSitesDisponiveis(quantidadeSitesDisponiveis);

		return obj;

	}

	private int retornarQtHospitaisComSites(int id, String data) throws SQLException {
		String sql = "Select count(id_pais) as qt_HospitaisComSite from Hospital where id_pais=" + id
				+ " and cv_tem_site = 'Sim'";

		int hospitaisComSite = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisComSite = resultado.getInt("qt_HospitaisComSite");
		}

		comando.close();
		con.conexao.close();
		return hospitaisComSite;
	}

	// Hospitais Sem Sites
	private int retornarQtHospitaisSemSites(int id, String data) throws SQLException {
		String sql = "Select count(id_pais) as qt_HospitaisSemSite from Hospital where id_pais=" + id
				+ " and cv_tem_site = 'Não'";

		int hospitaisSemSite = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisSemSite = resultado.getInt("qt_HospitaisSemSite");
		}

		comando.close();
		con.conexao.close();
		return hospitaisSemSite;
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
	public int retornarQtSitesForaDoAr(int id, String data) throws SQLException {
		String sql = " select COUNT(cv_observacao) as qt_sitesForaDoAr from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital  " + " where h.id_Pais = " + id
				+ " AND f.cv_observacao = 'Site fora do AR' " + " AND f.dt_formulario LIKE '%" + data + "%'";

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

	// Sites Disponíveis
	private int retornarQtSitesDisponiveis(int id, String data) throws SQLException {

		String sql = " select COUNT(cv_observacao) as qt_sitesDisponiveis from formulario_hospital fm "
				+ " INNER JOIN formulario f ON f.id_formulario = fm.id_formulario "
				+ " INNER JOIN hospital h ON h.id_hospital = fm.id_hospital  " + " where h.id_Pais = " + id
				+ " AND f.cv_observacao = 'Sem Observações!' " + " AND f.dt_formulario LIKE '%" + data + "%'";

		int sitesDisponiveis = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			sitesDisponiveis = resultado.getInt("qt_sitesDisponiveis");
		}

		comando.close();
		con.conexao.close();
		return sitesDisponiveis;
	}

	// HOSPITAIS Misto
	public int retornarQtHospitaisMisto(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisMistos from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Misto'";
		
		int hospitaisMistos = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisMistos = resultado.getInt("hospitaisMistos");
		}

		comando.close();
		con.conexao.close();
		return hospitaisMistos;

	}

	// HOSPITAIS Nao Definido
	public int retornarQtHospitaisNaoDefinido(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisNaoDefinido from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Nao definido'";
		
		int hospitaisNaoDefinido = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisNaoDefinido = resultado.getInt("hospitaisNaoDefinido");
		}

		comando.close();
		con.conexao.close();
		return hospitaisNaoDefinido;

	}

	// HOSPITAIS SEM FINS LUCRATIVOS
	public int retornarQtHospitaisSemFinsLucrativos(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisSemFinsLucrativos from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Sem Fins lucrativos'";
		System.out.println(sql);

		int hospitaisSemFinsLucrativos = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisSemFinsLucrativos = resultado.getInt("hospitaisSemFinsLucrativos");
		}

		comando.close();
		con.conexao.close();
		return hospitaisSemFinsLucrativos;

	}

	// HOSPITAIS Universitário
	public int retornarQtHospitaisUniversitarios(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisUniversitarios from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Universitario'";
	
		int hospitaisUniversitarios = 0;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);

		while (resultado.next()) {
			hospitaisUniversitarios = resultado.getInt("hospitaisUniversitarios");
		}

		comando.close();
		con.conexao.close();
		return hospitaisUniversitarios;

	}

	// HOSPITAIS PRIVADOS
	public int retornarQtHospitaisPrivados(int id) throws SQLException {
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisPrivados from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Privado'";
	
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
		String sql = " select COUNT(cv_tipo_organizacao) as hospitaisPublicos from hospital h "
				+ " where id_Pais = " + id + " AND  cv_tipo_organizacao = 'Publico'";
	
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
