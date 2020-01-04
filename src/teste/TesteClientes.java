package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.ClienteDAO;
import modelo.Cliente;
import infra.ConnectionPool;


public class TesteClientes {
	public static void main (String []args) throws SQLException{
		
		try (Connection conexao = new ConnectionPool().getConnection() ) {
			//Cliente clienteUm = new Cliente ('Ana', 'Avenida Rei de França Nº09, Bairro: Cohab-Anil', '(98)3245-6789');
			
			Statement usar = conexao.createStatement();
			usar.execute("USE fretedb");
			
			Cliente cliente = new Cliente();
			cliente.setCodigo_cliente(1);
			cliente.setNome("Marcos Santos");
			cliente.setEndereco("Avenida Rei de França Nº09, Bairro: Cohab-Anil");
			cliente.setTelefone("(98)32070909");
			
			
			
			ClienteDAO daocliente = new ClienteDAO(conexao);
			daocliente.inserirCliente(cliente);
			
						
			List<Cliente> lista = daocliente.listarClientes();
			for (Cliente c: lista) {
				System.out.println("\nCodigo Cliente:" +c.getCodigo_cliente());
				System.out.println("Nome:" +c.getNome());
				System.out.println("Endereco:" +c.getEndereco());
				System.out.println("Telefone:" +c.getTelefone()+"\n");
			
		}
			//conexao.commit();
			conexao.close();
			
		
		
		}
	}
}
