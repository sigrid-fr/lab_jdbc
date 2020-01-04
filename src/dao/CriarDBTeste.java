package dao;

import java.sql.Connection;
import java.sql.SQLException;

import infra.ConnectionPool;

public class CriarDBTeste {
	
	public static void main(String[] args) throws SQLException{
		
		try(Connection conexao = new ConnectionPool().getConnection()){
			CriarBD criarDb = new CriarBD(conexao, "fretedb");
			criarDb.criarClienteTB();
			criarDb.criarCidadeTB();
			criarDb.criarFreteTB();
		}
	}

}
