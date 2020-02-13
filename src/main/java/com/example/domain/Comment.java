package com.example.domain;

/**
 * 記事へのコメントに関する情報を表すドメインクラス.
 * 
 * @author yuuki
 *
 */
public class Comment {
	
	/** 主キー */
	private Integer id;
	
	/** 投稿者名 */
	private String name;
	
	/** 投稿内容 */
	private String content;
	
	/** コメント対象の記事のID 外部キー */
	private Integer articleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getContent()=" + getContent()
				+ ", getArticleId()=" + getArticleId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
