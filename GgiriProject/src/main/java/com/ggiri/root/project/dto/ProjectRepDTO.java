
package com.ggiri.root.project.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
CREATE TABLE reply (
no NUMBER(10) not null,          -- 댓글 번호
bno NUMBER(10) primary key,      -- 게시물 번호
grp NUMBER(10) not null,         -- 대댓글 번호
grps NUMBER(10) not null,        -- 대댓글 순서
grpl NUMBER(10) not null,        -- 댓글의 깊이(모댓글이면 0, 답글이면 1)
id VARCHAR2(100) not null,       -- 회원ID
content VARCHAR2(1000),          -- 내용
wdate DATE DEFAULT SYSDATE,      -- 작성일
CONSTRAINT fk_test1 FOREIGN KEY(bno) REFERENCES ggiriProject(projectNum) ON DELETE CASCADE,
CONSTRAINT fk_test2 FOREIGN KEY(id) REFERENCES ggiriMember(id) ON DELETE CASCADE
);
*/

public class ProjectRepDTO {
	
	private int no; 		// 댓글 번호
	private int bno; 		// 게시물 번호
	private String id; 		// 회원ID
	private String content; // 내용
	private String wdate; 	// 작성일
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getBno() {
		return bno;
	}
	
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWdate() {
		return wdate;
	}
	
	public void setWdate(Timestamp wdate) {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		this.wdate = format.format(wdate);
	}
	
	
	
	
	
	
	
	
}
