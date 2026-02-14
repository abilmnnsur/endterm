package org.example.endterm.service;

import org.example.endterm.cache.InMemoryCache;
import org.example.endterm.utils.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrendService {

    private final InMemoryCache cache = InMemoryCache.getInstance();
    private static final String TRENDING_TAGS_CACHE_KEY = "trending_tags";

    public Map<String, Integer> getTrendingTags() {

        if (cache.contains(TRENDING_TAGS_CACHE_KEY)) {
            return (Map<String, Integer>) cache.get(TRENDING_TAGS_CACHE_KEY);
        }

        Map<String, Integer> trends = new LinkedHashMap<>();

        String sql = """
            SELECT t.name, COUNT(at.tag_id) AS usage_count
            FROM tags t
            JOIN article_tags at ON t.tag_id = at.tag_id
            GROUP BY t.name
            ORDER BY usage_count DESC
        """;

        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                trends.put(rs.getString("name"), rs.getInt("usage_count"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        cache.put(TRENDING_TAGS_CACHE_KEY, trends);
        return trends;
    }
}
