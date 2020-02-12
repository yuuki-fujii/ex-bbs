package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * commentsテーブルにアクセスするためのリポジトリ.
 * 
 * @author yuuki
 *
 */
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
}
