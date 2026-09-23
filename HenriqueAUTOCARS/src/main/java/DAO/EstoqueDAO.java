package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Estoque;
import model.Veiculo;

/**
 *
 * @author eduar
 */
public class EstoqueDAO {

    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public int inserir(Estoque estoque) {
        String sql = "INSERT INTO estoque (veiculo_id, quantidade) VALUES (?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, estoque.getVeiculo().getId());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    estoque.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir estoque.", ex);
        }
        return -1;
    }

    public boolean atualizar(Estoque estoque) {
        String sql = "UPDATE estoque SET veiculo_id = ?, quantidade = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getVeiculo().getId());
            stmt.setInt(2, estoque.getQuantidade());
            stmt.setInt(3, estoque.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar estoque.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar estoque.", ex);
        }
    }

    public Estoque buscarPorVeiculoId(int veiculoId) {
        String sql = "SELECT * FROM estoque WHERE veiculo_id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, veiculoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar estoque por veiculo.", ex);
        }
        return null;
    }

    public List<Estoque> listarTodos() {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar estoque.", ex);
        }
        return lista;
    }

    private Estoque mapear(ResultSet rs) throws SQLException {
        Veiculo veiculo = veiculoDAO.buscarPorId(rs.getInt("veiculo_id"));
        Estoque estoque = new Estoque();
        estoque.setId(rs.getInt("id"));
        estoque.setVeiculo(veiculo);
        estoque.setQuantidade(rs.getInt("quantidade"));
        return estoque;
    }
}