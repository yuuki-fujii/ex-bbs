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
import com.example.repository.CommentRepository;

/**
 * @author yuuki
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
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
		
		for (Article article : articleList) {
			article.setCommmentList(commentRepository.findByArticle(article.getId()));
		}
		
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
	@RequestMapping("/insert")
	public String insert(ArticleForm form) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.insertArticle(article);
		return "redirect:/article/to-index";
	}
	
	/**
	 * 投稿を削除する
	 * 
	 * @param id　主キー
	 * @return　
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(Integer id) {
		articleRepository.deleteArticle(id);
		return "redirect:/article/to-index";
	}
	
	
	/**
	 * ダブルサブミット対策　リダイレクト用
	 * 
	 * @param model リクエストスコープ
	 * @return　記事一覧画面
	 */
	@RequestMapping("/to-index")
	public String toIndex(Model model) {
		return index(model);
	}
}
