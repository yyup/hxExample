<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户管理</title>
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
			手机号或姓名：
			<input autocomplete="off" name="nameormobile" id="nameormobile" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:queryUser();">查询</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:addUser();">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:modifyUser();">修改</a>
		</div>
		
		<!-- 中间文字说明 -->
		<div class="textInfo"></div>
		
		<!-- 列表 -->
		<div class="tableInfo">
			<table id="commonmemberDatagrid" class="easyui-datagrid" singleSelect="true"
				url="pagelist.hx"  border="false" striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="10" pageList="[1,5,10,20,50,100,200,500,1000]">
				<thead>
					<tr>
						<th data-options="field:'hxuuid'" hidden="true">hxuuid</th>
						<th data-options="field:'member_mobile', sortable:'true'" width="15%" align="center">手机号</th>
						<th data-options="field:'member_name', sortable:'true'" width="15%" align="center">姓名</th>
						<th data-options="field:'member_nick', sortable:'true'" width="15%" align="center">昵称</th>
						<th data-options="field:'member_sex', sortable:'true'" width="10%" align="center" formatter="formatsex">性别</th>
						<th data-options="field:'member_email', sortable:'true'" width="25%" align="center">邮箱</th>
						<th data-options="field:'date_created', sortable:'true'" width="20%" align="center">创建时间</th>
						<!--  <th data-options="field:'opt', sortable:'true'" width="15%" align="center">操作</th>-->
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="userinfoDiv" class="easyui-window" style="width:500px;height:350px;padding:10px;" 
			title="添加用户" minimizable="false" maximizable="false" collapsible="false" closed="true">
			<form id="saveuserFrm">
				<input autocomplete="off" type="hidden" name="hxuuid" id="hxuuid" value=""/>
				<br />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr style="height: 30px;">
						<td width="100px" align="right">
							<span style="color: red">*</span>
							手机号：
						</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_mobile" id="member_mobile" maxlength="11" />
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">
							<span style="color: red">*</span>
							姓名：
						</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_name" id="member_name" maxlength="10" />
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">昵称：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_nick" id="member_nick" maxlength="10" />
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">
							<span style="color: red">*</span>
							性别：
						</td>
						<td>
							<select autocomplete="off" class="reg_input" name="member_sex" id="member_sex" style="width:130px">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">邮箱地址：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_email" id="member_email" maxlength="30" />
						</td>
					</tr>
					<tr id = "pwd1" style="height: 30px;display:none">
						<td width="100px" align="right">密码：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_pwd" id="member_pwd" type="password" maxlength="18"  />
							<span style="color:#FF4A04">&nbsp;&nbsp;如果密码为空，则不更改原密码</span>
						</td>
					</tr>
					<tr id = "pwd2" style="height: 30px;display:none">
						<td width="100px" align="right">确认密码：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="member_pwd2" id="member_pwd2" type="password" maxlength="18" />
						</td>
					</tr>
					<tr style="height: 60px;">
						<td align="center" colspan="2">
							<a class="easyui-linkbutton" href="javascript:onsaveuser();">保存</a>
							<a class="easyui-linkbutton" href="javascript:hideWin();">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<script type="text/javascript">
			// 查询
			function queryUser() {
				var _nameormobile=$("#nameormobile").val();
				$("#commonmemberDatagrid").datagrid('load', {
					url: "pagelist.hx",
					nameormobile: _nameormobile
				});
			}
			// 回车查询
			function toQuery(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					queryUser();
				}
			}
			//格式化性别
			function formatsex(val,row){
				if(row.member_sex == '1'){
					return '男';
				}else if(row.member_sex == '2'){
					return '女';
				}
			}
			//打开窗口
			function openWin(){
				$("#userinfoDiv").window("open");
			}
			//关闭窗口
			function hideWin(){
				$("#userinfoDiv").window("close");
			}
			//添加用户
			function addUser(){
				$("#hxuuid").val("");
				$("#member_mobile").val("");
				$("#member_name").val("");
				$("#member_nick").val("");
				$("#member_sex").val("");
				$("#member_email").val("");
				$("#member_pwd").val("");
				$("#member_pwd2").val("");
				$("#pwd1").css("display","none");
				$("#pwd2").css("display","none");
				$("#userinfoDiv").window({"title":"添加用户"});
				$("#member_mobile").removeAttr("disabled");
				openWin();	//打开窗口
			}
			//修改用户
			function modifyUser(){
				//判断是否选择了某个用户
				var row = $("#commonmemberDatagrid").datagrid("getSelected");
				if(row == null){
					$.messager.alert("提示","请选择要修改的行");
				}else{
					//$.messager.alert("提示",row.member_name);
					$("#hxuuid").val(row.hxuuid);
					$("#member_mobile").val(row.member_mobile);
					$("#member_name").val(row.member_name);
					$("#member_nick").val(row.member_nick);
					$("#member_sex").val(row.member_sex);
					$("#member_email").val(row.member_email);
					$("#member_pwd").val("");
					$("#member_pwd2").val("");
					$("#pwd1").css("display","");
					$("#pwd2").css("display","");
					$("#userinfoDiv").window({"title":"修改用户"});
					$("#member_mobile").attr("disabled",true);
					openWin();	//打开窗口
				}
			}
			//保存
			function onsaveuser(){
				var hxuuid = $.trim($("#hxuuid").val());
				var member_mobile = $.trim($("#member_mobile").val());
				var member_name = $.trim($("#member_name").val());
				var member_nick = $.trim($("#member_nick").val());
				var member_sex = $.trim($("#member_sex").val());
				var member_email = $.trim($("#member_email").val());
				var member_pwd = $.trim($("#member_pwd").val());

			 	if(isEmpty(member_mobile)){
					$.messager.alert("提示","手机号不能为空");
					return;
				}
				//如果hxuuid为空（即添加用户），则判断手机是否11位数字
				if(isEmpty(hxuuid)){
					var regex_mobile = /^[1][0-9]{10}/;
					if(!regex_mobile.test(member_mobile)){
						$.messager.alert("提示","手机号不正确");
						return;
					}
				}
				if(isEmpty(member_name)){
					$.messager.alert("提示","姓名不能为空");
					return;
				}
				if(isEmpty(member_sex)){
					$.messager.alert("提示","性别不能为空");
					return;
				}
				//判断邮箱格式是否正确
				if(!isEmpty(member_email)){
					var regex_email = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2,3})?)$/g;
					if(!regex_email.test(member_email)){
						$.messager.alert("提示","邮件格式不正确");
						return;
					}
				} 
				$.ajax({
					url:"saveuser.hx",
					cache: false,
					type:"post",
					data:{"hxuuid":hxuuid,"member_mobile":member_mobile,"member_name":member_name,"member_nick":member_nick,"member_sex":member_sex,"member_email":member_email,"member_pwd":member_pwd},
					dataType:"json",							
					success:function(data){
						if (data.status) {
							displayMsg(data.info,true);
							hideWin();
							$("#commonmemberDatagrid").datagrid('reload');
						} else {
							displayMsg(data.info,false);
						}
					},
					error:function(data) {
						displayAlert('警告', data.responseText);
					}
				});
			}
		</script>
	</body>
</html>
