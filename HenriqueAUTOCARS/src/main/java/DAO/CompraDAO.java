package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Funcionario;
import model.Veiculo;

/**
 * @author eduar
 */
public class CompraDAO {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public int inserir(Compra compra) {
        String sql = "INSERT INTO compra (cliente_id, veiculo_id, funcionario_id, data_compra, valor_total) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, compra.getCliente().getId());
            stmt.setInt(2, compra.getVeiculo().getId());
            stmt.setInt(3, compra.getFuncionario().getId());
            stmt.setString(4, compra.getDataCompra().toString());
            stmt.setDouble(5, compra.getValorTotal());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    compra.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir compra.", ex);
        }
        return -1;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM compra WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar compra.", ex);
        }
    }

    public Compra buscarPorId(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar compra por id.", ex);
        }
        return null;
    }

    public List<Compra> listarTodos() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra ORDER BY data_compra DESC";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                compras.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar compras.", ex);
        }
        return compras;
    }

    public List<Compra> listarPorCliente(int clienteId) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE cliente_id = ? ORDER BY data_compra DESC";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    compras.add(mapear(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar compras do cliente.", ex);
        }
        return compras;
    }

    private Compra mapear(ResultSet rs) throws SQLException {
        Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
        Veiculo veiculo = veiculoDAO.buscarPorId(rs.getInt("veiculo_id"));
        Funcionario funcionario = funcionarioDAO.buscarPorId(rs.getInt("funcionario_id"));

        Compra compra = new Compra();
        compra.setId(rs.getInt("id"));
        compra.setCliente(cliente);
        compra.setVeiculo(veiculo);
        compra.setFuncionario(funcionario);
        compra.setDataCompra(LocalDate.parse(rs.getString("data_compra")));
        compra.setValorTotal(rs.getDouble("valor_total"));
        return compra;
    }
}