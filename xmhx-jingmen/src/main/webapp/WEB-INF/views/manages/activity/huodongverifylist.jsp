<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>活动审核</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
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
			标题：<input autocomplete="off" name="title" id="title" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>&nbsp;&nbsp;
			<!-- 
			活动时间：<input autocomplete="off" name="start_time" id="start_time" style="width: 80;height: 25px;" onkeydown="toQuery(event);"/>-
			         <input autocomplete="off" name="end_time" id="end_time" style="width: 80;height: 25px;" onkeydown="toQuery(event);"/>&nbsp;&nbsp;
			 -->
			<a class="easyui-linkbutton" style="margin-left:5px" href="javascript:queryActivity();">查询</a>
		</div>
		<!-- 中间文字说明 -->
		<div class="textInfo">
		</div>
		<!-- 列表 -->
		<div class="tableInfo">
			<table id="HuodongVerifyDatagrid" class="easyui-datagrid" singleSelect="false" title="活动审核列表"
				url="${ctx }/manages/pageActivity.hx"  border="false" 
				striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="10" pageList="[10,20,50,100,200,500,1000]" toolbar="#tb">
				<thead>
					<tr>
					    <th data-options="field:'ck', sortable:'true'" checkbox="true"></th>
						<th data-options="field:'id'" hidden="true">id</th>
						<th data-options="field:'hxuuid'" hidden="true">hxuuid</th>
						<th data-options="field:'tp', sortable:'true'" width="10%" align="center" formatter="formatTp">活动类型</th>
						<th data-options="field:'title', sortable:'true'" width="30%" align="center">标题</th>
						<th data-options="field:'stime', sortable:'true'" width="15%" align="center">活动开始时间</th>
						<th data-options="field:'etime', sortable:'true'" width="15%" align="center">活动结束时间</th>
						<th data-options="field:'overtime', sortable:'true'" width="15%" align="center">报名截止时间</th>
						<th data-options="field:'verifystatus', sortable:'true'" width="15%" align="center" formatter="formatverifystatus">审核状态</th>
						<th data-options="field:'opt', sortable:'true'" width="30%" align="center" formatter="formatoption">
							操作
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- 审核不通过审核意见 -->
		<div id="VerifyDiv" class="easyui-window" style="top:50px;width:700px;height:400px;padding:10px;" 
			minimizable="false" maximizable="false" collapsible="false" closed="true">
			<div style="border: 1px solid #DEDEE0;">
				<form action="" id="ExcutesqlFrm" method="post">
					<div style="margin:20px; border: 1px solid #DEDEE0;">
						<input type="hidden" name="activityid" id="activityid">
						<textarea rows="" cols="" name="verifydesc" id="verifydesc" style="width: 600px; height: 200px;"></textarea>
						温馨提示：审核不通意见超过200字符将自动截位
					</div>
					<div id="tbExcutesql" style="padding:10px;height:auto">
						<a href="javascript:onverifynot();" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-options="size:'small'">保存</a>
						<a href="javascript:hiddenVerifyDivWin();" class="easyui-linkbutton" iconCls="icon-no" plain="true" data-options="size:'small'">关闭</a>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			$('#start_time').datetimebox({    
			    showSeconds: false   
			});  
			$('#end_time').datetimebox({    
			    showSeconds: false   
			});  
		    // 查询
			function queryActivity(){
		   		$("#HuodongVerifyDatagrid").datagrid('load',{
					url:"manages/pageActivity.hx",
					title:$("#title").val()
				});
			}
			// 回车查询
			function toQuery(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					queryActivity();
				}
			}
			// 删除
			/*function delNews(){
			    var ids = [];
				var rows = $("#datagrid").datagrid('getSelections');
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].id);
				}
				if (ids.length>0){
					$.messager.confirm('删除', '是否删除资讯?', function(f) {
						if (f) {
							$.ajax({
								url:"delNews.do",
								data:"ids="+ids,
								type:"post",
								success:function(data){
									if(data.status){
										displayMsg(data.info, true);
										$("#datagrid").datagrid('reload');
									}else{
										displayMsg(data.info, false);
									}
								}
							});
						}
					});
				}else{
					displayMsg("请选择一条记录");
				}
			}*/
			function formatTp(val, row) {
				if (val == "1") {
					return "聚会";
				} else if(val == "2") {
					return "沙龙";
				} else if(val == "3") {
					return "培训";
				} else if(val == "4") {
					return "路演";
				} else if(val == "5") {
					return "开心农场";
				} else {
					return "其他";
				}
			}
			function formatverifystatus(val, row) {
				if (val == "0") {
					return "待审核";
				} else if(val == "1") {
					return "审核通过";
				} else if(val == "2") {
					return "审核不通过";
				}
			}
			function formatoption(val, row) {
				var _html = '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="${ctx}/manages/lookActivityById.hx?activityid='+row.hxuuid+'"><span class="l-btn-left"><span class="l-btn-text icon-search l-btn-icon-left">详情</span></span></a>';
					_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="javascript:toverifypass(\''+row.hxuuid+'\', '+row.verifystatus+')"><span class="l-btn-left"><span class="l-btn-text icon-ok l-btn-icon-left">审核通过</span></span></a>'
					_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="javascript:toverifynot(\''+row.hxuuid+'\', '+row.verifystatus+')"><span class="l-btn-left"><span class="l-btn-text icon-cancel l-btn-icon-left">审核不通过</span></span></a>';;
				return _html;
			}
			function reload() {
				$('#HuodongVerifyDatagrid').datagrid('reload'); 
			}
			function toverifypass(_activityid, _verifystatus) {
				$.messager.confirm('确认','记录确认审核通过吗？',function(r){    
				    if (r){   
				    	if (_verifystatus == 0) {
				    		$.post("${ctx}/manages/huodongverifypass.hx", {"activityid": _activityid}, function(data) {
				    			if (data.status) {
				    				reload();
				    			} else {
				    				$.messager.alert("错误", "审核通过异常", "error");
				    			}
				    		});
				    	} else {
				    		$.messager.alert("温馨提示", "不是待审核状态，不需重复审核", "info");
				    	}
				    }    
				}); 
			}
			function toverifynot(_activityid, _verifystatus) {
				$.messager.confirm('确认','记录确认审核不通过吗？',function(r){    
				    if (r){    
				    	if (_verifystatus == 0) {
				    		$("#activityid").val(_activityid);
							$("#verifydesc").val('');
				    		showVerifyDivWin('审核不通过意见');
				    	} else {
				    		$.messager.alert("温馨提示", "不是待审核状态，不需重复审核", "info");
				    	}
				    }    
				}); 
			}
			function onverifynot() {
				var _activityid = $("#activityid").val();
				var _verifydesc = $("#verifydesc").val();
				if (_verifydesc!=null && $.trim(_verifydesc)!='') {
					if (_activityid!=null && $.trim(_activityid)!='') {
						$.post("${ctx}/manages/huodongverifyno.hx", {"activityid": _activityid, "verifydesc": _verifydesc}, function(data) {
							if (data.status) {
								hiddenVerifyDivWin();
								reload();
							} else {
			    				$.messager.alert("错误", "审核不通过异常", "error");
			    			}
						});
					} else {
						$.messager.alert("温馨提示", "审批主键不能为空", "info");
						return;
					}
				} else {
					$.messager.alert("温馨提示", "审批意见不能为空", "info");
					return;
				}
			}
			function showVerifyDivWin(title) {
				$("#VerifyDiv").window({title:title,modal:true});
				$("#VerifyDiv").window('open');
			}
			function hiddenVerifyDivWin() {
				$("#VerifyDiv").window('close');
			}
		</script>
	</body>
</html>
