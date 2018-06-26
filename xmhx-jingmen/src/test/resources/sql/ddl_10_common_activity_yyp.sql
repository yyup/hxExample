/*
 * 活动表
 * @author 杨瑜萍 by 20170203
 */
CREATE TABLE common_activity (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  hxuuid varchar(100) NOT NULL COMMENT 'UUID',
  tp char(1) NOT NULL COMMENT '类型:0 其它 1聚会 2 沙龙 3 培训 4 路演 ',
  title varchar(200) DEFAULT NULL COMMENT '标题',
  content text NOT NULL COMMENT '内容',
  stime varchar(100) NOT NULL COMMENT '活动开始时间',
  etime varchar(100) DEFAULT NULL COMMENT '活动结束时间',
  overtime varchar(100) DEFAULT NULL COMMENT '截止时间',
  address varchar(200) DEFAULT NULL COMMENT '地点',
  contact varchar(200) DEFAULT NULL COMMENT '联系人',
  tel varchar(100) DEFAULT NULL COMMENT '联系电话',
  longitude varchar(100) DEFAULT NULL COMMENT '地点经度',
  latitude varchar(100) DEFAULT NULL COMMENT '地点维度',
  enternum int(11) DEFAULT NULL COMMENT '报名人数',
  praisenum int(11) DEFAULT NULL COMMENT '点赞数',
  verifystatus char(1) DEFAULT NULL COMMENT '审核状态(0待审核 1审核通过 2审核不通过)',
  verifytorid varchar(100) DEFAULT NULL COMMENT '审核人FK',
  verifytor varchar(100) DEFAULT NULL COMMENT '审核人名称',
  dateverify varchar(100) DEFAULT NULL COMMENT '审核时间',
  verifydesc text COMMENT '审核意见',
  activiissueid varchar(100) NOT NULL COMMENT '活动发布人FK',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';


  
  