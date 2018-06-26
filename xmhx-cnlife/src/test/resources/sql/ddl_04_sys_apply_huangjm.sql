/*
 * 系统应用表
 * @author 黄建明 by 20161221
 */
CREATE TABLE sys_apply (
  hxuuid varchar(100) NOT NULL COMMENT 'PK',
  apply_name varchar (100) NOT NULL COMMENT '应用名称',
  apply_code varchar (100) NOT NULL COMMENT '应用编码',
  apply_icon varchar(100) COMMENT '应用图标',
  apply_title varchar (100) COMMENT '应用副标题（暂未使用）',
  apply_desc text COMMENT '应用简介（暂未使用）',
  apply_belong varchar(2) COMMENT '归属(1移动办公 2企业管理 3其他服务)',
  apply_ifprime char(1) DEFAULT '0' COMMENT '是否推荐(0否 1是)（暂未使用）',
  apply_ifwap char(1) DEFAULT '0' COMMENT '应用类型(0原生 1wap)',
  apply_url varchar(200) COMMENT 'URL访问地址',
  apply_no int(3) DEFAULT NULL COMMENT '排序',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (hxuuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统应用表';

  
  