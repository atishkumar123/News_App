package com.atish.navigationdrawer.modals;

import java.util.List;

public class News {
    private String status;
    private float totalResults;
    private List<Article> articles;


    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }



    // Getter Methods

    public String getStatus() {
        return status;
    }

    public float getTotalResults() {
        return totalResults;
    }

    // Setter Methods

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(float totalResults) {
        this.totalResults = totalResults;
    }
}
