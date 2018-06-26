/*
 * 导航栏菜单表
 * @author 许彬阳 by 20160609
 */
CREATE TABLE xmhx_base_menu (
	id  				int(11) PRIMARY KEY not null auto_increment comment 'PK',
	menu_name  			varchar(255) NOT NULL comment '菜单名称',
	menu_code  			varchar(255) NOT NULL comment '菜单编号',
	parent_menu_code  	varchar(255) comment 'top一级菜单，子菜单则为其父菜单的menu_code',
	menu_desc  			varchar(255) comment '菜单描述',
	menu_url  			varchar(255) comment '菜单地址',
	menu_icon  			varchar(32) comment '菜单图标',
	weight  			int(2) comment '菜单排序权重,最小的排在最前边',
	hxuuid				varchar(32) comment 'UUID',
	data_state  		char(1) comment '是否有效 1有效,2无效',
	created_by  		varchar(100) comment '创建人',
	date_created  		varchar(100) comment '创建时间',
	updated_by  		varchar(100) comment '更新人',
	date_updated  		varchar(100) comment '更新时间'
);

  
  