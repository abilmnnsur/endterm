package org.example.endterm.service;

import org.example.endterm.cache.InMemoryCache;
import org.example.endterm.model.Article;
import org.example.endterm.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final InMemoryCache cache = InMemoryCache.getInstance();

    private static final String ALL_ARTICLES_CACHE_KEY = "all_articles";

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        if (cache.contains(ALL_ARTICLES_CACHE_KEY)) {
            return (List<Article>) cache.get(ALL_ARTICLES_CACHE_KEY);
        }

        List<Article> articles = articleRepository.getAllArticles();
        cache.put(ALL_ARTICLES_CACHE_KEY, articles);
        return articles;
    }
}
