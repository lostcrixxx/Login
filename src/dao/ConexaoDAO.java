package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {

	public static Connection getConexao() {

		String servidor = "localhost";
		String porta = "3306";
		String database = "database";

		String usuario = "root";
		String senha = "root";

		try {
			String url = "jdbc:mysql://" + servidor + ":" + porta + "/" + database;

			return DriverManager.getConnection(url, usuario, senha);
		} catch (Exception e) {
			// serve de apoio para esclarecer o erro
			System.out.println(e);
			return null;
		}
	}

}
