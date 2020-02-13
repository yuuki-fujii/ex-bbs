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
