<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新闻管理</title>
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
			新闻标题：
			<input autocomplete="off" name="newsTitle" id="newsTitle" style="width: 150px;height: 25px;"/>&nbsp;&nbsp;
			审核状态：
			<select name="verifyStatus" id="verifyStatus" style="width: 100px;height: 25px;">
				<option value="">全部</option>
				<option value="0">待审核</option>
				<option value="1">审核通过</option>
				<option value="2">审核不通过</option>
			</select>&nbsp;&nbsp;
			审核人：
			<input autocomplete="off" name="verifytor" id="verifytor" style="width: 80px;height: 25px;"/>&nbsp;&nbsp;
			发布人：
			<input autocomplete="off" name="created_by" id="created_by" style="width: 80px;height: 25px;"/>&nbsp;&nbsp;
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:queryNews();">查询</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:addNews();">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-recover',size:'small',plain:'true'" style="margin-left:10px;" onclick="javascript:deleteNews();">删除</a>
		</div>
		
		<!-- 中间文字说明 -->
		<div class="textInfo"></div>
		
		<!-- 列表 -->
		<div class="tableInfo">
			<table id="newsDatagrid" class="easyui-datagrid" singleSelect="false"
				url="newslistpage.hx?sort=date_created&order=desc"  border="false" striped="true" remoteSort="false"
				rownumbers="true" fitColumns="true" pagination="true" pagePosition="bottom"
				pageSize="10" pageList="[1,5,10,20,50,100,200,500,1000]">
				<thead>
					<tr>
						<th data-options="field:'hxuuid'" hidden="true">hxuuid</th>
						<th data-options="field:'chk', sortable:'false'" checkbox="true" ></th>
						<th data-options="field:'newsTitle', sortable:'true'" width="40%" align="center">新闻标题</th>
						<!--  <th data-options="field:'newsSubtitle', sortable:'true'" width="15%" align="center">副标题</th>-->
						<!--<th data-options="field:'newsContent', sortable:'true'" width="35%" align="center">内容</th>-->
						<th data-options="field:'created_by', sortable:'true'" width="8%" align="center">发布人</th>
						<th data-options="field:'date_created', sortable:'true'" width="15%" align="center">发布时间</th>
						<th data-options="field:'verifyStatus', sortable:'true'" width="10%" align="center" formatter="formatVerifyStatus">审核状态</th>
						<th data-options="field:'verifytor', sortable:'true'" width="8%" align="center">审核人</th>
						<th data-options="field:'verify_date', sortable:'true'" width="15%" align="center">审核时间</th>
						<th data-options="field:'opt', sortable:'true'" width="15%" align="center" formatter="formatOption">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<script type="text/javascript">
			// 查询
			function queryNews() {
				var _newsTitle=$("#newsTitle").val();
				var _newsContent=$("#newsContent").val();
				var _verifyStatus=$("#verifyStatus").val();
				var _verifytor=$("#verifytor").val();
				var _created_by=$("#created_by").val();
				$("#newsDatagrid").datagrid('load', {
					url: "newslistpage.hx",
					newsTitle: _newsTitle,
					newsContent:_newsContent,
					verifyStatus:_verifyStatus,
					verifytor:_verifytor,
					created_by:_created_by
				});
			}
			//格式化审核状态
			function formatVerifyStatus(val,row){
				if(val == '0'){
					return '待审核';
				}else if(val == '1'){
					return '审核通过';
				}else if(val == '2'){
					return '审核不通过';
				}
			}
			//格式化列表的操作栏
			function formatOption(val,row){
				var _html = '';
				_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="javascript:linkDetail(\'' +row.hxuuid+ '\');"><span class="l-btn-left"><span class="l-btn-text icon-search l-btn-icon-left">详情</span></span></a>';
				_html += '<a class="easyui-linkbutton l-btn l-btn-plain" plain="true" href="javascript:linkModify(\'' +row.hxuuid+ '\');"><span class="l-btn-left"><span class="l-btn-text icon-pencil l-btn-icon-left">修改</span></span></a>';
				return _html;
			}
			//新闻详情
			function linkDetail(hxuuid) {
				addNewTab("新闻详情","news/newsdetail.hx?hxuuid=" + hxuuid);
			}
			//添加TAB页
			function addNewTab(title,openUrl){
				window.parent.add(title, openUrl);
			}
			//新增新闻
			function addNews(){
				addNewTab("新增新闻","news/addnewspage.hx");
			}
			//修改新闻
			function linkModify(hxuuid){
				addNewTab("修改新闻","news/modifynewspage.hx?hxuuid="+hxuuid);
			}
			//删除新闻
			function deleteNews(){
				var ids = [];
				var rows = $("#newsDatagrid").datagrid('getSelections');
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].hxuuid);
				}
				if (ids.length>0){
					$.messager.confirm('删除', '是否删除新闻?', function(f) {
						if (f) {
							$.ajax({
								url:"deletenews.hx",
								data:"ids="+ids,
								type:"post",
								success:function(data){
									if(data.status){
										displayMsg(data.info, true);
										$("#newsDatagrid").datagrid('reload');
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
		</script>
	</body>
</html>
