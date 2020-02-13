package com.example.form;

/**
 * コメント投稿フォーム.
 * 
 * @author yuuki
 *
 */
public class CommentForm {
	
	/** コメント投稿者名 */
	private String name;
	
	/** コメント投稿内容 */
	private String content;
	
	/** 記事ID */
	private String articleId;
	

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
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
	}
}
