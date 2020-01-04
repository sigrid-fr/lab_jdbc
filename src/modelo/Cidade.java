package modelo;

public class Cidade {
	
	private int codigo_cidade;
	private String nome;
	private String UF;
	private double taxa;
	
	
	public int getCodigo_cidade() {
		return codigo_cidade;
	}
	public void setCodigo_cidade(int codigo_cidade) {
		this.codigo_cidade = codigo_cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String UF) {
		this.UF = UF;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	@Override
	public String toString() {
		return "Cidade [codigo_cidade=" + codigo_cidade + ", nome=" + nome + ", UF=" + UF + ", taxa=" + taxa + "]";
	}
	
	

}
