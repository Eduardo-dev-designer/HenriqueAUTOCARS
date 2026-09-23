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
import model.Funcionario;
import model.Locacao;
import model.Veiculo;

/**
 * @author eduar
 */
public class LocacaoDAO {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public int inserir(Locacao locacao) {
        String sql = "INSERT INTO locacao (cliente_id, veiculo_id, funcionario_id, data_inicio, data_fim, valor_diaria) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, locacao.getCliente().getId());
            stmt.setInt(2, locacao.getVeiculo().getId());
            stmt.setInt(3, locacao.getFuncionario().getId());
            stmt.setString(4, locacao.getDataInicio().toString());
            stmt.setString(5, locacao.getDataFim().toString());
            stmt.setDouble(6, locacao.getValorDiaria());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    locacao.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir locacao.", ex);
        }
        return -1;
    }

    public boolean atualizar(Locacao locacao) {
        String sql = "UPDATE locacao SET data_inicio = ?, data_fim = ?, valor_diaria = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, locacao.getDataInicio().toString());
            stmt.setString(2, locacao.getDataFim().toString());
            stmt.setDouble(3, locacao.getValorDiaria());
            stmt.setInt(4, locacao.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar locacao.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM locacao WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar locacao.", ex);
        }
    }

    public Locacao buscarPorId(int id) {
        String sql = "SELECT * FROM locacao WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar locacao por id.", ex);
        }
        return null;
    }

    public List<Locacao> listarTodos() {
        List<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacao ORDER BY data_inicio DESC";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                locacoes.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar locacoes.", ex);
        }
        return locacoes;
    }

    public List<Locacao> listarPorCliente(int clienteId) {
        List<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT * FROM locacao WHERE cliente_id = ? ORDER BY data_inicio DESC";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    locacoes.add(mapear(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar locacoes do cliente.", ex);
        }
        return locacoes;
    }

    private Locacao mapear(ResultSet rs) throws SQLException {
        Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
        Veiculo veiculo = veiculoDAO.buscarPorId(rs.getInt("veiculo_id"));
        Funcionario funcionario = funcionarioDAO.buscarPorId(rs.getInt("funcionario_id"));

        Locacao locacao = new Locacao();
        locacao.setId(rs.getInt("id"));
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setFuncionario(funcionario);
        locacao.setDataInicio(LocalDate.parse(rs.getString("data_inicio")));
        locacao.setDataFim(LocalDate.parse(rs.getString("data_fim")));
        locacao.setValorDiaria(rs.getDouble("valor_diaria"));
        return locacao;
    }
}