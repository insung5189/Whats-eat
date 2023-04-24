package org.example.service;

import org.example.Container;
import org.example.dto.Article;
import org.example.repository.ArticleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = Container.articleRepository;
    }

    public int write(String title, String content, int hit, int like, int account_id, int mapping_id) {
        return articleRepository.write(title, content, hit, like, account_id, mapping_id);
    }

    public boolean articleExists(int id) {
        return articleRepository.articleExists(id);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }

    public void update(int id, String title, String content) {
        articleRepository.update(id, title, content);
    }

    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }

    public void increaseHit(int id) {
        articleRepository.increaseHit(id);
    }

    public List<Article> getForPrintArticleById(int page, int pageItemCount, String searchKeyword) {
        int limitFrom = (page - 1) * pageItemCount;
        int limitTake = pageItemCount;

        Map<String, Object> args = new HashMap<>();
        args.put("limitFrom", limitFrom);
        args.put("limitTake", limitTake);
        return articleRepository.getArticles(args, searchKeyword);
    }
}
