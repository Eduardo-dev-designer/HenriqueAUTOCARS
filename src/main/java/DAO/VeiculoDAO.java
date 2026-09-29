package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

/**
 * @author eduar
 */
public class VeiculoDAO {

    public int inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (marca, modelo, ano, cor, kilometragem, placa, valor, disponivel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preencherStatement(stmt, veiculo);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    veiculo.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir veiculo.", ex);
        }
        return -1;
    }

    public boolean atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET marca = ?, modelo = ?, ano = ?, cor = ?, kilometragem = ?, "
                + "placa = ?, valor = ?, disponivel = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            preencherStatement(stmt, veiculo);
            stmt.setInt(9, veiculo.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar veiculo.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar veiculo.", ex);
        }
    }

    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar veiculo por id.", ex);
        }
        return null;
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo ORDER BY marca, modelo";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                veiculos.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar veiculos.", ex);
        }
        return veiculos;
    }

    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE disponivel = 1 ORDER BY marca, modelo";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                veiculos.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar veiculos disponiveis.", ex);
        }
        return veiculos;
    }

    private void preencherStatement(PreparedStatement stmt, Veiculo veiculo) throws SQLException {
        stmt.setString(1, veiculo.getMarca());
        stmt.setString(2, veiculo.getModelo());
        stmt.setInt(3, veiculo.getAno());
        stmt.setString(4, veiculo.getCor());
        stmt.setDouble(5, veiculo.getKilometragem());
        stmt.setString(6, veiculo.getPlaca());
        stmt.setDouble(7, veiculo.getValor());
        stmt.setInt(8, veiculo.isDisponivel() ? 1 : 0);
    }

    private Veiculo mapear(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rs.getInt("id"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAno(rs.getInt("ano"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setKilometragem(rs.getDouble("kilometragem"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setValor(rs.getDouble("valor"));
        veiculo.setDisponivel(rs.getInt("disponivel") == 1);
        return veiculo;
    }
}