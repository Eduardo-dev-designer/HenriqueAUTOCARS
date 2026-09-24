package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author eduar
 */
public class ClienteDAO {

    public int inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, email, telefone, senha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    cliente.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir cliente.", ex);
        }
        return -1;
    }

    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());
            stmt.setInt(6, cliente.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar cliente.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar cliente.", ex);
        }
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar cliente por id.", ex);
        }
        return null;
    }

    /**
     * Usado na tela de login do cliente (email/telefone + senha).
     */
    public Cliente buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM cliente WHERE (email = ? OR telefone = ?) AND senha = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao autenticar cliente.", ex);
        }
        return null;
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar clientes.", ex);
        }
        return clientes;
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setSenha(rs.getString("senha"));
        return cliente;
    }
}