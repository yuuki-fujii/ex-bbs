package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * @author yuuki
 *
 */
/**
 * 記事一覧画面の操作を行うコントローラ.
 * 
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
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
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
			article.setCommmentList(commentRepository.findByArticleId(article.getId()));
		}
		
		model.addAttribute("articleList", articleList);
		return "index";
	}
	
	/**
	 * 記事を投稿する.
	 * 
	 * @param form　記事投稿フォーム
	 * @return　記事投稿画面
	 */
	@RequestMapping("/insert-article")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article/to-index";
	}
	
	/**
	 * コメントを投稿する.
	 * 
	 * @param form　コメント投稿フォーム
	 * @param articleId　記事ID
	 * @return 記事投稿画面
	 */
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.getIntArticleId());
		commentRepository.insert(comment);
		return "redirect:/article/to-index";
	}
	
	/**
	 * 投稿を削除する
	 * 
	 * @param id　主キー
	 * @return　記事一覧画面
	 */
	@RequestMapping("/delete-article")
	public String deleteArticle(int id) {
		commentRepository.deleteByArticleId(id);
		articleRepository.deleteById(id);
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
