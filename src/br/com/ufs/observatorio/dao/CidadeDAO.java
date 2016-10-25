package br.com.ufs.observatorio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.ufs.observatorio.model.Cidade;
import br.com.ufs.observatorio.util.Conexao;

public class CidadeDAO {

	Conexao con = new Conexao();

	public void alterarCidade(String descricao, String capital, int id) throws SQLException {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String format = formatter.format(data);

		String sql = "UPDATE cidade SET cv_descricao ='" + descricao + "'," + " cv_capital='" + capital
				+ "', ultima_alteracao='" + format + "' where id_cidade=" + id;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		comando.execute(sql);
		comando.close();
		con.conexao.close();

	}

	public void excluirCidade(int id) throws SQLException {
		String sql = "DELETE FROM cidade WHERE id_cidade =" + id;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		comando.execute(sql);
		comando.close();
		con.conexao.close();

	}

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
			obj.setUltimaAlteracao(resultado.getString("ultima_alteracao"));
			lista.add(obj);
		}

		comando.close();
		con.conexao.close();
		return lista;

	}

	public Cidade consultarCidadeByID(int id) throws SQLException {

		String sql = "select * from cidade where id_cidade=" + id;

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		ResultSet resultado = comando.executeQuery(sql);
		Cidade obj = new Cidade();

		while (resultado.next()) {
			obj.setCodigo(resultado.getInt("id_cidade"));
			obj.setDescricao(resultado.getString("cv_descricao"));
			obj.setCapital(resultado.getString("cv_capital"));
		}

		comando.close();
		con.conexao.close();
		return obj;

	}

	public boolean cadastrarCidade(String nome, String capital) throws SQLException {
		boolean sucesso;
		sucesso = false;

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String format = formatter.format(data);

		String sql = "Insert into cidade (cv_descricao, cv_capital, ultima_alteracao) values ('" + nome + "','"
				+ capital + "','" + format + "')";

		con.setConnection();
		Statement comando = con.conexao.createStatement();
		sucesso = comando.execute(sql);
		return sucesso;
	}

}
