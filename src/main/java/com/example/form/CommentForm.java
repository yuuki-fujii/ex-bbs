package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * コメント投稿フォーム.
 * 
 * @author yuuki
 *
 */
public class CommentForm {
	
	/** コメント投稿者名 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;
	
	/** コメント投稿内容 */
	@NotBlank(message = "コメントを入力してください")
	private String content;
	
	/** 記事ID */
	private Integer articleId;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
}
