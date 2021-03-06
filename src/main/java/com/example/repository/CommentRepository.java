package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルにアクセスするためのリポジトリ.
 * 
 * @author yuuki
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** Commentクラスオブジェクトを生成するRowMapper. */
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER
		= (rs, i)->{
			Comment comment = new Comment();
			comment.setId(rs.getInt("id"));
			comment.setName(rs.getString("name"));
			comment.setContent(rs.getString("content"));
			comment.setArticleId(rs.getInt("article_id"));
			return comment;
		};
	
	
	/**
	 * コメントの記事IDで記事を検索.
	 * 
	 * @param articleId 記事ID 
	 * @return 同一の記事IDを持つコメント　idの降順
	 */
	
	public List<Comment> findByArticleId(int articleId) {
		String sql = "SELECT id, name, content, article_id FROM comments "
					+"WHERE article_id = :articleId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List <Comment> commentList = template.query(sql, param,COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	
	/**
	 * 投稿されたコメントをDBに保存する.
	 * 
	 * @param comment コメント
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments (name, content, article_id) " +
					 "VALUES (:name, :content, :articleId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName())
									.addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}
	
	
	/**
	 * 削除された記事に紐づいているコメントをDBから削除する.
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
	
	/**
	 * コメントをDBから削除する.
	 * 
	 * @param id 主キー
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM comments WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
}
