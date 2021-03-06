package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

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
//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER
//		= (rs, i)->{
//			Article article = new Article();
//			article.setId(rs.getInt("id"));
//			article.setName(rs.getString("name"));
//			article.setContent(rs.getString("content"));
//			return article;
//		};
	
	/**
	 * 結合したテーブルを表示するResultSetExtractor.
	 */
	
	// コメントアウトした分はレビュー前
	private static final ResultSetExtractor<List<Article>>  ARTICLE_RESULT_SET_EXTRACTOR
		= (rs)->{
			List <Article> articleList = new ArrayList<>();
//			Map<Integer ,Article> beforeAritcleIdMap = new LinkedHashMap<>();
			int beforeAritcleId = 0;
//			Article article = null;
			List <Comment> commentList = null;
			while (rs.next()) {
				Integer nowArticleId = rs.getInt("article_id");
//				Integer commentId = rs.getInt("comment_id");
//				List <Comment> commentList = new ArrayList<>();
				
//				article = beforeAritcleIdMap.get(articleId);
				if (beforeAritcleId != nowArticleId) {
//				if (article == null) {
					Article article = new Article();
					article.setId(nowArticleId);
					article.setName(rs.getString("article_name"));
					article.setContent(rs.getString("article_content"));
					commentList = new ArrayList<>();
					article.setCommmentList(commentList);
					articleList.add(article);
//					beforeAritcleIdMap.put(articleId, article);
				}
				
				if(rs.getInt("comment_id") != 0) {
					Comment comment = new Comment();
					comment.setId(rs.getInt("comment_id"));
					comment.setName(rs.getString("comment_name"));
					comment.setContent(rs.getString("comment_content"));
					comment.setArticleId(rs.getInt("comment_article_id"));
					
//					article = articleList.get(articleList.size()- 1);
//					commentList = article.getCommmentList();
					commentList.add(comment);
				}		
				
				beforeAritcleId = nowArticleId;
			}
			return articleList;
		};
		
		
	
		
	/**
	 * 全ての記事を取得する.
	 * 
	 * @return DBに保存されている全ての記事
	 */
	public List<Article> findAll(){
		String sql = "SELECT a.id AS article_id , a.name AS article_name, a.content AS article_content," +
					 "c.id AS comment_id, c.name AS comment_name, c.content AS comment_content, c.article_id AS comment_article_id " + 
					 "FROM articles a LEFT JOIN comments c " + 
					 "ON a.id = c.article_id ORDER BY a.id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
		return articleList;
	}
	
	
	/**
	 * 投稿された記事をDBに保存する.
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource()
				 					.addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
	
	/**
	 * 投稿をDBから削除する.
	 */
	public void deleteById(Integer id) {
		String sql = "WITH deleted AS (DELETE FROM articles WHERE id = :id RETURNING id) " + 
				     "DELETE FROM comments WHERE article_id  IN (SELECT article_id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
