package modelo;

public class Frete {
	
	private int codigo_frete;
	private int codigo_cidade;
	private int codigo_cliente;
	private String descricao;
	private double peso;
	private double valor;
	private Cliente cliente;
	private Cidade cidade;
	
	
	public int getCodigo_frete() {
		return codigo_frete;
	}
	public void setCodigo_frete(int codigo_frete) {
		this.codigo_frete = codigo_frete;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return "Frete nº.: " + codigo_frete + ", Cidade nº.: " + codigo_cidade + " Cliente nº.: "
				+ codigo_cliente + ", Tipo: " + descricao + ", Peso: " + peso + ", Valor: R$" + valor;
	}
	
	
	

}
