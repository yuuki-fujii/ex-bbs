package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Article;

/**
 * articlesテーブルにアクセスするためのリポジトリ.
 * 
 * @author yuuki
 *
 */
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** Articleクラスオブジェクトを生成するRowMapper. */
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER
		= (rs, i)->{
			Article article = new Article();
			article.setId(rs.getInt("id"));
			article.setName(rs.getString("name"));
			article.setContent(rs.getString("content"));
			return article;
		};
		
	/**
	 * 全ての記事を取得する.
	 * 
	 * @return DBに保存されている全ての記事
	 */
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM articles ";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
}
