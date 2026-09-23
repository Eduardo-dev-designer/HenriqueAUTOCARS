package model;

/**
 *
 * @author eduar
 */
public class Funcionario {

    private int id;
    private String nome;
    private String matricula;
    private String cargo;
    private String senha;

    public Funcionario() {
    }

    public Funcionario(String nome, String matricula, String cargo, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.senha = senha;
    }

    public Funcionario(int id, String nome, String matricula, String cargo, String senha) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", nome=" + nome
                + ", matricula=" + matricula + ", cargo=" + cargo + '}';
    }
}