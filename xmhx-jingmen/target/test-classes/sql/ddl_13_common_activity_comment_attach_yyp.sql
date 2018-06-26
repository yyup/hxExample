/*
 * 活动评论附件表
 * @author 杨瑜萍 by 20170203
 */
CREATE TABLE common_activity_comment_attach (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK(主键,自增)',
  hxuuid varchar(100) NOT NULL COMMENT 'uuid',
  attachname varchar(200) DEFAULT NULL COMMENT '附件上传名称',
  attachorigname varchar(200) DEFAULT NULL COMMENT '附件中文名称',
  attachpath varchar(200) DEFAULT NULL COMMENT '保存路径',
  attachtype varchar(10) NOT NULL COMMENT '附件类型(pngjpg	xtxlsxlsx)',
  attachmod varchar(10) NOT NULL COMMENT '(参看Enums.DLMopermodel代码) 11成员 12成员身份证 2 资讯',
  attachfkid varchar(32) DEFAULT NULL COMMENT '附件来源ID(FK)',
  data_state char(1) NOT NULL COMMENT '是否有效 Y-有效 N-无效',
  created_by varchar(100) DEFAULT NULL,
  date_created varchar(100) DEFAULT NULL,
  updated_by varchar(100) DEFAULT NULL,
  date_updated varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动评论附件表';


  
  