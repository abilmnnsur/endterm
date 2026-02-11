package org.example.endterm.repository;

import org.example.endterm.model.Article;
import org.example.endterm.model.CS2Article;
import org.example.endterm.model.EsportsArticle;
import org.example.endterm.utils.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();

        String sql = """
                SELECT article_id,
                       title,
                       content,
                       type,
                       author_name,
                       published_at
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
                LocalDateTime publishedAt =
                        rs.getTimestamp("published_at").toLocalDateTime();

                Article article;

                if ("CS2".equalsIgnoreCase(type)) {
                    article = new CS2Article(
                            id,
                            title,
                            content,
                            author,
                            publishedAt,
                            "HLTV"
                    );
                } else {
                    article = new EsportsArticle(
                            id,
                            title,
                            content,
                            author,
                            publishedAt,
                            "Esports News"
                    );
                }

                articles.add(article);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
