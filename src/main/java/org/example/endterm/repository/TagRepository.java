package org.example.endterm.repository;

import org.example.endterm.model.Tag;
import org.example.endterm.utils.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TagRepository {
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT tag_id, name FROM tags";

        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tags.add(new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }
}
