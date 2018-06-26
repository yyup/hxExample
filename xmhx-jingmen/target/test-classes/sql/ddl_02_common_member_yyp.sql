/*
 * 用户表
 * @author 杨瑜萍 by 20161125
 */
CREATE TABLE common_member (
  hxuuid varchar(100) NOT NULL COMMENT 'PK',
  member_nick varchar(20) DEFAULT NULL COMMENT '昵称',  
  member_name varchar(20) DEFAULT NULL COMMENT '真实姓名',
  member_mobile varchar(20) NOT NULL COMMENT '手机号码',
  member_pwd varchar(50) NOT NULL COMMENT '密码',
  pattern_lock varchar(50) DEFAULT NULL COMMENT '手势密码',
  member_head varchar(255) DEFAULT NULL COMMENT '头像',
  first_char char(2) DEFAULT NULL COMMENT '首字母',
  member_sex char(1) DEFAULT NULL COMMENT '性别1:男2:女',
  member_email varchar(50) DEFAULT NULL COMMENT '邮箱',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (hxuuid),
  UNIQUE KEY member_mobile (member_mobile)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

  
  