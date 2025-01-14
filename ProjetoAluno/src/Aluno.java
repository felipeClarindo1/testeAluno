import java.util.List;

public class Aluno {
	
	private int codigo;
	private String nome;
	private double total_aulas;
	private List<Double> notas;
	private double faltas;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getTotal_aulas() {
		return total_aulas;
	}
	public void setTotal_aulas(double total_aulas) {
		this.total_aulas = total_aulas;
	}
	public List<Double> getNotas() {
		return notas;
	}
	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
	public double getFaltas() {
		return faltas;
	}
	public void setFaltas(double faltas) {
		this.faltas = faltas;
	}

}
