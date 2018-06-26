<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>首页</title>
		<%@ include file="/WEB-INF/views/include/source.jsp"%>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" class="head">
			<div style="line-height: 58px;padding-right: 50px;">
				<table align="right" >
					<tr><td align="right">
						<span style="font-size: 12px; font-weight: bold;">欢迎您：【${logineduser }】</span> 
						<span>
							<a class="easyui-linkbutton" iconCls="icon-shutdown" plain="true" 
								style="font-size: 12px; font-weight: bold; color: red;"
								href="javascript:loginout();">退出系统</a>
						</span>
					</td></tr>
					<!-- <tr><td>
						<input autocomplete="off" type="text" class="reg_input" name="searchcode" id="searchcode" style="width: 200px;" onkeydown="querySearchcode(event);">
						<a href="javascript:void(0)" id="sb" class="easyui-splitbutton"   
					        data-options="menu:'#mmsearch',iconCls:'icon-search'" onclick="javascript:searchAnyway('ALL');">查询</a>
					    <a href="javascript:void(0)" id="sb" class="easyui-menubutton"   
					        data-options="menu:'#mmadd',iconCls:'icon-add'">新增</a>
					</td></tr> -->
				</table>
			</div>
		</div>		
		<div data-options="region:'west',split:true,title:'功能导航'" style="width:200px; height: 500px;">		
			<div id="accordion_menu" class="easyui-accordion" style="width:150px; height:200px; border:0;" data-options="fit:true,selected:false">			
				<c:if test="${not empty topmenus}">
					<c:forEach items="${topmenus}" var="menuObj" >
						<div title="${menuObj.menu_name}" data-options="href:'${ctx}/manages/menu/childrenmenu.hx?menucode=${menuObj.menu_code}'" style="overflow:auto"></div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		  
		<div data-options="region:'center'">
			<div id="mainTab" class="easyui-tabs" data-options="fit:true,border:false">
				<div title="首页" style="padding:10px">
					<!--  <iframe name="mainiframe" id="mainiframe" src="${ctx}/manages/main.hx" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>
					-->				
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" class="bottom">
			CopyRight @ 2015 弘信创业工场投资集团股份有限公司
		</div>
		
		<!-- 查询
		<div id="mmsearch" style="width:100px;">   		 
		    <div><a href="javascript:searchAnyway('BUG');" style="text-decoration: none;">
		    	<span>测试缺陷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('PRD');" style="text-decoration: none;">
		    	<span>生产问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('sbug');" style="text-decoration: none;">
		    	<span>测试子问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('sprd');" style="text-decoration: none;">
		    	<span>生产子问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('SR');" style="text-decoration: none;">
		    	<span>SR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('RM');" style="text-decoration: none;">
		    	<span>版&nbsp;&nbsp;本&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:searchAnyway('RD');" style="text-decoration: none;">
		    	<span>版本移交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		</div>  -->
		<!-- 新增
		<div id="mmadd" style="width:100px;">   		    
		    <c:if test="${dr_newbug == 1}">
		    	<div><a href="javascript:addAnyway('BUG');" style="text-decoration: none;">
		    		<span>测试缺陷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
		    	</div>
		    </c:if>
		    <c:if test="${dr_newprd == 1}">
		    	<div><a href="javascript:addAnyway('PRD');" style="text-decoration: none;">
		    		<span>生产问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
		    	</div>
		    </c:if>
		    <c:if test="${dr_newversion == 1}">
		    	<div><a href="javascript:addAnyway('RM');" style="text-decoration: none;">
		    		<span>版&nbsp;&nbsp;本&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
		    	</div>
		    </c:if>
		</div> -->
		<!-- 帮助
		<div id="mmhelp" style="width:100px;">   
		    <div><a href="javascript:void(0)" style="text-decoration: none;">
		    	<span>帮助首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		    <div><a href="javascript:void(0)" style="text-decoration: none;">
		    	<span>问题与建议&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div>
		</div> -->
		
		<!-- 左右选择组件 -->
		<div id="selectDiv" class="easyui-window" style="width:900px;height:400px;" 
			minimizable="false" maximizable="false" modal="true" collapsible="true" closed="true">
			<form name="selectfrm" id="selectfrm" method="post">
				<input autocomplete="off" type="hidden" name="roleId" id="roleId"/>
				<div id="divSelect" class="menuTree" data-options="region:'north'" style="overflow: auto;padding-left: 5px;">
					<div id="divHYGL_select" class="dtree">
						<table align="center" style="margin-left:10px;">
							<tr>
								<th style="font-size: 20px;color: #0b99d8;" align="left">未绑定该角色用户</th>
								<th style="font-size: 20px;text-align: center;" width="200" align="center">操作</th>
								<th style="font-size: 20px;color: #0b99d8;" align="right">已绑定该角色用户</th>
							</tr>
							<tr>
								<td align="left">
									<select multiple="multiple" style="width:300px;height:250px;margin-right:20px;" id="selected_user"></select>
								</td>
								<td width="200" align="center">
									<a class="easyui-linkbutton" href="javascript:youyi();">&gt;&gt;右移</a>
									<br><br>
									<a class="easyui-linkbutton" href="javascript:zuoyi();">&lt;&lt;左移</a>
								</td>
								<td align="right">
									<select multiple="multiple" style="width:300px;height:250px;margin-right:20px;" id="all_user"></select>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="text-align: center;" data-options="region:'south',border:false">
					<a class="easyui-linkbutton" href="javascript:saveSelect();">保存</a>
					&nbsp;&nbsp;
					<a class="easyui-linkbutton" href="javascript:closeselectwin();">关闭</a>
				</div>
			</form>
		</div>
		<!-- 树组件 -->
		<div id="treeDiv" class="easyui-window" style="width:600px;height:400px;" 
			minimizable="false" maximizable="false" modal="true" collapsible="true" closed="true">
			<form name="rolemenufrm" id="rolemenufrm" method="post">
				<input autocomplete="off" type="hidden" name="roleid" id="roleid"/>
				<div id="divHYGL" class="menuTree" data-options="region:'north'" style="width: auto;height:300px;overflow: auto;padding-left: 5px;">
					<div id="divHYGL_tree" class="dtree"></div>
				</div>
				<div style="text-align: center;" data-options="region:'south',border:false">
					<a class="easyui-linkbutton" href="javascript:saveroleany();">保存</a>
					&nbsp;&nbsp;
					<a class="easyui-linkbutton" href="javascript:closetreewin();">关闭</a>
				</div>
			</form>
		</div>
		<!-- 鼠标右键 -->
		<div id="rcmenu" class="easyui-menu" style="display: none; width: 100px;background-color: #FCEAD9;">
			<div id="closecurrent">关闭当前页签</div>
			<div id="closeall">关闭全部</div>
			<div id="closeother">关闭其他</div>
		</div>
		<script type="text/javascript">
			$(function() {
				//默认展开第一个菜单
				$("#accordion_menu").accordion("select",0);
				// 绑定鼠标右键
				$(".tabs-wrap").bind('contextmenu',function(e){
					e.preventDefault();
					$('#rcmenu').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				});
				//关闭当前页签
				$("#closecurrent").bind("click",function(){
					var tab = $('#mainTab').tabs('getSelected');
					var index = $('#mainTab').tabs('getTabIndex',tab);
					if (index > 0)
						$('#mainTab').tabs('close',index);
				});
				//关闭全部页签
				$("#closeall").bind("click",function(){
					var tablist = $('#mainTab').tabs('tabs');
					for(var i=tablist.length-1;i>0;i--){
						$('#mainTab').tabs('close',i);
					}
				});
				//关闭其他，非当前标签页（先关闭右侧，再关闭左侧）
				$("#closeother").bind("click",function(){
					var tablist = $('#mainTab').tabs('tabs');
					var tab = $('#mainTab').tabs('getSelected');
					var index = $('#mainTab').tabs('getTabIndex',tab);
					for(var i=tablist.length-1;i>index;i--){
						$('#mainTab').tabs('close',i);
					}
					var num = index-1;
					for(var i=num;i>0;i--){
						$('#mainTab').tabs('close',i);
					}
				});
				//激活TAB页时刷新列表
				 $("#mainTab").tabs({
					onSelect:function(title, index) {
						if ( title == '新闻管理'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('news/list.hx') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#newsDatagrid").datagrid('reload');
							}
						} 
					}
				}); 
			});
			// 新增页签
			function add(title, operUrl) {
				if(notEmpty(operUrl)){
					var content = '<iframe src="'+operUrl+'" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>';
					if ($("#mainTab").tabs('exists',title)) {
						//存在该TAB页，则看情况更新
						$("#mainTab").tabs("select",title);//选择该TAB页
						var tab = $('#mainTab').tabs('getSelected'); 
						if(tab.panel('options').title.indexOf('新增')!=-1){
							//标题包含新增的页面不更新
						}else{
							//更新该TAB页
							$('#mainTab').tabs('update', {
								tab: tab,
								options: {
									title: title,
									content: content  // 新内容的URL
								}
							});
						}
					} else {
						//不存在该TAB页，则新增TAB页面
						$('#mainTab').tabs('add', {
							title: title,
							content:content,
							closable: true
						});
					}					
				}else{
					displayMsg("未找到匹配的查询结果");
				}
			}
			// 退出系统
			function loginout(){
				$.messager.defaults = {
					ok : '是',
					cancel : '否',
				};
				$.messager.confirm('确认对话框','是否退出系统?', function (flag) {
					if (flag) {
						window.location.href="logout.hx";
					}
				});
			}
			// 分配用户
			function toroleusr(idKey){
				$("#roleId").val(idKey);
				$("#selectDiv").window({title:"分配用户"});
				$("#selectDiv").window('open');
			}
			// 分配权限
			function torolemenu(idKey){
				$("#divHYGL_tree").empty();
				$("#roleid").val(idKey);
				$("#divHYGL_tree").tree({
					url : 'menutree.hx?roleid='+idKey,
					iconCls : "icon-save",
					checkbox : true,
					animate : false,
					lines : true,
					onlyLeafCheck : true,
					onExpand : function(node) {
						url : 'menutree.hx?id='+node.id;
					}
				});
				$("#treeDiv").window({title:"分配权限"});
				$("#treeDiv").window('open');
			}
			// 角色分配用户保存
			function saveroleany() {
				var roleid = $("#roleid").val();
				var opertype = $("#opertype").val();
				if (opertype=='usr') {
					// 分配用户保存
					var mobile = '';
					var _checkedpoints = $('#divHYGL_tree').tree('getChecked');
					for (var i = 0, n = _checkedpoints.length; i < n; i++) {
						mobile += _checkedpoints[i].id + ",";
					}
					$.post("saveroleusr.hx", {"roleid" : roleid, "mobile" : mobile}, function(data) {
						if (data.status) {
							showMsg(data.info, 1);
							closetreewin();
						} else {
							showMsg(data.info, 2);
						}
					});
				} else if (opertype=='menu') {
					// 分配权限保存
					var menucode = '';
					var _checkednodes = $('#divHYGL_tree').tree('getChecked');
					for (var i = 0, n = _checkednodes.length; i < n; i++) {
						menucode += _checkednodes[i].id + ",";
					}
					var _indeternodes = $('#divHYGL_tree').tree('getChecked', 'indeterminate');
					for (var i = 0, n = _indeternodes.length; i < n; i++) {
						menucode += _indeternodes[i].id + ",";
					}
					$.post("saverolemenu.hx", {"roleid" : roleid, "menucode" : menucode}, function(data) {
						if (data.status) {
							showMsg(data.info, 1);
							closetreewin();
						} else {
							showMsg(data.info, 2);
						}
					});
				}
			}
		</script>
	</body>
</html>
