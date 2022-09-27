package model.dto;

public class Notice {
	int no;
	String title;
	String writer;
	String date;
	String info;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Notice(int no, String title, String writer, String date, String info) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.date = date;
		this.info = info;
	}
	
	
	
	
	
	
}
