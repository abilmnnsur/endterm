package org.example.endterm.repository;

import org.example.endterm.model.Article;
import org.example.endterm.model.CS2Article;
import org.example.endterm.model.EsportsArticle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbc;

    public ArticleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Article> getAllArticles() {
        String sql = """
            SELECT article_id, title, content, type, author_name, published_at
            FROM articles
            ORDER BY published_at DESC
        """;

        return jdbc.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("article_id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String type = rs.getString("type");
            String author = rs.getString("author_name");
            LocalDateTime publishedAt =
                    rs.getTimestamp("published_at").toLocalDateTime();

            return switch (type) {
                case "CS2" -> new CS2Article ( id, title, content, author, publishedAt,"Valve / CS2");
                case "ESPORTS", "MATCH_REPORT" ->
                        new EsportsArticle(id, title, content, author, publishedAt,"HLTV / Esports Media");
                default ->
                        throw new IllegalStateException("Unknown article type: " + type);
            };
        });
    }
}
