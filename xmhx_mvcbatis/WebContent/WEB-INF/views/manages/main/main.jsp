<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<title>主内容</title>
		<%@include file="/WEB-INF/views/include/source.jsp" %>
	</head>
	<style>
		.toolBarTb td{ padding:5px}
		.inputTable td{ padding:5px; border:1px solid #B8BBCC; border-collapse: collapse; }
		.inputTable tr:nth-child(odd){ background:#DEE9ED}
		.infoCon{
			padding:0 20px 0 20px;
			margin-top:20px;
		}
		.infoTitle{
			width:150px;
			height:30px; 
			background:hsl(26, 95%, 69%);
			color:#fff;
			font-weight:bold;
			line-height:30px; 
			text-indent:30px;
		}		
		.infoBtn{
			width:100px;
			height:50px;			
			line-height:25px; 
			text-align:center;
			display:block;
			cursor:pointer
		}
	</style>
	<body id="cc" class="easyui-layout">
		<%-- <div data-options="region:'center'" style="width:100%;">
			<div><img src="resource/images/dlmhead.png" style="width:100%;"></img></div>
			<div id="todoTabs" class="easyui-tabs" data-options="tools:'#tab-tools',border:false">
				<div title="版本(0)">
					<table id="rmdatagrid" class="easyui-datagrid" singleSelect="true"
						url="rmTodoTask.do" border="false" 
						striped="true" remoteSort="false"
						rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
						pageSize="10" pageList="[5,10,20,50,100,200,500,1000]" toolbar="#tb">
						<thead>
							<tr>
								<th data-options="field:'versionid', sortable:'true'" width="100px" align="center" formatter="formatid">
									版本号
								</th>
								<th data-options="field:'versionstatus', sortable:'true'" width="100px" align="center" formatter="formatversionstatus">
									状态
								</th>
								<th data-options="field:'versionname', sortable:'true'" width="200px" align="center">
									名称
								</th>
								<th data-options="field:'subsystemname', sortable:'true'" width="200px" align="center">
									子系统
								</th>
							</tr>
						</thead>						
					</table>
				</div>
				<div title="SR(0)">
					<table id="srdatagrid" class="easyui-datagrid" singleSelect="true"
						url="srTodoTask.do" border="false" 
						striped="true" remoteSort="false"
						rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
						pageSize="10" pageList="[5,10,20,50,100,200,500,1000]" toolbar="#tb">
						<thead>
							<tr>
								<th data-options="field:'srid', sortable:'true'" width="100px" align="center" formatter="formatid">
									ID
								</th>
								<th data-options="field:'srstatus', sortable:'true'" width="100px" align="center">
									状态
								</th>
								<th data-options="field:'srname', sortable:'true'" width="200px" align="center">
									名称
								</th>
								<th data-options="field:'subsystemname', sortable:'true'" width="200px" align="center">
									子系统
								</th>
							</tr>
						</thead>						
					</table>				
				</div>
				<div title="测试缺陷(0)">
					<table id="bugdatagrid" class="easyui-datagrid" singleSelect="true"
						url="bugTodoTask.do" border="false" 
						striped="true" remoteSort="false"
						rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
						pageSize="10" pageList="[5,10,20,50,100,200,500,1000]" toolbar="#tb">
						<thead>
							<tr>
								<th data-options="field:'bugid', sortable:'true'" width="100px" align="center" formatter="formatid">
									ID
								</th>
								<th data-options="field:'bugstatus', sortable:'true'" width="100px" align="center">
									状态
								</th>
								<th data-options="field:'bugname', sortable:'true'" width="200px" align="center">
									名称
								</th>
								<th data-options="field:'subsystemname', sortable:'true'" width="200px" align="center">
									子系统
								</th>
							</tr>
						</thead>						
					</table>				
				</div>
				<div title="生产问题(0)">
					<table id="prddatagrid" class="easyui-datagrid" singleSelect="true"
						url="prdTodoTask.do" border="false" 
						striped="true" remoteSort="false"
						rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
						pageSize="10" pageList="[5,10,20,50,100,200,500,1000]" toolbar="#tb">
						<thead>
							<tr>
								<th data-options="field:'prdid', sortable:'true'" width="100px" align="center" formatter="formatid">
									ID
								</th>
								<th data-options="field:'prdstatus', sortable:'true'" width="100px" align="center">
									状态
								</th>
								<th data-options="field:'prdname', sortable:'true'" width="200px" align="center">
									名称
								</th>
								<th data-options="field:'subsystemname', sortable:'true'" width="200px" align="center">
									子系统
								</th>
							</tr>
						</thead>						
					</table>
				</div>
			</div>
					
			<div id="tab-tools">					
				显示
				<select id="todoDays" name="todoDays" style="width: 50px;" onchange="javascript:refreshData(this.value)">
					<option value='30'>30</option>
					<option value='60'>60</option>
					<option value='90'>90</option>
				</select>
				天内活跃的待办任务
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" 
					href="javascript:refleshTodo();" style="font-size: 12px;color: red;font-weight: bold;"></a>					
			</div>
		</div>
		<form id="mainForm" name="mainForm" action="" method="post">
		<div id="eastMain" data-options="region:'east',split:true,title:'帮助文档',collapsible:false" style="width:250px;">
			<div class="easyui-accordion" height="100%" style="width:150px;border:0;" data-options="fit:true">
				<div style="margin-left: 10px">
					<table>
						<tr>
							<td style="padding:13px"><img src="resource/images/computer.png" style="width:40px;height:40px"></img></td>
							<td>
								<font color="RED">推荐分辨率：</font><br/>
								(台式机)1280*1024<br/>
						   		(笔记本)1280*800 | 1366*768
						   	</td>
						</tr>
					</table>
					<div class="wline"></div>
					<table>
						<tr height="100"><td >
							<div class="infoTitle">系统公告&nbsp;&nbsp;&gt;&gt;</div>
							
							<div style="margin-top:5px">
								<ul>
									<li style="padding:3px"><a id="change" style="text-decoration: none;color:#87807F;" href="javascript:;" target="_blank">DLM版本更新历史(点击查看)</a></li>
								</ul>
							</div>
							<div></div>
							<div></div>
						</td></tr>
						<tr height="100" ><td>
							<div class="infoTitle">系统帮助&nbsp;&nbsp;&gt;&gt;</div>
							<div style="margin-top:5px">
								<ul>
									<li style="padding:3px"><a id="user" style="text-decoration: none;color:#87807F;" href="http://itdocshare.sdb.com.cn/Documents/Forms/AllItems.aspx?RootFolder=%2FDocuments%2F07%2D%E6%8A%80%E6%9C%AF%E8%B5%84%E6%96%99%2F01%2D%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E9%85%8D%E7%BD%AE%E7%AE%A1%E7%90%86%E3%80%81%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86%2F%E6%89%8B%E5%86%8C%2FDLM%E6%89%8B%E5%86%8C&InitialTabId=Ribbon%2EDocument&VisibilityContext=WSSTabPersistence" target="_blank">1、用户使用手册</a></li>
									<li style="padding:3px"><a id="role" style="text-decoration: none;color:#87807F;" href="http://itdocshare.sdb.com.cn/Documents/Forms/AllItems.aspx?RootFolder=%2FDocuments%2F07%2D%E6%8A%80%E6%9C%AF%E8%B5%84%E6%96%99%2F01%2D%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E9%85%8D%E7%BD%AE%E7%AE%A1%E7%90%86%E3%80%81%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86%2F%E6%89%8B%E5%86%8C%2FDLM%E6%89%8B%E5%86%8C&InitialTabId=Ribbon%2EDocument&VisibilityContext=WSSTabPersistence" target="_blank">2、角色权限说明</a></li>
									<li style="padding:3px"><a id="faq" style="text-decoration: none;color:#87807F;" href="http://itdocshare.sdb.com.cn/Documents/Forms/AllItems.aspx?RootFolder=%2FDocuments%2F07%2D%E6%8A%80%E6%9C%AF%E8%B5%84%E6%96%99%2F01%2D%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E9%85%8D%E7%BD%AE%E7%AE%A1%E7%90%86%E3%80%81%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86%2F%E6%89%8B%E5%86%8C%2FDLM%E6%89%8B%E5%86%8C" target="_blank">3、FAQ</a></li>
								</ul>
							</div>
							<div></div>
						</td></tr>
						<tr height="100"><td>
							<div class="infoTitle">常用链接&nbsp;&nbsp;&gt;&gt;</div>
							<div style="margin-top:5px">
								<ul>
									<li style="padding:3px"><a id="vp" style="text-decoration: none;color:#87807F;" href="http://itpm.paic.com.cn/project/login.jsp" target="_blank">1、项目管理平台(VP)</a></li>
									<li style="padding:3px"><a id="sonar" style="text-decoration: none;color:#87807F;" href="http://10.14.49.78:9000/" target="_blank">2、代码扫描平台(Sonar)</a></li>
									<li style="padding:3px"><a id="doc" style="text-decoration: none;color:#87807F;" href="http://itdocshare.sdb.com.cn/default.aspx" target="_blank">3、IT文档共享平台(Docshare)</a></li>
								</ul>
							</div>
						</td></tr>
				   </table>
			    </div>
			</div>
		</div>
		</form> --%>
	</body>
	<script type="text/javascript">
		/* $(function() {
			refleshTodo();
		});
		function refleshTodo() {
			var _todoDays = $("#todoDays").val();
			refreshData(_todoDays);
		}
		function refreshData(_todoDays) {
			$.post("queryTabtitle.do",{"todoDays":_todoDays},function(data){
				if (data.status) {
					var _rmcount = data.attr.map.rmcount;
					var _srcount = data.attr.map.srcount;
					var _bugcount = data.attr.map.bugcount;
					var _prdcount = data.attr.map.prdcount;
					showrm(_rmcount);
					showsr(_srcount);
					showbug(_bugcount);
					showprd(_prdcount);
				}
			});
       		// 加载数据
       		$('#rmdatagrid').datagrid('load', {   
       			todoDays: _todoDays       			
       		});
       		$('#srdatagrid').datagrid('load', {    
       			todoDays: _todoDays
       		});
       		$('#bugdatagrid').datagrid('load', {   
       			todoDays: _todoDays
       		});
       		$('#prddatagrid').datagrid('load', {   
       			todoDays: _todoDays
       		});
		}
		function showrm(rmcount) {
	       	var tab0 = $("#todoTabs").tabs('getTab', 0);
	       	$('#todoTabs').tabs('update', {
	           	tab: tab0,
	           	options: {
	              	title: '版本('+rmcount+')'
	           	}
	       	});
		}
		function showsr(srcount) {
			var tab1 = $("#todoTabs").tabs('getTab', 1);
       		$('#todoTabs').tabs('update', {
	           	tab: tab1,
	           	options: {
	              	title: 'SR('+srcount+')'
	           	}
	      	});
		}
		function showbug(bugcount) {
			var tab2 = $("#todoTabs").tabs('getTab', 2);
       		$('#todoTabs').tabs('update', {
	           	tab: tab2,
	           	options: {
	              	title: '测试缺陷('+bugcount+')'
	           	}
	      	});
		}
		function showprd(prdcount) {
			var tab3 = $("#todoTabs").tabs('getTab', 3);
       		$('#todoTabs').tabs('update', {
	           	tab: tab3,
	           	options: {
	              	title: '生产问题('+prdcount+')'
	           	}
	      	});
		}
		//首页查询
		function searchInfomation(_searchcode) {
			if(!_searchcode)return;
			var regexSR = /^SR_.+$/ig;
			var regexBUG = /^BUG_.+$/ig;
			var regexPRD = /^PRD_.+$/ig;
			var _operUrl='';
			if(regexSR.test(_searchcode)) {
				_operUrl="srdevelopeinfo.do?srid=" + _searchcode;
			}else if(regexBUG.test(_searchcode)) {
				_operUrl="bugreport.do?bugid=" + _searchcode;
			} else if(regexPRD.test(_searchcode)) {
				_operUrl="prdreport.do?prdid=" + _searchcode;
			} else {
				_operUrl="verbaseinfo.do?versionid=" + _searchcode;
			}
			return _operUrl;
		}
		function formatid(val, row) {
			if(notEmpty(row.versionid)){
				return '<a href=javascript:linkid("'+row.versionid+'"); style="text-decoration: none;">'+ row.versionid +'</a>';
			}
			if(notEmpty(row.srid)){
				return '<a href=javascript:linkid("'+row.srid+'"); style="text-decoration: none;">'+ row.srid +'</a>';
			}
			if(notEmpty(row.prdid)){
				return '<a href=javascript:linkid("'+row.prdid+'"); style="text-decoration: none;">'+ row.prdid +'</a>';
			}
			if(notEmpty(row.bugid)){
				return '<a href=javascript:linkid("'+row.bugid+'"); style="text-decoration: none;">'+ row.bugid +'</a>';
			}
		}
		function linkid(id) {
			var openUrl = searchInfomation(id);
			addNewTab(id,openUrl);
		}
		function addNewTab(title,openUrl){
			window.parent.add(title, openUrl);
		};
		function formatversionstatus(val, row){
			switch(row.versionstatus){
			case "1":
				return "新建";
				break;
			case "2":
				return "测试中";
				break;
			case "3":
				return "版本待制作";
				break;
			case "4":
				return "生产版本待确认";
				break;
			case "5":
				return "待投产";
				break;
			case "6":
				return "并行版本待同步";
				break;
			case "7":
				return "已完成";
				break;
			case "8":
				return "已关闭";
				break;
			}
		}
		// 测试CIP，以后无用
		function TestingCIP() {
			$.post("testingcip.do", {"taskid": "TASK_24669", "testype":"1"}, function(data) {
				if (data.status) {
					displayMsg(data.info, true);
				} else {
					displayMsg(data.info, false);
				}
			});
		}
		// 测试生产版本制作模板下载
		function testingTemplatePrdver() {
			$.post("beforeValidateTemplatePrdver.do", function(data) {
				if (data.status) {
					mainForm.action="downloadTemplatePrdver.do";
					mainForm.method="post";
					mainForm.submit();
				} else {
					displayMsg(data.info, false);
				}
			});
		}
		//鼠标滑过，变色
		$("#vp").hover(
		  	function () {			  
		  	  	$("#vp").css("color","red");
		  	},
		  	function () {
			 	$("#vp").css("color","#87807F");
		 	}
		);
		$("#sonar").hover(
		  	function () {			  
		    	$("#sonar").css("color","red");
		  	},
		  	function () {
			 	$("#sonar").css("color","#87807F");
		  	}
		);
		$("#doc").hover(
		  	function () {			  
		    	$("#doc").css("color","red");
		  	},
		  	function () {
			 	$("#doc").css("color","#87807F");
		  	}
		);
		$("#change").hover(
		  	function () {			  
		    	$("#change").css("color","red");
		  	},
		  	function () {
			 	$("#change").css("color","#87807F");
		  	}
		);
		$("#user").hover(
		  	function () {			  
		    	$("#user").css("color","red");
	  		},
		  	function () {
			 	$("#user").css("color","#87807F");
		  	}
		);
		$("#faq").hover(
		  	function () {			  
		    	$("#faq").css("color","red");
		  	},
		  	function () {
			 	$("#faq").css("color","#87807F");
		  	}
		);
		$("#role").hover(
		  	function () {			  
		    	$("#role").css("color","red");
		  	},
		  	function () {
			 	$("#role").css("color","#87807F");
		  	}
		); */
	</script>
</html>
