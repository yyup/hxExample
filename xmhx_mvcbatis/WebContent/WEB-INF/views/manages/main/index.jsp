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
			<div class="easyui-accordion" style="width:150px; height:200px; border:0;" data-options="fit:true,selected:false">			
				<c:if test="${not empty topmenus}">
					<c:forEach items="${topmenus}" var="menuObj">
						<div title="${menuObj.menu_name}" data-options="href:'${ctx}/manages/childrenmenu.do?menucode=${menuObj.menu_code}'" style="overflow:auto;"></div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="mainTab" class="easyui-tabs" data-options="fit:true,border:false">
				<div title="首页" style="padding:10px">
					<iframe name="mainiframe" id="mainiframe" src="${ctx}/manages/main.do" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>
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
				
				/* $("#mainTab").tabs({
					onSelect:function(title, index) {
						if ( title == '生产问题管理'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('prdmanage.do') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#prddatagrid").datagrid('reload');
							}
						} else if ( title == '版本管理'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('rmmanage.do') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#rmdatagrid").datagrid('reload');
							}
						} else if ( title == '测试缺陷管理'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('bugmanage.do') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#bugdatagrid").datagrid('reload');
							}
						}else if ( title == 'SR管理'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('srmanage.do') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#srdatagrid").datagrid('reload');
							}
						}else if ( title == '测试版本部署'){
							var ifrms = document.getElementsByTagName('iframe');
							var k = -1;
							for (var i = 0; i < ifrms.length; i++ ){
								if ( ifrms[i].src.indexOf('rdmanage.do') != -1 ){
									k = i;
									break;
								}
							}
							if ( k != -1 ){
								ifrms[k].contentWindow.$("#rddatagrid").datagrid('reload');
							}
						}
					}
				}); */
			});
			// 新增页签
			function add(title, operUrl) {
				if(notEmpty(operUrl)){
					var content = '<iframe src="'+operUrl+'" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>';
					if ($("#mainTab").tabs('exists',title)) {
						$("#mainTab").tabs("select",title);
						var tab = $('#mainTab').tabs('getSelected'); 
						$('#mainTab').tabs('update', {
							tab: tab,
							options: {
								title: title,
								content: content  // 新内容的URL
							}
						});
					} else {
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
						window.location.href="logout.do";
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
					url : 'menutree.do?roleid='+idKey,
					iconCls : "icon-save",
					checkbox : true,
					animate : false,
					lines : true,
					onlyLeafCheck : true,
					onExpand : function(node) {
						url : 'menutree.do?id='+node.id;
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
					$.post("saveroleusr.do", {"roleid" : roleid, "mobile" : mobile}, function(data) {
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
					$.post("saverolemenu.do", {"roleid" : roleid, "menucode" : menucode}, function(data) {
						if (data.status) {
							showMsg(data.info, 1);
							closetreewin();
						} else {
							showMsg(data.info, 2);
						}
					});
				}
			}
			
			/* // 查询条件自动填充（作废 by 刘武 20151201）
			function changeSearchcode(_searchcode) {
				$.post("searchcodeAutocomplete.do", {"searchcode":_searchcode}, function(data) {
					if (data.status) {
						searchcodeAutocomplete(data.attr.result);
					}
				});
			}
			// 自动填充
			function searchcodeAutocomplete(data) {
				$("#searchcode").flushCache();
				$('#searchcode').autocomplete(data, {
                    max: 12,    //列表里的条目数
                    minChars: 0,    //自动完成激活之前填入的最小字符
                    width: 300,     //提示的宽度，溢出隐藏
                    scrollHeight: 300,   //提示的高度，溢出显示滚动条
                    matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                    autoFill: false,    //自动填充
                    formatItem: function(row, i, max) {
                        return i + '/' + max + ':"' + row.searchcode + '"[' + row.searchname + ']';
                    },
                    formatMatch: function(row, i, max) {
                        return row.searchcode + row.searchname;
                    },
                    formatResult: function(row) {
                        return row.searchcode;
                    }
                }).result(function(event, row, formatted) {
                    //alert(row.searchcode);
                });
			} */
			/* // 查询
			function searchAnyway(range) {
				var _searchcode = $("#searchcode").val();
				if (_searchcode!=null && $.trim(_searchcode)!='') {
					_searchcode=$.trim(_searchcode);
					var regex="";
					if (range == 'NEED') {
						regex = /^NEED_.+$/ig;
					} else if (range == 'BUG') {
						regex = /^BUG_.+$/ig;
					} else if (range == 'PRD') {
						regex = /^PRD_.+$/ig;
					} else if (range == 'sprd') {
						regex = /^sprd_.+$/ig;
					} else if (range == 'sbug') {
						regex = /^sbug_.+$/ig;
					} else if (range == 'SR') {
						regex = /^SR_.+$/ig;
					} else if (range == 'RD') {
						regex = /^RD_.+$/ig;
					} else if (range == 'RM') {
						//regex = /^RM_.+$/ig;
						regex = /.+/;
					} else {
						regex = /^.+$/ig;
					}
					if (regex.test(_searchcode)) {
						$.ajax({
							url:"searchcodeFormain.do",
							cache: false,
							type:"post",
							data:{"searchcode":_searchcode},
							dataType:"json",							
							success:function(data){
								if (data.status) {
									var _operUrl = searchInfomation(_searchcode);
									if ( _searchcode.indexOf("RM_") == 0 ){
										if(data.attr.rmdto.versionid != null){
											_searchcode = data.attr.rmdto.versionid;
										}
									}
									add(_searchcode, _operUrl);
								} else {
									displayMsg("查询无结果.");
								}
							},
							error:function(data) {
								displayAlert('警告', data.responseText);
							}
						});
					} else {
						displayMsg("查询无结果!");
					}
				} else {
					displayMsg("请输入查询条件");
					$("#searchcode").focus();
				}
			}
			// 新增
			function addAnyway(range) {
				if (range!=null && $.trim(range)!='') {					
				 		 if (range == 'BUG') {
						add("新建测试缺陷", "bugnew.do");
					} else if (range == 'PRD') {
						add("新建生产问题", "prdnew.do");
					} else if (range == 'RM') {
						add("新建版本", "vernewinfo.do");
					} else if (range == 'SUBSYS') {
						add("新建子系统", "subsystnew.do");
					} else {
					}
				} else {
					displayMsg("请输入新增条件");
				}
			} */
			/* // 回车查询
			function querySearchcode(e){
				var key = window.event?e.keyCode:e.which;
				if (key==13) {
					searchAnyway('ALL');
				}
			} */
		</script>
	</body>
</html>
