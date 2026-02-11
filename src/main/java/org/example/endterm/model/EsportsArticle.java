package org.example.endterm.model;

import java.time.LocalDateTime;

public class EsportsArticle extends Article {

    private String source;

    public EsportsArticle(int articleId,
                          String title,
                          String content,
                          String authorName,
                          LocalDateTime publishedAt,
                          String source) {

        super(articleId, title, content, "ESPORTS", authorName, publishedAt);
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getCategory() {
        return "ESPORTS NEWS";
    }
}
