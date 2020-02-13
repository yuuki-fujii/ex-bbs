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
	 * @return 同一の記事IDを持つコメント　idの昇順
	 */
	public List<Comment> findByArticle(int articleId) {
		String sql = "SELECT id, name, content, article_id FROM comments "
					+"WHERE article_id = :articleId ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List <Comment> commentList = template.query(sql, param,COMMENT_ROW_MAPPER);
		return commentList;
	}
	
}
