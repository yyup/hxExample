package com.xmhx.buzz.platform.model.manages.init;

import com.xmhx.buzz.platform.model.core.TempDTO;
import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.annotation.Id;

/**
 * 新闻实体类
 * 
 * @author 许彬阳 by 20160311
 * 
 */
@Entity
public class NewsDTO extends TempDTO {

	private int id;					// 主键
	private String notice_id;		// 资讯ID
	private String title;			// 标题
	private String notice_tp;		// 类型 '1新闻 2 政策 31 广告链接 32广告图文',
	private String content;			// 内容
	
	//文件（单个）
	private AttachDTO attachDTO;
	
	@Id
	@AutoIncrement
	@Column(columnName = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(columnName="notice_id")
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	@Column(columnName = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(columnName = "notice_tp")
	public String getNotice_tp() {
		return notice_tp;
	}
	public void setNotice_tp(String notice_tp) {
		this.notice_tp = notice_tp;
	}
	@Column(columnName = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(columnName = "attachDTO")
	public AttachDTO getAttachDTO() {
		return attachDTO;
	}
	public void setAttachDTO(AttachDTO attachDTO) {
		this.attachDTO = attachDTO;
	}
	
	
}
