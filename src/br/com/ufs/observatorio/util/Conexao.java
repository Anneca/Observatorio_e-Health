package br.com.ufs.observatorio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Anne Caroline Melo Santos
 *
 */

public class Conexao {

	public Connection conexao;

	public Conexao() {

		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Conexao teste = new Conexao();
		try {
			teste.setConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Settando conexão de acordo com o banco de dados utilizado.
	 */
	public void setConnection() throws SQLException {
		String host = "localhost:5433";
		String database = "WEB_CRAWLER_03-09-2016";
		String url = "jdbc:postgresql://" + host + "/" + database;
		String user = "postgres";
		String password = "12345678";
		conexao = DriverManager.getConnection(url, user, password);

	}

	/*
	 * Métodos de consulta ao banco de dados
	 */

	public void remover() {
		try {
			conexao.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
