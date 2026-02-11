package org.example.endterm.repository;

import org.example.endterm.model.Article;
import org.example.endterm.model.CS2Article;
import org.example.endterm.model.EsportsArticle;
import org.example.endterm.utils.util;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();

        String sql = """
            SELECT article_id, title, content, type, author_name, published_at, source
            FROM articles
        """;

        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("article_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String type = rs.getString("type");
                String author = rs.getString("author_name");
                LocalDateTime publishedAt = rs.getTimestamp("published_at").toLocalDateTime();
                String source = rs.getString("source");

                Article article;
                if ("CS2".equalsIgnoreCase(type)) {
                    article = new CS2Article(id, title, content, author, publishedAt, source);
                } else {
                    article = new EsportsArticle(id, title, content, author, publishedAt, source);
                }

                articles.add(article);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch articles", e);
        }

        return articles;
    }
}
