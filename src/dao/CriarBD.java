package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBD {
	
	private Connection conexao;
	
	public CriarBD(Connection conexao, String nome) throws SQLException{
		this.conexao = conexao;
		String sql = "CREATE DATABASE IF NOT EXISTS " + nome;
		Statement stm = this.conexao.createStatement();
		stm.execute(sql);
		stm.execute("USE " + nome);
				
	}
	
	public void criarClienteTB() throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS clienteTB("
				+ "codigo_cliente INT AUTO_INCREMENT,"
				+ "PRIMARY KEY(codigo_cliente),"
				+ "nome VARCHAR(100) DEFAULT '' NOT NULL,"
				+ "endereco VARCHAR(100) DEFAULT '' NOT NULL,"
				+ "telefone VARCHAR(30) DEFAULT '' NOT NULL"
				+ ");";
		
		Statement stm = this.conexao.createStatement();
		stm.execute(sql);
		
	}
	
	
	public void criarFreteTB() throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS freteTB("
				+ "codigo_frete INT AUTO_INCREMENT,"
				+ "PRIMARY KEY(codigo_frete),"
				+ "codigo_cidade INT,"
				+ "codigo_cliente INT,"
				+ "descricao VARCHAR(30) DEFAULT '' NOT NULL,"
				+ "peso FLOAT(4),"
				+ "valor FLOAT(4),"
				+ "CONSTRAINT"
				+ " FOREIGN KEY(codigo_cidade) REFERENCES cidadeTB(codigo_cidade)"
				+ " ON DELETE NO ACTION ON UPDATE NO ACTION,"
				+ " FOREIGN KEY(codigo_cliente) REFERENCES clienteTB(codigo_cliente)"
				+ ");";
		Statement stm = this.conexao.createStatement();
		stm.execute(sql);
	}
	
	public void criarCidadeTB() throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS cidadeTB("
				+ "codigo_cidade INT AUTO_INCREMENT,"
				+ "PRIMARY KEY(codigo_cidade),"
				+ "nome VARCHAR(30) DEFAULT '' NOT NULL,"
				+ "UF VARCHAR(30) DEFAULT '' NOT NULL,"
				+ "taxa FLOAT(4)"
				+ ");";
		Statement stm = this.conexao.createStatement();
		stm.execute(sql);
	}

}
