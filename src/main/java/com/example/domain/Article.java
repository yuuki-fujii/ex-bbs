package com.example.domain;

import java.util.List;

/**
 * 記事の情報を表すドメインクラス.
 * 
 * @author yuuki
 *
 */
public class Article {
	/** 主キー */
	private Integer id;
	
	/** 投稿者名 */
	private String name;
	
	/** 投稿内容 */
	private String content;
	
	/** コメントリスト */
	private List<Comment> commmentList;
	

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
	public List<Comment> getCommmentList() {
		return commmentList;
	}
	public void setCommmentList(List<Comment> commmentList) {
		this.commmentList = commmentList;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commmentList=" + commmentList
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getContent()=" + getContent()
				+ ", getCommmentList()=" + getCommmentList() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
