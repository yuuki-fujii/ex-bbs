package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * articlesテーブルにアクセスするためのリポジトリ.
 * 
 * @author yuuki
 *
 */
@Repository
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
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	
	/**
	 * 投稿された記事をDBに保存する
	 */
	public void insertArticle(Article article) {
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource()
				 					.addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
	
	/**
	 * 投稿をデータベースから削除する
	 */
	public void deleteArticle(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
