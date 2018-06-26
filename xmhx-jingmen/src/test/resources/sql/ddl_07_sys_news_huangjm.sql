/*
 * 新闻表
 * @author 黄建明 by 20170103
 */
CREATE TABLE sys_news (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  hxuuid varchar(100) NOT NULL COMMENT 'UUID',
  read_count int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  news_title varchar(200) NOT NULL COMMENT '标题',
  news_subtitle varchar(200) DEFAULT NULL COMMENT '副标题',
  news_type varchar(10) DEFAULT NULL COMMENT '新闻类型 （预留）',
  news_content text NOT NULL COMMENT '内容',
  verify_status char(1) NOT NULL DEFAULT '0' COMMENT '审核状态(0待审核 1审核通过 2审核不通过)',
  verifytor_id varchar(100) DEFAULT NULL COMMENT '审核人ID',
  verifytor varchar(100) DEFAULT NULL COMMENT '审核人姓名',
  verify_date varchar(100) DEFAULT NULL COMMENT '审核时间',
  verify_desc text DEFAULT NULL COMMENT '审核意见',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表';

  
  