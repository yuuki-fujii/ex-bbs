package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * @author yuuki
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	/**
	 * 記事投稿画面に遷移.
	 * 
	 * @param model リクエストスコープ
	 * @return　記事投稿画面
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		List <Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		return "index";
	}
	
	/**
	 * 記事を投稿する.
	 * 
	 * @param form　記事投稿フォーム
	 * @param model　リクエストスコープ
	 * @return　記事投稿画面
	 */
	@RequestMapping("/create")
	public String create(ArticleForm form) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.saveArticle(article);
		return "redirect:/article/to-index";
	}
	
	@RequestMapping("/to-index")
	public String toIndex(Model model) {
		return index(model);
	}
}
