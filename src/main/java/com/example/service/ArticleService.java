package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事に関する情報を扱う業務処理を行う.
 * 
 * @author yuuki
 *
 */
@Service
@Transactional
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	
	/**
	 * 全ての記事を取得する.
	 * 
	 * @return DBに保存されている全ての記事
	 */
	public List <Article> getAllArticles (){
		List<Article> articleList = articleRepository.findAll();
		return articleList;
	}
}
