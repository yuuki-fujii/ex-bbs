package com.example.form;

/**
 * 記事投稿フォーム
 * 
 * @author yuuki
 *
 */
public class ArticleForm {
	/** 主キー */
	private String id;
	
	/** 投稿者名 */
	private String name;
	
	/** 投稿内容 */
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
}
