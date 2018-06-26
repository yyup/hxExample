package com.xmhx.cnlife.buzz.platform.model;

import com.xmhx.cnlife.core.model.BaseEntity;

public class MessagesDTO extends BaseEntity {

	public static final String REPLY_NOT = "1";//未应答
	public static final String REPLY_OK = "2";//已应答
	
	private static final long serialVersionUID = 1L;

	//接收人手机号码
	private String msgMobile;
	//消息提醒类型 1**-物业  2**-视频  3**-采集
	private String msgType;
	//消息标题
	private String msgTitle;
	//消息内容
	private String msgContent;
	//费用项目名称
	private String costName;
	//费用金额
	private String costMoney;
	//费用缴交截止日期
	private String costCutoff;
	//费用逾期天数
	private String costOverdue;
	//产生的滞纳金
	private String overdueFine;
	//总金额
	private String sumMoney;
	//合同编号
	private String contractNo;
	//合同类型
	private String contractType;
	//合同到期日期
	private String contractCutoff;
	//剩余天数
	private String daysRemaining;
	//描述
	private String discription;
	//应答 1无应答,2已应答
	private String ifreply;
	
	// 虚拟字段，由三方公司传参写入
	// 三方公司编号由枚举EnumUtils.ThreesidesNo获得结果
	private String threesidesNo;

	public String getMsgMobile() {
		return msgMobile;
	}
	public void setMsgMobile(String msgMobile) {
		this.msgMobile = msgMobile;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(String costMoney) {
		this.costMoney = costMoney;
	}
	public String getCostCutoff() {
		return costCutoff;
	}
	public void setCostCutoff(String costCutoff) {
		this.costCutoff = costCutoff;
	}
	public String getCostOverdue() {
		return costOverdue;
	}
	public void setCostOverdue(String costOverdue) {
		this.costOverdue = costOverdue;
	}
	public String getOverdueFine() {
		return overdueFine;
	}
	public void setOverdueFine(String overdueFine) {
		this.overdueFine = overdueFine;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractCutoff() {
		return contractCutoff;
	}
	public void setContractCutoff(String contractCutoff) {
		this.contractCutoff = contractCutoff;
	}
	public String getDaysRemaining() {
		return daysRemaining;
	}
	public void setDaysRemaining(String daysRemaining) {
		this.daysRemaining = daysRemaining;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getIfreply() {
		return ifreply;
	}
	public void setIfreply(String ifreply) {
		this.ifreply = ifreply;
	}
	public String getThreesidesNo() {
		return threesidesNo;
	}
	public void setThreesidesNo(String threesidesNo) {
		this.threesidesNo = threesidesNo;
	}
	
}
