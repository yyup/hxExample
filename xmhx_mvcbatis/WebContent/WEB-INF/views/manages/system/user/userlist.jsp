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
			域账号：<input autocomplete="off" name="umName" id="umName" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>&nbsp;&nbsp;
			真实姓名：<input autocomplete="off" name="realName" id="realName" style="width: 150px;height: 25px;" onkeydown="toQuery(event);"/>&nbsp;&nbsp;
			来源：	<select name="creType" id="creType" style="width: 150px;height: 25px;" onkeydown="toQuery(event);">
						<option value="" data-auto-add="Y"></option>
						<option value="1" data-auto-add="Y">域服务器</option>
						<option value="2" data-auto-add="Y">自建</option>
					</select>
			<a class="easyui-linkbutton" style="margin-left:20px" href="javascript:queryUserinfo();">查询</a>
			<c:if test="${dr_admin == 1 }">
				<a class="easyui-linkbutton" style="margin-left:20px" href="javascript:onDeleteUserinfo();">删除</a>
			</c:if>
			
			<a class="easyui-linkbutton" href="javascript:doUpload();">附件上传</a>
			<a class="easyui-linkbutton" href="javascript:doQueryattach();">附件查看测试</a>
			<a class="easyui-linkbutton" href="javascript:doDownloadattach();">附件下载测试</a>
			<a class="easyui-linkbutton" href="javascript:doDelattach();">附件删除测试</a>
		</div>
		<!-- 中间文字说明 -->
		<div class="textInfo">
		</div>
		<!-- 列表 -->
		<div class="tableInfo">
			<!-- 创建按钮 -->
			<div style="height:40px; background:#FCEAD9;">
				<a class="easyui-linkbutton" style="margin:8px 8px 8px 930px;" href="javascript:onAdduser();">创建</a>
			</div>
			<table id="datagrid" class="easyui-datagrid" singleSelect="true"
				url="userpage.do"  border="false" 
				striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="10" pageList="[1,5,10,20,50,100,200,500,1000]" toolbar="#tb">
				<thead>
					<tr>
						<th data-options="field:'id'" hidden="true">id</th>
						<th data-options="field:'umname', sortable:'true'" width="20%" align="center">域账号</th>
						<th data-options="field:'realname', sortable:'true'" width="20%" align="center">真实姓名</th>
						<th data-options="field:'email', sortable:'true'" width="30%" align="center">邮箱地址</th>
						<th data-options="field:'cretype', sortable:'true'" width="20%" align="center" formatter="formatcretype">来源</th>
						<th data-options="field:'date_created', sortable:'true'" width="20%" align="center">创建时间</th>
						<th data-options="field:'opt', sortable:'true'" width="15%" align="center" formatter="formatoption">
							操作
						</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 创建或修改Window -->
		<div id="userinfoDiv" class="easyui-window" style="width:600px;height:300px;padding:10px;" 
			minimizable="false" maximizable="false" collapsible="false" closed="true">
			<form action="onsaveuser.do" id="windowFrm" method="post">
				<input autocomplete="off" type="hidden" name="id" id="id" value=""/>
				<br />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr style="height: 30px;">
						<td width="100px" align="right">域账号：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="umname" id="umname" />
							<span style="color: red">*</span>
							<span id="umnameTip" class="onshow"></span>
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">来源：</td>
						<td>
							<select autocomplete="off" class="reg_input" name="cretype" id="cretype" onchange="javascript:changecretype(this.value);">
								<option value=""></option>
								<option value="1">域服务器</option>
								<option value="2">自建</option>
							</select>
							<span style="color: red">*</span>
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">真实姓名：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="realname" id="realname" />
							<span style="color: red">*</span>
							<span id="realnameTip" class="onshow"></span>
						</td>
					</tr>
					<tr style="height: 30px;">
						<td width="100px" align="right">邮箱地址：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="email" id="email" />
							<span style="color: red">*</span>
							<span id="emailTip" class="onshow"></span>
						</td>
					</tr>
					<tr id = "pwd1" style="height: 30px;">
						<td width="100px" align="right">密码：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="newpwd" id="newpwd" type="password"  />
							<span style="color:#FF4A04">&nbsp;&nbsp;如果密码为空，则不更改原密码</span>
						</td>
					</tr>
					<tr id = "pwd2" style="height: 30px;">
						<td width="100px" align="right">确认密码：</td>
						<td>
							<input autocomplete="off" class="reg_input" name="password" id="password" type="password" />
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
			<form id="AttachForm" name="AttachForm" action="" method="post"></form>
		</div>
		<script type="text/javascript">
			$(function(){
				// 表单验证
				$.formValidator.initConfig();
				//toformyz();
			});
			// 表单验证
			/* function toformyz(){
				$("#umname").formValidator({onShow:""});
				$("#realname").formValidator({onShow:""});
				$("#email").formValidator({onShow:""});
				$("#newpwd").formValidator({onShow:""});
				$("#password").formValidator();
			} */
			// 查询
			function queryUserinfo(){
				$.ajax({
					type: "POST",
				   	url: "dlmiflogoned.do",
				   	cache: false,
				   	async: false,
				   	dataType: "json",
				   	success: function(data) {
				   		$("#datagrid").datagrid('load',{
							url:"userpage.do",
							umname:$("#umName").val(),
							realname:$("#realName").val(),
							cretype:$("#creType").val()
						});
				   	},
				   	error: function(data) {
				   		displayAlert("警告", data.responseText);
				   	}
				}); 
			}
			// 回车查询
			function toQuery(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					queryUserinfo();
				}
			}
			//在弹出的窗体改变来源字段，密码字段隐藏或显示
			function changecretype(val){
				if(isEmpty(val)){
					$.messager.alert("提示","来源不能为空，请选择来源");
				}else if(val == 1){
					//来源是域服务器
					$("#pwd1").css('display','none');
					$("#pwd2").css('display','none');
				}else if(val == 2){
					//来源是自建
					$("#pwd1").css('display','');
					$("#pwd2").css('display','');
					$("#newpwd").val('');
					$("#password").val('');
				}
			}
			// 窗体显示
			function showWin(title){
				var _umname = $("#umname").val();
				var _cretype = $("#cretype").val();
				//清空密码
				$("#newpwd").val('');
				$("#password").val('');
				attrUser();
				//如果umname不为空则是点击的是创建按钮，否则点击的是修改按钮
				if(isEmpty(_umname)){
					$("#cretype").removeAttr("disabled");
					$("#cretype").val('1');//创建，来源默认为域服务器
					$("#pwd1").css('display','none');
					$("#pwd2").css('display','none');
				}else if(_cretype == "1"){
					//修改，来源是域服务器
					$("#pwd1").css('display','none');
					$("#pwd2").css('display','none');
					//$("#cretype").attr("disabled",true);
				}else if(_cretype == "2"){
					//修改，来源是自建
					$("#pwd1").css('display','');
					$("#pwd2").css('display','');
					//$("#cretype").attr("disabled",true);
				}
				$("#userinfoDiv").window({title:title,modal:true});
				$("#userinfoDiv").window('open');
			}
			// 窗体关闭
			function hideWin(){
				$("#userinfoDiv").window('close');
			}
			function formatcretype(val, row){
				switch(row.cretype){
				case "1":
					return '域服务器';
					break;
				case "2":
					return '自建';
					break;
				}
				return ''; 
			}
			// 增加	
			function onAdduser(){
				$("#umname").removeAttr("disabled");
				$("#newpwd").removeAttr("disabled");
				$("#password").removeAttr("disabled");
				//attrUser();
				zeroUser();
				showWin("添加系统用户");
			}
			function attrUser() {
				$("#umname").formValidator({onShow:"4-40个字符",onFocus:"4-40个字符",onCorrect:"通过"}).inputValidator({min:4,max:40,onError:"域帐号输入有误"});
				$("#realname").formValidator({onShow:"1-50个字符",onFocus:"1-50个字符",onCorrect:"通过"}).inputValidator({min:1,max:50,onError:"真实姓名输入有误"});
				$("#email").formValidator({onShow:"1-50个字符",onFocus:"1-50个字符",onCorrect:"通过"}).inputValidator({min:1,max:50,onError:"邮箱输入有误"});
			}
			function zeroUser() {
				$("#id").val('');
				$("#cretype").val('');
				$("#umname").val('');
				$("#realname").val('');
				$("#email").val('');
				$("#newpwd").val('');
				$("#password").val('');
			}
			// 删除
			function onDeleteUserinfo(){
				var row = $('#datagrid').datagrid('getSelected');
				if (row){
					$.messager.confirm('删除', '是否删除用户?', function(f) {
						if (f) {
							$.ajax({
								url:"ondeluser.do",
								data:"umname="+encodeURI(row.umname),
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
			}
			
			function formatoption(val, row) {
				var _html = '';
					_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href=javascript:onUpdateUserinfo("'+ row.umname +'","'+row.cretype+'","'+row.id+'");><span class="l-btn-left"><span class="l-btn-text icon-pencil l-btn-icon-left">修改</span></span></a>';
				return _html;
				//onUpdateUserinfo(row);
			}
			// 修改，基本信息加载
			function onUpdateUserinfo(umname,cretype,id) {
				var _umname = umname;
				$.post("validateUserRights.do", {"umname": _umname}, function(data) {
					if (data.status) {
						if( data.info == "系统管理员或数据管理员角色" ){
							$("#cretype").removeAttr("disabled");
						}else if(data.info == "修改的记录是自己"){
							$("#cretype").attr("disabled",true);
						}
						//有权限修改
						$.ajax({
							//根据ID更新用户信息
							url:"onupdateloaduser.do",
							data:"id="+id,
							type:"post",
							success:function(data){
								if(data.status){
									$("#id").val(data.info.id);
									$("#umname").val(data.info.umname);
									$("#realname").val(data.info.realname);
									$("#employeecode").val(data.info.employeecode);
									$("#email").val(data.info.email);
									$("#phone").val(data.info.phone);
									$("#mobile").val(data.info.mobile);		
									$("#cretype").val(data.info.cretype);									
									$("#umname").attr({"disabled":true});
									
									//toformyz();
									showWin("修改用户");
								}
							}
						});
					} else {
						//没有权限修改
						displayMsg(data.info, false);
					}
				});
			}
			function checkPwd(){
				var newpwd = $("#newpwd").val();
				var password = $("#password").val();
				if(password != newpwd){
					displayMsg("两次输入的密码不一致！", false);
					return false;
				}
				return true;
			}	
			
			function onsaveuser() {
				var _umname = $("#umname").val();
				if(!notEmpty(_umname)){
					displayMsg("域帐号不允许为空", false);
					return;
				}
				var _cretype = $("#cretype").val();
				if(!notEmpty(_cretype)){
					displayMsg("来源不允许为空", false);
					return;
				}
				var _realname = $("#realname").val();
				if(!notEmpty(_realname)){
					displayMsg("真实姓名不允许为空", false);
					return;
				}
				var _email = $("#email").val();
				if(!notEmpty(_email)){
					displayMsg("邮箱不允许为空", false);
					return;
				}
				if(checkPwd()){
					$("#umname").removeAttr("disabled");
					$('#windowFrm').form('submit', {
						success:function(data){
							var _data = eval("(" + data + ")");
							if (_data.status) {
								hideWin();
								displayMsg(_data.info, true);
								$("#datagrid").datagrid('reload');								
							} else {
								displayMsg(_data.info, false);
							}
						}
					});
					$("#umname").attr("disabled",true);
				}
			}
			function doUpload() {
				uploadattach('1', 'testing12345');
			}
			function doQueryattach() {
				showattach('1');
			}
			function doDownloadattach() {
				downloadattach('1');		
			}
			function doDelattach() {
				delattach('1');
			}
			function addNewTab(title,openUrl) {
				window.parent.add(title, openUrl);
			};
			//鼠标滑过“这里”，变色
			$("#dr").hover(
			  	function () {			  
			   		$("#dr").css("color","blue");
			  	},
			  	function () {
				 	$("#dr").css("color","#F90");
			  	}
			);
			function onUse(tShow,tHide){		
				$("").css('display','block');
				$("").css('display','none');
			}
		</script>
	</body>
</html>
