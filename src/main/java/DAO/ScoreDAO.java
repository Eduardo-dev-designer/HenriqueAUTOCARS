package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Score;

/**
 *
 * @author eduar
 */
public class ScoreDAO {

    public int inserir(Score score) {
        String sql = "INSERT INTO score (cliente_id, pontuacao, classificacao) VALUES (?, ?, ?)";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, score.getClienteId());
            stmt.setInt(2, score.getPontuacao());
            stmt.setString(3, score.getClassificacao().name());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    score.setId(id);
                    return id;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir score.", ex);
        }
        return -1;
    }

    public boolean atualizar(Score score) {
        String sql = "UPDATE score SET pontuacao = ?, classificacao = ? WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, score.getPontuacao());
            stmt.setString(2, score.getClassificacao().name());
            stmt.setInt(3, score.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar score.", ex);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM score WHERE id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar score.", ex);
        }
    }

    public Score buscarPorClienteId(int clienteId) {
        String sql = "SELECT * FROM score WHERE cliente_id = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar score do cliente.", ex);
        }
        return null;
    }

    public List<Score> listarTodos() {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM score";
        try (Connection con = ConexaoBD.conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                scores.add(mapear(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar scores.", ex);
        }
        return scores;
    }

    /**
     * Lista os veiculos permitidos para uma classificacao de score, seguindo
     * a ideia original das tabelas listacarro_scorealto/bom/baixo: aqui
     * simplesmente filtramos os veiculos disponiveis, mas a faixa de preco
     * pode ser ajustada conforme a classificacao do cliente.
     */
    public List<Score> listarPorClassificacao(Score.Classificacao classificacao) {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM score WHERE classificacao = ?";
        try (Connection con = ConexaoBD.conectar();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, classificacao.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    scores.add(mapear(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar scores por classificacao.", ex);
        }
        return scores;
    }

    private Score mapear(ResultSet rs) throws SQLException {
        Score score = new Score();
        score.setId(rs.getInt("id"));
        score.setClienteId(rs.getInt("cliente_id"));
        score.setPontuacao(rs.getInt("pontuacao"));
        score.setClassificacao(Score.Classificacao.valueOf(rs.getString("classificacao")));
        return score;
    }
}