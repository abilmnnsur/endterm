package org.example.endterm.repository;

import org.example.endterm.model.Player;
import org.example.endterm.utils.util;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        String sql = "SELECT * FROM players";

        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Player p = new Player(
                        rs.getInt("player_id"),
                        rs.getInt("team_id"),
                        rs.getString("nickname"),
                        rs.getString("real_name"),
                        rs.getString("country"),
                        rs.getDouble("player_rating"),
                        rs.getInt("kills"),
                        rs.getInt("deaths"),
                        rs.getInt("matches_played")
                );
                players.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players", e);
        }

        return players;
    }
}
