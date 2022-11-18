package com.crud.bean;

import java.io.File;
import java.util.Date;

public class BoardVO {
	private int seq;
	private String category;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	private String photo;
	private File file;

	public File getFile(){return file;}
	public void setFile(String filename){
		this.file = new File("upload/"+filename);
	}

	public String getFilename(){return photo;}
	public void setFilename(String photo){
		this.photo = photo;
	}

	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
