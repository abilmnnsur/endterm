package org.example.endterm.repository;

import org.example.endterm.model.Team;
import org.springframework.stereotype.Repository;
import org.example.endterm.utils.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository {

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        String sql = "SELECT team_id, name, country, team_rating FROM teams";

        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getDouble("team_rating")
                );
                teams.add(team);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch teams from database", e);
        }


        return teams;
    }
}
