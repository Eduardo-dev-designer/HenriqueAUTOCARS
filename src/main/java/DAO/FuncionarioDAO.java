package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author eduar
 */
public class FuncionarioDAO {

    public int inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, matricula, cargo, senha) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getSenha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    funcionario.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir funcionario.", ex);
        }
        return -1;
    }

    public boolean atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, matricula = ?, cargo = ?, senha = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getSenha());
            stmt.setInt(5, funcionario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar funcionario.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar funcionario.", ex);
        }
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionario por id.", ex);
        }
        return null;
    }

    /**
     * Usado na tela de login do funcionario (matricula + senha).
     */
    public Funcionario buscarPorMatriculaSenha(String matricula, String senha) {
        String sql = "SELECT * FROM funcionario WHERE matricula = ? AND senha = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao autenticar funcionario.", ex);
        }
        return null;
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                funcionarios.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar funcionarios.", ex);
        }
        return funcionarios;
    }

    private Funcionario mapear(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setMatricula(rs.getString("matricula"));
        funcionario.setCargo(rs.getString("cargo"));
        funcionario.setSenha(rs.getString("senha"));
        return funcionario;
    }
}