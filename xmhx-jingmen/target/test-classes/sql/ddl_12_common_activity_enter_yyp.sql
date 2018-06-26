/*
 * 活动报名表
 * @author 杨瑜萍 by 20170203
 */
CREATE TABLE common_activity_enter (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  hxuuid varchar(100) NOT NULL COMMENT 'uuid',
  activityid varchar(100) DEFAULT NULL COMMENT '活动id',
  entertel varchar(100) NOT NULL COMMENT '电话',
  enternm varchar(100) NOT NULL COMMENT '姓名',
  entercompany varchar(200) DEFAULT NULL COMMENT '公司',
  enterremark text COMMENT '备注',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动报名表';


  
  