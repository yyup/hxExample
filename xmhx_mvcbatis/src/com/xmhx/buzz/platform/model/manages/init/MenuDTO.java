package com.xmhx.buzz.platform.model.manages.init;

import com.xmhx.buzz.utils.EnumUtils.DataType;
import com.xmhx.core.annotation.AutoIncrement;
import com.xmhx.core.annotation.Column;
import com.xmhx.core.annotation.Entity;
import com.xmhx.core.annotation.Id;
import com.xmhx.core.model.BaseEntity;


/**
 * 系统菜单实体
 * @author 吴进 by 20150819
 * 
 */
@Entity
public class MenuDTO extends BaseEntity {
	
	private int id;							// PK
	private String menu_name;				// 菜单名称
	private String menu_code;				// 菜单编码
	private String parent_menu_code;		// 若为一级菜单，则此字段取值为：top,若为控制台，则取值：console，子菜单则为其父菜单的menu_code
	private String menu_desc;				// 菜单描述
	private String menu_url;				// 菜单地址
	private String menu_icon;				// 菜单图标
	private int weight;						// 菜单排序权重，递增排序，最小的排在最前边

	@Id
	@AutoIncrement
	@Column(columnName="id", dataType=DataType.INT)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(columnName="menu_name")
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	@Column(columnName="menu_code")
	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	@Column(columnName="parent_menu_code")
	public String getParent_menu_code() {
		return parent_menu_code;
	}

	public void setParent_menu_code(String parent_menu_code) {
		this.parent_menu_code = parent_menu_code;
	}

	@Column(columnName="menu_desc")
	public String getMenu_desc() {
		return menu_desc;
	}

	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}

	@Column(columnName="menu_url")
	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	
	@Column(columnName="menu_icon")
	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	@Column(columnName="weight",dataType=DataType.INT)
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
