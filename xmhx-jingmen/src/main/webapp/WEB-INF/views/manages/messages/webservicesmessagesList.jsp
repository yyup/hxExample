<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>消息管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<%@include file="/WEB-INF/views/include/source.jsp" %>
	</head>
	<style>
	 .toolbar{
	 	width:=100%"; 
	 	border="0"; 
	 	cellpadding="0";
	 	cellspacing="0";
	 }
	</style>
	<body class="body1">
		<div class="formInfo">
			推送手机号码：
			<input autocomplete="off" name="msgMobile" id="msgMobile" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>&nbsp;&nbsp;
			消息标题：
			<input autocomplete="off" name="msgTitle" id="msgTitle" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:queryMessages();">查询</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-recover',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:delMessages();">删除</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:pushMessages();">消息推送</a>
		</div>
		
		<!-- 中间文字说明 -->
		<div class="textInfo"></div>
		
		<!-- 列表 -->
		<div class="tableInfo">
			<table id="webservicesMessagesDatagrid" class="easyui-datagrid" singleSelect="false"
				url="pagelist.hx"  border="false" striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="20" pageList="[1,5,10,20,50,100,200,500,1000]">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th data-options="field:'hxuuid'" hidden="true">hxuuid</th>
						<th data-options="field:'msgMobile', sortable:'true'" width="15%" align="center">推送手机号码</th>
						<th data-options="field:'msgType', sortable:'true'" width="25%" align="center" formatter="fmtmsgType">消息类型</th>
						<th data-options="field:'msgTitle', sortable:'true'" width="25%" align="center">消息标题</th>
						<th data-options="field:'ifreply', sortable:'true'" width="10%" align="center" formatter="fmtifreply">移动端应答</th>
						<th data-options="field:'date_created', sortable:'true'" width="15%" align="center">创建时间</th>
						<th data-options="field:'fmtdetail', sortable:'true'" width="10%" align="center" formatter="fmtdetail">查看明细</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="CostMessagedetailDiv" class="easyui-window" style="width:600px;height:350px;padding:10px;" 
			title="物业类消息详情" minimizable="false" maximizable="false" collapsible="false" closed="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr style="height: 30px;">
					<td width="150px" align="right">费用项目名称：</td>
					<td>
						<span id="costNamedt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">费用金额：</td>
					<td>
						<span id="costMoneydt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">费用缴交截止日期：</td>
					<td>
						<span id="costCutoffdt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">费用逾期天数：</td>
					<td>
						<span id="costOverduedt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">产生的滞纳金：</td>
					<td>
						<span id="overdueFinedt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">总金额：</td>
					<td>
						<span id="sumMoneydt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">说明：</td>
					<td>
						<span id="costdiscriptiondt" ></span>
					</td>
				</tr>
			</table>
		</div>
		<div id="ContractMessagedetailDiv" class="easyui-window" style="width:600px;height:350px;padding:10px;" 
			title="合同类消息详情" minimizable="false" maximizable="false" collapsible="false" closed="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr style="height: 30px;">
					<td width="150px" align="right">合同编号：</td>
					<td>
						<span id="contractNodt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">合同类型：</td>
					<td>
						<span id="contractTypedt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">合同到期日期：</td>
					<td>
						<span id="contractCutoffdt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">剩余天数：</td>
					<td>
						<span id="daysRemainingdt" ></span>
					</td>
				</tr>
				<tr style="height: 30px;">
					<td width="150px" align="right">说明：</td>
					<td>
						<span id="contractdiscriptiondt" ></span>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="CollectiveMessagedetailDiv" class="easyui-window" style="width:600px;height:350px;padding:10px;" 
			title="集采类消息详情" minimizable="false" maximizable="false" collapsible="false" closed="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr style="height: 30px;">
					<td width="100px" align="right">说明：</td>
					<td>
						<span id="collectivediscriptiondt" ></span>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript">
			// 查询
			function queryMessages() {
				var _msgMobile=$("#msgMobile").val();
				var _msgTitle=$("#msgTitle").val();
				$("#webservicesMessagesDatagrid").datagrid('load', {
					url: "pagelist.hx",
					msgMobile:_msgMobile,
					msgTitle: _msgTitle
				});
			}
			// 回车查询
			function toQuery(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					queryMessages();
				}
			}
			//格式化消息类型
			function fmtmsgType(val,row) {
				if (val == '101') {
					return '物业费逾期提醒(101)';
				} else if(val == '102') {
					return '租赁合同即将到期提醒(102)';
				} else if(val == '301') {
					return '买家下单提醒-已下单(301)';
				} else if(val == '302') {
					return '买家下单提醒-新订单(302)';
				} else if(val == '303') {
					return '已接单提醒(303)';
				} else if(val == '304') {
					return '已发货提醒(304)';
				} else if(val == '305') {
					return '买家退单提醒(305)';
				} else if(val == '306') {
					return '商家退单提醒(306)';
				}
			}
			//格式应答状态
			function fmtifreply(val,row) {
				if (val == '1') {
					return '未应答';
				} else if(val == '2') {
					return '已应答';
				}
			}
			//详情
			function fmtdetail(val,row) {
				var _html = '';
				_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="javascript:messageDetail(\'' +row.hxuuid+ '\');"><span class="l-btn-left"><span class="l-btn-text icon-search l-btn-icon-left">详情</span></span></a>';
				return _html;
			}
			//查看详情
			function messageDetail(hxuuid) {
				$.ajax({
					url : "queryMessageById.hx",
					data : "hxuuid=" + hxuuid,
					type : "post",
					success : function(data) {
						if (data.status) {
							//赋值
							$("#hxuuiddt").val(data.attr.messageDTO.hxuuid);//hxuuid
							$("#msgMobiledt").html(data.attr.messageDTO.msgMobile);//手机号码
							$("#msgTitledt").html(data.attr.messageDTO.msgTitle);//标题
							$("#msgContentdt").html(data.attr.messageDTO.msgContent);//内容
							$("#date_createddt").html(data.attr.messageDTO.date_created);//时间
							var ifreply = data.attr.messageDTO.ifreply;
							if (ifreply == '1') {
								$("#ifreplydt").html("未应答");//应答状态
							} else if (ifreply == '2') {
								$("#ifreplydt").html("已应答");//应答状态
							}
							$("#costNamedt").html(data.attr.messageDTO.costName);
							$("#costMoneydt").html(data.attr.messageDTO.costMoney);
							$("#costCutoffdt").html(data.attr.messageDTO.costCutoff);
							$("#costOverduedt").html(data.attr.messageDTO.costOverdue);
							$("#overdueFinedt").html(data.attr.messageDTO.overdueFine);
							$("#sumMoneydt").html(data.attr.messageDTO.sumMoney);
							$("#contractNodt").html(data.attr.messageDTO.contractNo);
							$("#contractTypedt").html(data.attr.messageDTO.contractType);
							$("#contractCutoffdt").html(data.attr.messageDTO.contractCutoff);
							$("#daysRemainingdt").html(data.attr.messageDTO.daysRemaining);
							
							var msgType = data.attr.messageDTO.msgType;
							if (msgType.substring(0, 1) == '1') {
								// 物业
								if (msgType == '101') {//物业逾期提醒
									$("#msgTypedt").html("物业费逾期提醒(101)");
									$("#costdiscriptiondt").html(data.attr.messageDTO.discription);
									CostOpenWin();
								} else if (msgType == '102') {//合同到期
									$("#msgTypedt").html("租赁合同即将到期提醒(102)");
									$("#contractdiscriptiondt").html(data.attr.messageDTO.discription);
									ContractOpenWin();
								}
							} else if (msgType.substring(0, 1) == '2') {
								// 视频								
							} else if (msgType.substring(0, 1) == '3') {
								// 集采
								if (msgType == '301') {
									$("#msgTypedt").html("买家下单提醒-已下单(301)");
								} else if (msgType == '302') {
									$("#msgTypedt").html("买家下单提醒-新订单(302)");
								} else if (msgType == '303') {
									$("#msgTypedt").html("已接单提醒(303)");
								} else if (msgType == '304') {
									$("#msgTypedt").html("已发货提醒(304)");
								} else if (msgType == '305') {
									$("#msgTypedt").html("买家退单提醒(305)");
								} else if (msgType == '306') {
									$("#msgTypedt").html("商家退单提醒(306)");
								}
								$("#collectivediscriptiondt").html(data.attr.messageDTO.discription);
								CollectiveOpenWin();
							}
						} else if (json == -1) {
							displayMsg(data.info, false);
						}
					}
				});
			}
			// 打开物业窗口
			function CostOpenWin() {
				$("#CostMessagedetailDiv").window("open");
			}
			// 关闭物业窗口
			function CostHideWin() {
				$("#CostMessagedetailDiv").window("close");
			}
			// 打开合同窗口
			function ContractOpenWin() {
				$("#ContractMessagedetailDiv").window("open");
			}
			// 关闭合同窗口
			function ContractHideWin() {
				$("#ContractMessagedetailDiv").window("close");
			}
			// 打开集采窗口
			function CollectiveOpenWin() {
				$("#CollectiveMessagedetailDiv").window("open");
			}
			// 关闭集采窗口
			function CollectiveHideWin() {
				$("#CollectiveMessagedetailDiv").window("close");
			}
			//删除消息
			function delMessages(){
				var ids = [];
				var rows = $("#webservicesMessagesDatagrid").datagrid("getSelections");
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].hxuuid);
				}
				if (ids.length > 0) {
					$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
						if (r) {
							$.ajax( {
								url : "delMessages.hx",
								data : "ids=" + ids,
								type : "post",
								success : function(data) {
									if (data.status) {
										displayMsg(data.info,true);
										$("#webservicesMessagesDatagrid").datagrid('reload');
									} else if (json == -1) {
										displayMsg(data.info,false);
									}
								}
							});
						}
					});
				} else {
					showMsg("请选择要删除的数据");
				}
			}
			//推送消息
			function pushMessages(){
				var ids = [];
				var rows = $("#webservicesMessagesDatagrid").datagrid("getSelections");
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].hxuuid);
				}
				if (ids.length > 0) {
					$.messager.confirm('确认', '您确认想要推送消息吗？', function(r) {
						if (r) {
							$.ajax( {
								url : "jpusMessages.hx",
								data : "ids=" + ids,
								type : "post",
								success : function(data) {
									if (data.status) {
										displayMsg(data.info,true);
										$("#webservicesMessagesDatagrid").datagrid('reload');
									} else if (json == -1) {
										displayMsg(data.info,false);
									}
								}
							});
						}
					});
				} else {
					showMsg("请选择要推送的数据");
				}
			}
			
		</script>
	</body>
</html>
