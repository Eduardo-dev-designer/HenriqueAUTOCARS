package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eduar
 */
public class ConexaoBD {

    private static final String URL = "jdbc:sqlite:vendas.db";

    private ConexaoBD() {
    }

    public static Connection conectar() throws SQLException {
        Connection conexao = DriverManager.getConnection(URL);
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        return conexao;
    }

    /**
     * Cria todas as tabelas do sistema caso ainda nao existam. Deve ser
     * chamado uma vez na inicializacao da aplicacao (ex.: na classe main).
     */
    public static void inicializarBanco() {
        String[] tabelas = {
            "CREATE TABLE IF NOT EXISTS cliente ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT,"
            + "cpf TEXT UNIQUE,"
            + "email TEXT UNIQUE,"
            + "telefone TEXT,"
            + "senha TEXT)",
            "CREATE TABLE IF NOT EXISTS funcionario ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT,"
            + "matricula TEXT UNIQUE,"
            + "cargo TEXT,"
            + "senha TEXT)",
            "CREATE TABLE IF NOT EXISTS veiculo ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "marca TEXT,"
            + "modelo TEXT,"
            + "ano INTEGER,"
            + "cor TEXT,"
            + "kilometragem REAL,"
            + "placa TEXT UNIQUE,"
            + "valor REAL,"
            + "disponivel INTEGER)",
            "CREATE TABLE IF NOT EXISTS score ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "cliente_id INTEGER,"
            + "pontuacao INTEGER,"
            + "classificacao TEXT,"
            + "FOREIGN KEY(cliente_id) REFERENCES cliente(id))",
            "CREATE TABLE IF NOT EXISTS estoque ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "veiculo_id INTEGER,"
            + "quantidade INTEGER,"
            + "FOREIGN KEY(veiculo_id) REFERENCES veiculo(id))",
            "CREATE TABLE IF NOT EXISTS compra ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "cliente_id INTEGER,"
            + "veiculo_id INTEGER,"
            + "funcionario_id INTEGER,"
            + "data_compra TEXT,"
            + "valor_total REAL,"
            + "FOREIGN KEY(cliente_id) REFERENCES cliente(id),"
            + "FOREIGN KEY(veiculo_id) REFERENCES veiculo(id),"
            + "FOREIGN KEY(funcionario_id) REFERENCES funcionario(id))",
            "CREATE TABLE IF NOT EXISTS locacao ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "cliente_id INTEGER,"
            + "veiculo_id INTEGER,"
            + "funcionario_id INTEGER,"
            + "data_inicio TEXT,"
            + "data_fim TEXT,"
            + "valor_diaria REAL,"
            + "FOREIGN KEY(cliente_id) REFERENCES cliente(id),"
            + "FOREIGN KEY(veiculo_id) REFERENCES veiculo(id),"
            + "FOREIGN KEY(funcionario_id) REFERENCES funcionario(id))"
        };

        try (Connection conexao = conectar(); Statement stmt = conexao.createStatement()) {
            for (String sql : tabelas) {
                stmt.execute(sql);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inicializar o banco de dados.", ex);
        }
    }
}