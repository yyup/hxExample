<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>数据权限管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@include file="/WEB-INF/views/include/source.jsp" %>
		<style>
			.windowTb td, .toolBarTb td {
				padding: 5px;
			}
			.windowTb td input,select,.toolBarTb td input {
				width: 300px;
				height: 25px;
			}
			.bottombtn {
				padding-top: 10px;
				padding-bottom: 10px; 
				padding-left:20px;
				order-collapse: collapse;
				border: 1px solid #F6D098;
				background: #FFF0E0;		
			}		
		</style>
	</head>
	<body class="body1">
		<!-- toolbar -->
		<div class="formInfo">
			<table width="100%" class="toolBarTb">
				<tr>
					<td width="80">数据角色：</td>
					<td>
						<input autocomplete="off" type="hidden" id="queryrole" value="">
						<select id ="queryrolesel" class="role_select chzn-rtl"  data-placeholder="请选择..." multiple="multiple" onchange="javascript:qrySelectRole();">
					    </select>
					</td>
					<td width="80">用户：</td>
					<td>
						<input autocomplete="off" type="hidden" id="queryuser" value="">
						<select id="queryusersel" class="user_select chzn-rtl"  data-placeholder="请选择..." multiple="multiple" >
						</select>
					</td>
				</tr>
				<tr>
					<td width="80">范围类型：</td>
					<td>
						<input autocomplete="off" type="hidden" id="queryscopetype" value="">
						<select id="queryscopetypesel" style="height: 30px;" onchange="javascript:addscopevalue(this.value);">
							<option value="" data-auto-add="Y"></option>
							<c:if test="${not empty scopelist }">
								<c:forEach items="${scopelist }" var="scope">
									<option value="${scope.key }" data-auto-add="Y">${scope.value }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
					<td width="80">范围值：</td>
					<td>
						<input autocomplete="off" type="hidden" id="queryscopevalue" value="">
						<select id="queryscopevaluesel" class="scopevalue_select chzn-rtl" data-placeholder="请选择..." multiple="multiple">
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
					<td>
						<a class="easyui-linkbutton" href="javascript:queryDatarights();">查询</a>	
						<a class="easyui-linkbutton" style="margin-left:6px;" href="javascript:deleteDatarights();">删除</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- 列表 -->
		<div class="tableInfo">
			<div style="height:40px; background:#FCEAD9;">
				<c:if test="${dr_admin == 1 }">	
					<a class="easyui-linkbutton" style="margin:8px 8px 8px 930px;" href="javascript:onDatarights();">创建</a>
					<!-- 
					<a class="easyui-linkbutton" style="margin-left:6px;" href="javascript:importBatchrights();" title="支持.csv\.xls\.xlsx格式">批量导入</a>
					 -->
				</c:if>
			</div>
			<table id="datagrid" class="easyui-datagrid" singleSelect="false"
				url="datarightpage.do"  border="false" 
				striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="10" pageList="[5,10,20,50,100,200,500,1000]" toolbar="#tb">
				<thead>
					<tr>
						<th data-options=" checkbox:'true'"></th>
						<th data-options="field:'id', sortable:'true'" hidden="true" width="10%" align="center">ID</th>
						<th data-options="field:'rolecode', sortable:'true'" width="15%" align="center">
							角色
						</th>
						<th data-options="field:'scopetype', sortable:'true'" width="10%" align="center">
							范围类型
						</th>
						<th data-options="field:'scopevalue', sortable:'true'" width="30%" align="center">
							范围值
						</th>
						<th data-options="field:'umname', sortable:'true'" width="20%" align="center">
							帐号
						</th>
						<th data-options="field:'realname', sortable:'true'" width="15%" align="center">
							用户
						</th>
					</tr>
				</thead>
			</table>
		</div>		
		<!-- 创建Window -->
		<div id="datarightsDiv" class="easyui-window" style="top:60px;width:850px;height:400px;padding:10px;" 
			data-options="minimizable:false,maximizable:false,collapsible:false,closed:true">
			<div class="easyui-layout" fit="true">
				<div data-options="region:'center'" style="padding:5px;background:#FCFCFC;">
				<form action="saveDatarights.do" id="windowFrm" method="post">
					<input autocomplete="off" type="hidden" name="id" id="id" value="0" />
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="windowTb">
						<tr>
							<td width="100px" align="right">角色：</td>
							<td>
								<input autocomplete="off" type="hidden" id="addrole" name="addrole" value="">
								<select id="role" name="role" class="role_select chzn-rtl" data-placeholder="请选择..." multiple="multiple" onchange="javascript:addSelectRole();">
								</select>
							</td>
						</tr>
						<tr>
							<td width="100px" align="right">用户：</td>
							<td>
								<input autocomplete="off" type="hidden" id="umname" name="umname">
								<input autocomplete="off" id="addumname" name="addumname" style="width: 600px; height: 28px;" onfocus="javascript:addUserinfo();"/>
							</td>
						</tr>
						<tr>
							<td width="100px" align="right">范围类型：</td>
							<td>
								<input autocomplete="off" type="hidden" id="addscopetype" name="addscopetype" value="">
								<select id="scopetype" name="scopetype" style="width: 600px; height: 28px;" class="selector" disabled="disabled">
									<option value="" data-auto-add="Y"></option>
									<c:if test="${not empty scopelist }">
										<c:forEach items="${scopelist }" var="scope">
											<option id = "${scope.key }" value="${scope.key }" data-auto-add="Y">${scope.value }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td width="100px" align="right">范围：</td>
							<td>
								<input autocomplete="off" type="hidden" id="addscopeval" name="addscopeval" value="">
								<select id="scopevalue" name="scopevalue" class="scopevalue_select chzn-rtl" data-placeholder="请选择..." multiple="multiple" >
								</select>
							</td>
						</tr>
					</table>
				</form>
				</div>
			    <div data-options="region:'south'" style="height:50px; text-align: center;" class="bottombtn">
			    	<a class="easyui-linkbutton" href="javascript:saveDatarights();">保存</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" href="javascript:hideWin();">关闭</a>
			    </div>   
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".role_select").chosen();
				$(".scopevalue_select").chosen();
				$(".user_select").chosen();
				document.all.windowFrm.reset();
				addrole();
				adduser();
				addscopevalue('GLOBAL');
				$("#addumname").bind('input',function(){
					$("#umname").val('');
				}); 
			});
			//添加角色
			var globalArr =["ADMIN","DATAMGR","SEARCHER","OCGMANAGER"];
			var subsysArr =["CM","DEP","DEV","SYSPRINCIPAL","TES","VM","APPSERVICE","APPLEADER","HANDOVER","PRDPERSONNEL","DEVSECTIONLEADER"];
			function qrySelectRole(){
				var role = $("#queryrolesel").val();
				var flag1 = false;
				var flag2 = false;
				for ( var i = 0,n = globalArr.length; i < n; i++) {
					if(isContains(role,globalArr[i])){
						flag1 = true;
					}
				}
				for ( var i = 0,n = subsysArr.length; i < n; i++) {
					if(isContains(role,subsysArr[i])){
						flag2 = true;
					}
				}
				if (flag1 && flag2){
					displayMsg("不能同时选择“全局类型”和“子系统类型”角色进行查询", false);
					return;
				}
				if(flag1){
					$("#queryscopetypesel").val("GLOBAL");
					addscopevalue('GLOBAL');
				}
				if(flag2){
					$("#queryscopetypesel").val("SUBSYS");
					addscopevalue('SUBSYS');
				}
			}
			function addSelectRole(){
				var role = $("#role").val();
				var flag1 = false;
				var flag2 = false;
				for ( var i = 0,n = globalArr.length; i < n; i++) {
					if(isContains(role,globalArr[i])){
						flag1 = true;
					}
				}
				for ( var i = 0,n = subsysArr.length; i < n; i++) {
					if(isContains(role,subsysArr[i])){
						flag2 = true;
					}
				}
				if (flag1 && flag2){
					displayMsg("不能同时选择“全局类型”和“子系统类型”角色", false);
					return;
				}
				if(flag1){
					$(".selector").val("GLOBAL");
					addscopevalue('GLOBAL');
				}
				if(flag2){
					$(".selector").val("SUBSYS");
					addscopevalue('SUBSYS');
				}
			}
			
			/*新增时加载用户*/
			function addUserinfo(){
				$.ajax({
					url:"userload.do",
					cache: false,
					type:"post",
					dataType:"json",							
					success:function(data){
						$("#addumname").flushCache();
						$('#addumname').autocomplete(data, {
					        max: 12,    			//列表里的条目数
					        minChars: 0,    		//自动完成激活之前填入的最小字符
					        width: 598,     		//提示的宽度，溢出隐藏
					        scrollHeight: 200,   	//提示的高度，溢出显示滚动条
					        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
					        autoFill: false,    	//自动填充
					        formatItem: function(row, i, max) {
					            return row.umname + '【' + row.realname + ' | ' + row.email + '】';
					        },
					        formatMatch: function(row, i, max) {
					            return row.umname;
					        },
					        formatResult: function(row) {
					            return row.umname;
					        }
					    }).result(function(event, row, formatted) {
							$("#umname").val(row.umname);
					    });
					},
					error:function(data) {
						displayAlert("警告", data.responseText);
					}
				});
				/* $.post("userload.do",function(data){
					$("#addumname").flushCache();
					$('#addumname').autocomplete(data, {
				        max: 12,    			//列表里的条目数
				        minChars: 0,    		//自动完成激活之前填入的最小字符
				        width: 598,     		//提示的宽度，溢出隐藏
				        scrollHeight: 200,   	//提示的高度，溢出显示滚动条
				        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
				        autoFill: false,    	//自动填充
				        formatItem: function(row, i, max) {
				            return row.umname + '【' + row.realname + ' | ' + row.email + '】';
				        },
				        formatMatch: function(row, i, max) {
				            return row.umname;
				        },
				        formatResult: function(row) {
				            return row.umname;
				        }
				    }).result(function(event, row, formatted) {
						$("#umname").val(row.umname);
				    });
				}); */
			}
			/* 角色下拉列表 */
			function addrole(){
				//加载角色信息
				$.post("roleload.do",function(data){
					if (data != null) {
						var _html = '';
						for (var i = 0, n = data.length; i < n; i++) {
							_html += '<option value="' + data[i].rolecode +'">' + data[i].rolename + '</option>';
						}
						$(".role_select").html(_html);
						$(".role_select").trigger("chosen:updated");
					}
				});
			}
			/* 查询时添加用户下拉列表 */
			function adduser() {
				//加载开发人员信息
				$.post("userload.do", function(data) {
					if (data != null) {
						var _html = '';
						for ( var i = 0, n = data.length; i < n; i++) {
							_html += '<option value="' + data[i].umname +'">'+ data[i].umname + '|'+ data[i].realname +'</option>';
						}
						$(".user_select").html(_html);
						$(".user_select").trigger("chosen:updated");
					}
				});
			}
			/* 范围值下拉列表 */
			function addscopevalue(value){
				$.post("scopevalueload.do", {"scopetype" : value}, function(data) {
					if (data != null) {
						var _html = '';
						for ( var i = 0, n = data.length; i < n; i++) {
							_html += '<option value="' + data[i].scopevalueid +'">'+ data[i].scopevalue + '</option>';
						}
						$(".scopevalue_select").html(_html);
						$(".scopevalue_select").trigger("chosen:updated");
					}
				});
			}
			// 创建
			function onDatarights() {
				toreset('windowFrm');
				showWin("新增权限");
			}
			// 窗体显示
			function showWin(title) {
				$(".scopevalue_select").trigger("chosen:updated");
				$(".role_select").trigger("chosen:updated");
				$(".user_select").trigger("chosen:updated");
				$("#datarightsDiv").window({
					title : title,
					modal : true
				});
				$("#datarightsDiv").window('open');
				$("#role_chosen").css({
					"width" : "600px"
				});
				$("#scopevalue_chosen").css({
					"width" : "600px"
				});
				$("#user_chosen").css({
					"width" : "600px"
				});
				addscopevalue('GLOBAL');
			}
			//保存数据
			function saveDatarights() {
				/**注：用submit提交方式，先取出chosen控件的值添加到inpupt的value中**/
				var roles = $("#role").val();
				$("#addrole").val(roles);
				var addumname = $("#addumname").val();
				var scopetype = $("#scopetype").val();
				$("#addscopetype").val(scopetype);
				var scopevalues = $("#scopevalue").val();
				$("#addscopeval").val(scopevalues);
				
				var subflag = false;
				if (!checkIsComBoxData($("#addumname").val(), $("#umname").val(), "用户")) {
					subflag = false;
					return ;
				}
				if (roles==null || $.trim(roles)=='') {
					subflag=false;
					displayMsg("角色不能为空", false);
					return;
				}
				if (addumname==null || $.trim(addumname)=='') {
					subflag=false;
					displayMsg("用户不能为空", false);
					return;
				}
				if (scopetype==null || $.trim(scopetype)=='') {
					subflag=false;
					displayMsg("范围类型不能为空", false);
					return;
				}
				if (scopevalues==null || $.trim(scopevalues)=='') {
					subflag=false;
					displayMsg("范围值不能为空", false);
					return;
				}
				if (!subflag){
					$("#scopetype").removeAttr("disabled");
					$('#windowFrm').form('submit',{
						success:function(data){
							var _data = eval("(" + data + ")");
							if (_data.status) {
								showMsg(_data.info, 1);
								hideWin();
								$("#datagrid").datagrid('reload');
								$("#scopetype").attr({"disabled":true});
							} else {
								showMsg(_data.info, 2);
								$("#scopetype").attr({"disabled":true});
							}
						}
					});
				}	
			}
			//窗体关闭
			function hideWin() {
				$("#datarightsDiv").window('close');
				document.all.windowFrm.reset();
			}
			// 回车查询
			function toQuery(e){
				var key = window.event?e.keyCode:e.which;
				if(key==13){
					queryDatarights();
				}
			}
			function queryDatarights() {
				$.ajax({
					type: "POST",
				   	url: "dlmiflogoned.do",
				   	cache: false,
				   	async: false,
				   	dataType: "json",
				   	success: function(data) {
				   		var _queryscopetypesel = $("#queryscopetypesel").val();
						if(notEmpty(_queryscopetypesel) && _queryscopetypesel =="SUBSYS"){
							var _queryscopevaluesel = $("#queryscopevaluesel").val();
							if(!notEmpty(_queryscopevaluesel)){
								displayMsg("请输入子系统范围值");
								return;
							}
						}
						querysetvalue();
						$("#datagrid").datagrid('load',{
							url: "datarightpage.do",
							isSearch:'Y',
							queryrole: $("#queryrole").val(),
							queryuser: $("#queryuser").val(),
							queryscopetype: $("#queryscopetype").val(),
							queryscopevalue: $("#queryscopevalue").val()
						});
				   	},
				   	error: function(data) {
				   		displayAlert("警告", data.responseText);
				   	}
				}); 
			}
			function querysetvalue() {
				var _queryrolesel = $("#queryrolesel").val();
				var _queryusersel = $("#queryusersel").val();
				var _queryscopetypesel = $("#queryscopetypesel").val();
				var _queryscopevaluesel = $("#queryscopevaluesel").val();
				$("#queryrole").val(_queryrolesel);
				$("#queryuser").val(_queryusersel);
				$("#queryscopetype").val(_queryscopetypesel);
				$("#queryscopevalue").val(_queryscopevaluesel);
			}
			function deleteDatarights() {
				$.messager.confirm('确认', '确认删除吗？', function(r) {
					if (r) {
						var ids = "";
						var umnames = "";
						var rows = $('#datagrid').datagrid('getSelections');
						if (rows.length==0) {
							displayMsg("请选择一条记录");
						} else {
							for ( var i = 0,n = rows.length ; i < n ; i++) {
								ids += rows[i].id+",";
								umnames += rows[i].umname+",";
							}
							$.post("deleteDatarights.do", {"ids": ids, "umnames": umnames}, function(data) {
								if (data.status) {
									displayMsg(data.info, true);
									$("#datagrid").datagrid('reload');
								}else{
									displayMsg(data.info, false);
								}
							});		
						}
					}
				});
			}
			function importBatchrights() {
				// 上传方法
				$.upload({
					// 上传地址
					url:"importBatchrights.do",
					// 文件域名字
					fileName: 'file', 
					// 上传完成后, 返回json, text
					dataType: 'json',
					// 上传之前回调,return true表示可继续上传
					onSend: function() {
						return true;
					},
					// 上传之后回调
					onComplate: function(data) {
						if (data.status) {
							displayMsg(data.info, true);
							$("#datagrid").datagrid('reload');
						} else {
							displayAlert("错误", data.info, "error");
						}
					}
				});
			} 
			//鼠标滑过“这里”，变色
			$("#dr").hover(
			  	function () {			  
			    	$("#dr").css("color","blue");
			  	},
			  	function () {
					$("#dr").css("color","#F90");
			  	}
			);
		</script>
	</body>
</html>
