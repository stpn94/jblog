package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class PostVo {

	private int no;

	private Long categoryNo;

	@NotEmpty
	@Length(min = 2, max = 8)
	private String title;

	@NotEmpty
	@Length(min = 2, max = 8)
	private String contents;

	private String regDate;
	private String blogId;



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

}
