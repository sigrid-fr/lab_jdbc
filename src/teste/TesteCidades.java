package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import dao.CidadeDAO;
import infra.ConnectionPool;
import modelo.Cidade;



public class TesteCidades {
	
	public static void main (String []args) throws SQLException, ParseException{
		
		try (Connection conexao = new ConnectionPool().getConnection() ) {
			Statement usar = conexao.createStatement();
			usar.execute("USE fretedb");
			Cidade cidade = new Cidade();
			cidade.setCodigo_cidade(1);
			cidade.setNome("São Luis");
			cidade.setTaxa(2.55);
			cidade.setUF("MA");
			
			
			CidadeDAO daocidade = new CidadeDAO(conexao);
			daocidade.inserirCidade(cidade);
			
						
			List<Cidade> lista = daocidade.listarCidades();
			for (Cidade ci: lista) {
				System.out.println("\nCodigo Cidade:" +ci.getCodigo_cidade());
				System.out.println("Nome:" +ci.getNome());
				System.out.println("Taxa:" +ci.getTaxa());
				System.out.println("UF:" +ci.getUF()+"\n");
			
			}
			
			System.out.println("cidade com maior quantidade de fretes: " + daocidade.listarCidadeMaisFretes() +"\n");
			//conexao.commit();
			conexao.close();
			
		
		
		}
	}
}