/*
 * 附件表
 * @author 黄建明 by 20170103
 */
CREATE TABLE sys_attach (
  hxuuid varchar(50) NOT NULL COMMENT 'PK',
  attach_name varchar(200) DEFAULT NULL COMMENT '附件上传名称',
  attach_orig_name varchar(200) DEFAULT NULL COMMENT '附件原始名称',
  attach_path varchar(200) DEFAULT NULL COMMENT '附件保存路径',
  attach_type varchar(10) DEFAULT NULL COMMENT '附件类型(png|jpg|gif|txt|doc|docx|xls|xlsx)',
  attach_mod varchar(10) DEFAULT NULL COMMENT '附件来源模块 1 新闻 2 政策',
  attachfk_id varchar(50) DEFAULT NULL COMMENT '附件来源ID',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (hxuuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

  
  