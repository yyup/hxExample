/*
 * 用户应用关系表
 * @author 黄建明 by 20161221
 */
CREATE TABLE sys_user_apply (
	hxuuid		varchar(100) NOT NULL COMMENT 'PK',
	user_mobile varchar(11) NOT NULL COMMENT '用户手机号',
	apply_code varchar(100) NOT NULL COMMENT '应用编码',
	data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
	created_by varchar(100) DEFAULT NULL COMMENT '创建人',
	date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
	updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
	date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (hxuuid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户应用关系表';
Alter table sys_user_apply add unique(user_mobile,apply_code);

  
  