package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import dao.FreteDAO;
import infra.ConnectionPool;
import modelo.Cidade;
import modelo.Cliente;
import modelo.Frete;


public class TesteFretes {
	
	public static void main (String []args) throws SQLException{
		
		try (Connection conexao = new ConnectionPool().getConnection() ) {
			Statement usar = conexao.createStatement();
			usar.execute("USE fretedb");
			
			Cliente c = new Cliente();
			c.setCodigo_cliente(1);
			c.setNome("Ana Maria Braga");
			c.setEndereco("Rua General Arthur Carvalho");
			c.setTelefone("(98)987654321");
			
			Cidade ci = new Cidade();
			ci.setCodigo_cidade(1);
			ci.setNome("Ana Maria Braga");
			ci.setTaxa(3.27);
			ci.setUF("MA");
			
			
			
			Frete frete = new Frete();
			frete.setCliente(c);
			frete.setCidade(ci);
			frete.setDescricao("Frágil");
			frete.setPeso(55);
			frete.setValor(290);
			
			
			FreteDAO daofrete = new FreteDAO(conexao);
			daofrete.inserirFrete(frete);
		
						
			List<Frete> lista = daofrete.listarFretes();
			for (Frete f: lista) {
				System.out.println("Codigo Frete:" +f.getCodigo_frete());
				System.out.println("Cliente:" +f.getCliente().getNome());
				System.out.println("Cidade:" +f.getCidade().getNome());
				System.out.println("Descricao:" +f.getDescricao());
				System.out.println("Peso:" +f.getPeso()+"\n");
				System.out.println("Valor:" +f.getValor());			
			}
			
			System.out.println("Frete mais caro: " + daofrete.freteMaisCaro());
			//conexao.commit();
			
			Frete f = new Frete();
			f = daofrete.freteMaisCaro();
			System.out.println("Valor do frete mais caro: " + (f.getPeso()*10)+f.getCidade().getTaxa());
		
			
			conexao.close();
		}
	}
}