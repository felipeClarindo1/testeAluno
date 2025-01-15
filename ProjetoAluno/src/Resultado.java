import java.util.List;

public class Resultado {
    private String resultado;
    private List<Aluno> alunos;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}

class Aluno {
    private int COD;
    private String NOME;
    private int TOTAL_AULAS;
    private List<Nota> nota;

    public int getCOD() {
        return COD;
    }

    public void setCOD(int COD) {
        this.COD = COD;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public int getTOTAL_AULAS() {
        return TOTAL_AULAS;
    }

    public void setTOTAL_AULAS(int TOTAL_AULAS) {
        this.TOTAL_AULAS = TOTAL_AULAS;
    }

    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
    }
}

class Nota {
    private int FALTAS;
    private double NOTA;

    public int getFALTAS() {
        return FALTAS;
    }

    public void setFALTAS(int FALTAS) {
        this.FALTAS = FALTAS;
    }

    public double getNOTA() {
        return NOTA;
    }

    public void setNOTA(double NOTA) {
        this.NOTA = NOTA;
    }
}