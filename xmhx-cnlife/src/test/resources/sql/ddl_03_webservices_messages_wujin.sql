/*
 * 三方消息表
 * @author 吴进 by 20161129
 */
CREATE TABLE webservices_messages (
  hxuuid varchar(100) NOT NULL COMMENT 'uuid',
  msgMobile varchar(100) DEFAULT NULL COMMENT '接收人手机号码',
  msgType   varchar(3) NOT NULL COMMENT '消息提醒类型 1**-物业  2**-视频  3**-采集',
  msgTitle  varchar(255) DEFAULT NULL COMMENT '消息标题',
  msgContent varchar(255) NOT NULL COMMENT '消息内容',
  costName varchar(255) DEFAULT NULL COMMENT '费用项目名称',
  costMoney varchar(100) DEFAULT NULL COMMENT '费用金额',
  costCutoff varchar(100) DEFAULT NULL COMMENT '费用缴交截止日期',
  costOverdue varchar(100) DEFAULT NULL COMMENT '费用逾期天数',
  overdueFine varchar(100) DEFAULT NULL COMMENT '产生的滞纳金',
  sumMoney varchar(100) DEFAULT NULL COMMENT '总金额',
  contractNo varchar(255) DEFAULT NULL COMMENT '合同编号',
  contractType varchar(100) DEFAULT NULL COMMENT '合同类型',
  contractCutoff varchar(100) DEFAULT NULL COMMENT '合同到期日期',
  daysRemaining varchar(100) DEFAULT NULL COMMENT '剩余天数',
  discription varchar(255) DEFAULT NULL COMMENT '描述',
  ifreply    char(1) DEFAULT 1 COMMENT '应答 1无应答,2已应答',
  data_state char(1) DEFAULT NULL COMMENT '是否有效 Y有效,N无效',
  created_by varchar(100) DEFAULT NULL COMMENT '创建人',
  date_created varchar(100) DEFAULT NULL COMMENT '创建时间',
  updated_by varchar(100) DEFAULT NULL COMMENT '更新人',
  date_updated varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (hxuuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三方消息表';
